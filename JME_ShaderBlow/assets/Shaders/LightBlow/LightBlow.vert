#define ATTENUATION
//#define HQ_ATTENUATION

uniform mat4 g_WorldViewProjectionMatrix;
uniform mat4 g_WorldViewMatrix;
uniform mat3 g_NormalMatrix;
uniform mat4 g_ViewMatrix;

varying vec3 mat;



#ifdef SEPERATE_TEXCOORD
    attribute vec2 inTexCoord2;
    varying vec2 texCoord2;
#endif

uniform vec4 m_Ambient;
uniform vec4 m_Diffuse;
uniform vec4 m_Specular;
uniform float m_Shininess;

uniform vec4 g_LightColor;
uniform vec4 g_LightPosition;
uniform vec4 g_AmbientLightColor;

varying vec2 texCoord;

varying vec3 AmbientSum;
varying vec4 DiffuseSum;
varying vec3 SpecularSum;

attribute vec3 inPosition;
attribute vec2 inTexCoord;
attribute vec3 inNormal;

#ifdef HQ_ATTENUATION
  varying vec3 lightVec;
#endif

#ifdef VERTEX_COLOR
  attribute vec4 inColor;
#endif

#ifndef VERTEX_LIGHTING
  attribute vec4 inTangent;


  #ifndef NORMALMAP
    varying vec3 vNormal;
  #endif
  varying vec3 vPosition;
  varying vec3 vViewDir;
  varying vec4 vLightDir;
#endif


#if defined(REFLECTION) || defined(IBL)
    uniform vec3 g_CameraPosition;
    uniform mat4 g_WorldMatrix;
    varying vec4 refVec;
    varying vec4 iblVec;
#endif 









// JME3 lights in world space
void lightComputeDir(in vec3 worldPos, in vec4 color, in vec4 position, out vec4 lightDir){
    float posLight = step(0.5, color.w);
    vec3 tempVec = position.xyz * sign(posLight - 0.5) - (worldPos * posLight);
    #ifdef ATTENUATION
     float dist = length(tempVec);
     lightDir.w = clamp(1.0 - position.w * dist * posLight, 0.0, 1.0);
     lightDir.xyz = tempVec / vec3(dist);
     #ifdef HQ_ATTENUATION
       lightVec = tempVec;
     #endif
    #else
     lightDir = vec4(normalize(tempVec), 1.0);
    #endif
}

#ifdef VERTEX_LIGHTING
  float lightComputeDiffuse(in vec3 norm, in vec3 lightdir){
      return max(0.0, dot(norm, lightdir));
  }

  #if defined(SPECULAR_LIGHTING) && defined(VERTEX_LIGHTING)
  float lightComputeSpecular(in vec3 norm, in vec3 viewdir, in vec3 lightdir, in float shiny){
        #ifndef LOW_QUALITY
        vec3 H = (viewdir + lightdir) * vec3(0.5);
        return pow(max(dot(H, norm), 0.0), shiny);
        #else
        return 0.0;
        #endif
  }
  #endif

vec2 computeLighting(in vec3 wvPos, in vec3 wvNorm, in vec3 wvViewDir, in vec4 wvLightPos){
     vec4 lightDir;
     lightComputeDir(wvPos, g_LightColor, wvLightPos, lightDir);

     float diffuseFactor = lightComputeDiffuse(wvNorm, lightDir.xyz);
     
   #if defined(SPECULAR_LIGHTING) && defined(VERTEX_LIGHTING)
float specularFactor = lightComputeSpecular(wvNorm, wvViewDir, lightDir.xyz, m_Shininess);
     //specularFactor *= step(0.01, diffuseFactor);
     return vec2(diffuseFactor, specularFactor) * vec2(lightDir.w);
    #endif 
 #if !defined(SPECULAR_LIGHTING) && defined(VERTEX_LIGHTING)
     return vec2(diffuseFactor, 0.0) * vec2(lightDir.w);
   #endif  

}
#endif


 



void main(){

#ifdef SEPERATE_TEXCOORD
        texCoord2 = inTexCoord2;
    #endif

vec4  temp;

   vec4 pos = vec4(inPosition, 1.0);
   gl_Position = g_WorldViewProjectionMatrix * pos;
   texCoord = inTexCoord;

   vec3 wvPosition = (g_WorldViewMatrix * pos).xyz;
   vec3 wvNormal  = normalize(g_NormalMatrix * inNormal);
   vec3 viewDir = normalize(-wvPosition);

       //vec4 lightColor = g_LightColor[gl_InstanceID];
       //vec4 lightPos   = g_LightPosition[gl_InstanceID];
       //vec4 wvLightPos = (g_ViewMatrix * vec4(lightPos.xyz, lightColor.w));
       //wvLightPos.w = lightPos.w;

   vec4 wvLightPos = (g_ViewMatrix * vec4(g_LightPosition.xyz,clamp(g_LightColor.w,0.0,1.0)));
   wvLightPos.w = g_LightPosition.w;
   vec4 lightColor = g_LightColor;

   #if defined(NORMALMAP) && !defined(VERTEX_LIGHTING)
     vec3 wvTangent = normalize(g_NormalMatrix * inTangent.xyz);
     vec3 wvBinormal = cross(wvNormal, wvTangent);


     mat3 tbnMat = mat3(wvTangent, wvBinormal * -inTangent.w,wvNormal);

     
     mat = vec3(1.0) * tbnMat;
     mat = normalize(mat);
     vPosition = wvPosition * tbnMat;
     vViewDir  = viewDir * tbnMat;
     lightComputeDir(wvPosition, lightColor, wvLightPos, vLightDir);
     vLightDir.xyz = (vLightDir.xyz * tbnMat).xyz;
   #elif !defined(VERTEX_LIGHTING)
     vNormal = wvNormal;

     vPosition = wvPosition;
     vViewDir = viewDir;

     lightComputeDir(wvPosition, lightColor, wvLightPos, vLightDir);

     #ifdef V_TANGENT
        vNormal = normalize(g_NormalMatrix * inTangent.xyz);
        vNormal = -cross(cross(vLightDir.xyz, vNormal), vNormal);
     #endif
   #endif

   lightColor.w = 1.0;


   #ifdef MATERIAL_COLORS
      AmbientSum  = m_Ambient.rgb  * g_AmbientLightColor.rgb;
      DiffuseSum  = m_Diffuse  * lightColor;
      SpecularSum = m_Specular.rgb * lightColor.rgb;
    #else
      AmbientSum  = vec3(0.2, 0.2, 0.2) * g_AmbientLightColor.rgb; // Default: ambient color is dark gray
      DiffuseSum  = lightColor;
      SpecularSum = m_Specular.rgb * lightColor.rgb;
    #endif


    #ifdef VERTEX_COLOR
      AmbientSum *= inColor.rgb;
      DiffuseSum *= inColor;
    #endif

 
    #ifdef VERTEX_LIGHTING
       vec2 light = computeLighting(wvPosition, wvNormal, viewDir, wvLightPos);

       AmbientSum.a  = light.x;
       SpecularSum.a = light.y;
    #endif



#if defined (REFLECTION) || defined (IBL)
//Reflection vectors calculation


vec3 worldPos = (g_WorldMatrix * pos).xyz;

       vec3 I = normalize( g_CameraPosition -  worldPos  ).xyz;
       vec3 N = normalize( (g_WorldMatrix * vec4(inNormal, 0.0)).xyz );      

        refVec.xyz = reflect(I, N);
  

        iblVec.xyz = refVec.xyz;
        
        

    #endif
}
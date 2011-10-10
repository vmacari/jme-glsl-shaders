#import "Common/ShaderLib/Optics.glsllib"
varying vec2 texCoord;
uniform float m_texturesize;
  varying vec3 vPosition;
  varying vec3 vViewDir;

  uniform sampler2D m_DiffuseMap;

#ifdef NORMALMAP
  uniform sampler2D m_NormalMap;
 uniform float  m_NormalMapPower;    
  varying vec3 mat;
  varying vec3 I;  
  varying vec3 N;   
  varying vec3 worldPos; 


#endif
  varying vec3 vNormal;



    uniform float m_RefPower;
    uniform float m_RefIntensity;
    varying vec3 refVec;
    uniform ENVMAP m_RefMap;



void main(){

vec2 newTexCoord;
newTexCoord = texCoord;

 #ifdef NORMALMAP
      vec3 normalHeight = texture2D(m_NormalMap, newTexCoord).rgb;
   //   vec3 normal = ((normalHeight.xyz  - vec3(0.5))* vec3(2.0));
     vec3 normal = (normalHeight.xyz * vec3(2.0) - vec3(1.0));
      normal = normalize(normal);

    #if defined (NOR_INV_X) && (NORMALMAP) 
    normal.x = -normal.x;
    #endif

    #if defined (NOR_INV_Y) && (NORMALMAP)
    normal.y = -normal.y;
    #endif

    #if defined (NOR_INV_Z) && (NORMALMAP)
    normal.z = -normal.z;
    #endif
 
      #else 
      vec3 normal = vNormal;
    #endif

 vec3  vmr = vNormal.xyz;
vec3 coords = (vmr);

vec3 diffuseColor;


    #if defined (NORMALMAP)
vec3  normalz = mat.xyz*normal.xyz;
vec4 refGet = Optics_GetEnvColor(m_RefMap, (refVec + normalz*m_NormalMapPower));
#else
 vec4 refGet = Optics_GetEnvColor(m_RefMap, refVec);
//    diffuseColor = (diffuseColor - vec3(0.5, 0.5, 0.5) * 2.0);
#endif
    

    gl_FragColor.rgb = refGet.rgb;
    gl_FragColor.a = 1.0;

}

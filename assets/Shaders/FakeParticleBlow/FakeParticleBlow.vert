uniform mat4 g_WorldViewProjectionMatrix;
uniform float g_Time;

#if defined (ANY_DIR_Y) || defined (ANY_DIR_X)

uniform float m_TimeSpeed;
#endif
 
attribute vec3 inPosition;
attribute vec2 inTexCoord;
 
varying vec2 texCoord;
varying vec2 texCoordAni;
 
void main(){
    gl_Position = g_WorldViewProjectionMatrix * vec4(inPosition, 1.0);
 
float thisTime = g_Time * m_TimeSpeed;


texCoord = inTexCoord;
texCoordAni = inTexCoord;




#if defined (ANY_DIR_Y) && !defined (CHANGE_DIR)
texCoordAni.y += thisTime;
#elif defined (ANY_DIR_Y) && defined (CHANGE_DIR)
texCoordAni.y -= thisTime;
#endif

#if defined (ANY_DIR_X) && !defined (CHANGE_DIR)
texCoordAni.x += thisTime;
#elif defined (ANY_DIR_X) && defined (CHANGE_DIR)
texCoordAni.x -= thisTime;
#endif


}
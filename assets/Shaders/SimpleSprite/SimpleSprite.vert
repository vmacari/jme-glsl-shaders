uniform mat4 g_WorldViewProjectionMatrix;
uniform float g_Tpf;

attribute vec3 inPosition;
attribute vec2 inTexCoord;
 
varying vec2 texCoordAni;
 
uniform float m_X_Images;
uniform float m_Y_Images;


void main(){
 
   gl_Position = g_WorldViewProjectionMatrix * vec4(inPosition, 1.0);
 
g_Tpf;

texCoordAni = inTexCoord;
texCoordAni.x = 1/m_X_Images;
texCoordAni.y = 1/m_Y_Images;

// Here I need the Array
texCoordAni.y += g_Tpf;
texCoordAni.x += g_Tpf;



}
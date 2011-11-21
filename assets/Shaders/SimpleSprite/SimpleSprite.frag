varying vec2 texCoord;
varying vec2 texCoordAni;

uniform vec4 m_Multiply_Color;

uniform sampler2D m_AniTexMap;

void main(){

vec3 AniTex = texture2D(m_AniTexMap, vec2(texCoordAni)).rgb;

        gl_FragColor.rgb = m_Multiply_Color.rgb * AniTex;
        gl_FragColor.a = m_BaseColor.a;
}
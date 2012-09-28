uniform sampler2D m_Texture;
 
varying vec2 texCoord;
 
void main() {
     
    // Convert to grayscale
    vec3 colour = texture2D(m_Texture, texCoord).xyz;
    float gray = (colour.x + colour.y + colour.z) / 3.0;
    vec3 grayscale = vec3(gray);
     
    gl_FragColor = vec4(grayscale, 1.0);
}
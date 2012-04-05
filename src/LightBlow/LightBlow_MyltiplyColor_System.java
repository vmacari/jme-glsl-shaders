
package LightBlow;

import FixedTangentBinormalGenerator.FixedTangentBinormalGenerator;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.TextureKey;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import com.jme3.util.TangentBinormalGenerator;


public class LightBlow_MyltiplyColor_System extends SimpleApplication {


    
    
  public static void main(String[] args) {
    LightBlow_MyltiplyColor_System app = new LightBlow_MyltiplyColor_System();
    app.start();
  }
    

    
    
  @Override
  public void simpleInitApp() {

      TextureKey skyhi = new TextureKey("Textures/Water256.dds", true);
        skyhi.setGenerateMips(true);
        skyhi.setAsCube(true);
      final  Texture texhi = assetManager.loadTexture(skyhi);

      
//      TextureKey skylow = new TextureKey("Textures/Water32.dds", true);
//        skylow.setGenerateMips(true);
//        skylow.setAsCube(true);
      final  Texture texlow = assetManager.loadTexture(skyhi);
         rootNode.attachChild(SkyFactory.createSky(assetManager, texlow, false));

         
         
    Spatial char_boy = assetManager.loadModel("Models/LightBlow/jme_lightblow.blend");
    Material mat = assetManager.loadMaterial("Materials/LightBlow/MultiplyColor_System/MultiplyColor_Base.j3m");
    char_boy.setMaterial(mat);
    FixedTangentBinormalGenerator.generate(char_boy);
    rootNode.attachChild(char_boy);

    Spatial char_boy2 = assetManager.loadModel("Models/LightBlow/jme_lightblow.blend");
    Material mat2 = assetManager.loadMaterial("Materials/LightBlow/MultiplyColor_System/MultiplyColor_1.j3m");
    char_boy2.setMaterial(mat2);
    char_boy2.setLocalTranslation(-2f, 0, 0);
    FixedTangentBinormalGenerator.generate(char_boy2);
    rootNode.attachChild(char_boy2);
    
    
    Spatial char_boy3 = assetManager.loadModel("Models/LightBlow/jme_lightblow.blend");
    Material mat3 = assetManager.loadMaterial("Materials/LightBlow/MultiplyColor_System/MultiplyColor_2.j3m");
    char_boy3.setMaterial(mat3);
    char_boy3.setLocalTranslation(-4f, 0, 0);
    FixedTangentBinormalGenerator.generate(char_boy3);
    rootNode.attachChild(char_boy3);
    
    Spatial char_boy4 = assetManager.loadModel("Models/LightBlow/jme_lightblow.blend");
    Material mat4 = assetManager.loadMaterial("Materials/LightBlow/MultiplyColor_System/MultiplyColor_3.j3m");
    char_boy4.setMaterial(mat4);
    char_boy4.setLocalTranslation(-6f, 0, 0);
    FixedTangentBinormalGenerator.generate(char_boy4);
    rootNode.attachChild(char_boy4);
    


        flyCam.setMoveSpeed(5);   
     

        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.8f, -0.6f, -0.08f).normalizeLocal());
        dl.setColor(new ColorRGBA(1,1,1,1));
        rootNode.addLight(dl);
        
        AmbientLight al = new AmbientLight();
        al.setColor(new ColorRGBA(1.5f,1.5f,1.5f,1.0f));
        rootNode.addLight(al);
        
        
  }
  

@Override
    public void simpleUpdate(float tpf){
 

    
    
  
    }
    
    
    }
  




package Glass;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.TextureKey;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import com.jme3.util.TangentBinormalGenerator;


public class Glass extends SimpleApplication {

    Spatial fire;

    
  public static void main(String[] args) {
    Glass app = new Glass();
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
      
      
      
    Spatial char_boy1 = assetManager.loadModel("Models/LightBlow/jme_lightblow.obj");
    Material mat1 = assetManager.loadMaterial("Materials/Glass/Glass1.j3m");
    char_boy1.setMaterial(mat1);
    char_boy1.setLocalTranslation(0,0,0);
    TangentBinormalGenerator.generate(char_boy1);
    rootNode.attachChild(char_boy1);

    Spatial char_boy2 = assetManager.loadModel("Models/LightBlow/jme_lightblow.obj");
    Material mat2 = assetManager.loadMaterial("Materials/Glass/Glass1_bump.j3m");
    char_boy2.setMaterial(mat2);
    char_boy2.setLocalTranslation(1,0,0);
    TangentBinormalGenerator.generate(char_boy2);
    rootNode.attachChild(char_boy2);    

        Spatial char_boy3 = assetManager.loadModel("Models/LightBlow/jme_lightblow.obj");
    Material mat3 = assetManager.loadMaterial("Materials/Glass/Glass2_low.j3m");
    char_boy3.setMaterial(mat3);
    char_boy3.setLocalTranslation(-1,0,0);
    TangentBinormalGenerator.generate(char_boy3);
    rootNode.attachChild(char_boy3);

    Spatial char_boy4 = assetManager.loadModel("Models/LightBlow/jme_lightblow.obj");
    Material mat4 = assetManager.loadMaterial("Materials/Glass/Glass3_color.j3m");
    char_boy4.setMaterial(mat4);
    char_boy4.setLocalTranslation(-2,0,0);
    TangentBinormalGenerator.generate(char_boy4);
    rootNode.attachChild(char_boy4);    
    
        Spatial char_boy5 = assetManager.loadModel("Models/LightBlow/jme_lightblow.obj");
    Material mat5 = assetManager.loadMaterial("Materials/Glass/Glass4_specular.j3m");
    char_boy5.setMaterial(mat5);
    char_boy5.setLocalTranslation(-3,0,0);
    TangentBinormalGenerator.generate(char_boy5);
    rootNode.attachChild(char_boy5);    


      flyCam.setMoveSpeed(10);   
      viewPort.setBackgroundColor(ColorRGBA.Gray);      
  
  }
  

@Override
    public void simpleUpdate(float tpf){
 

    }
    
    
    }
  


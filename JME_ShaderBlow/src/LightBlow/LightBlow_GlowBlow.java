

package LightBlow;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetKey;
import com.jme3.asset.TextureKey;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import com.jme3.util.TangentBinormalGenerator;


public class LightBlow_GlowBlow extends SimpleApplication {


    FilterPostProcessor fpp;
    
  public static void main(String[] args) {
    LightBlow_GlowBlow app = new LightBlow_GlowBlow();
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

         
         
    Spatial char_boy = assetManager.loadModel("Models/LightBlow/jme_lightblow.obj");
    char_boy.setMaterial( (Material) assetManager.loadAsset(new AssetKey("Materials/LightBlow/Shading_System/LightBlow_ibl.j3m")));
    TangentBinormalGenerator.generate(char_boy);
    rootNode.attachChild(char_boy);

    Spatial char_boy2 = assetManager.loadModel("Models/LightBlow/jme_lightblow.obj");
    char_boy2.setMaterial( (Material) assetManager.loadAsset(new AssetKey("Materials/LightBlow/Shading_System/LightBlow_reflection.j3m")));
    char_boy2.setLocalTranslation(-2f, 0, 0);
    TangentBinormalGenerator.generate(char_boy2);
    rootNode.attachChild(char_boy2);
    
    
    Spatial char_boy3 = assetManager.loadModel("Models/LightBlow/jme_lightblow.obj");
    char_boy3.setMaterial( (Material) assetManager.loadAsset(new AssetKey("Materials/LightBlow/Shading_System/LightBlow_ref_a_nor.j3m")));  
    char_boy3.setLocalTranslation(-4f, 0, 0);
    TangentBinormalGenerator.generate(char_boy3);
    rootNode.attachChild(char_boy3);
    
    Spatial char_boy4 = assetManager.loadModel("Models/LightBlow/jme_lightblow.obj");
    char_boy4.setMaterial( (Material) assetManager.loadAsset(new AssetKey("Materials/LightBlow/Shading_System/LightBlow_minnaert.j3m")));  
    char_boy4.setLocalTranslation(-6f, 0, 0);
    TangentBinormalGenerator.generate(char_boy4);
    rootNode.attachChild(char_boy4);
    
    Spatial char_boy5 = assetManager.loadModel("Models/LightBlow/jme_lightblow.obj");
    char_boy5.setMaterial( (Material) assetManager.loadAsset(new AssetKey("Materials/LightBlow/Shading_System/LightBlow_rim.j3m")));  
    char_boy5.setLocalTranslation(-8f, 0, 0);
    TangentBinormalGenerator.generate(char_boy5);
    rootNode.attachChild(char_boy5);
    
    Spatial char_boy6 = assetManager.loadModel("Models/LightBlow/jme_lightblow.obj");
    char_boy6.setMaterial( (Material) assetManager.loadAsset(new AssetKey("Materials/LightBlow/Shading_System/LightBlow_rim_2.j3m")));  
    char_boy6.setLocalTranslation(-10f, 0, 0);
    TangentBinormalGenerator.generate(char_boy6);
    rootNode.attachChild(char_boy6);
    

    
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.8f, -0.6f, -0.08f).normalizeLocal());
        dl.setColor(new ColorRGBA(1,1,1,1));
        rootNode.addLight(dl);

        flyCam.setMoveSpeed(5);   
     

        
        
        fpp=new FilterPostProcessor(assetManager);
       // fpp.setNumSamples(4);
        BloomFilter bloom=new BloomFilter(BloomFilter.GlowMode.Objects);
        bloom.setDownSamplingFactor(2);
        bloom.setBlurScale(2.37f);
        bloom.setExposurePower(2.9f);
        bloom.setExposureCutOff(0.1f);
        bloom.setBloomIntensity(2.3f);
 //       BloomUI ui=new BloomUI(inputManager, bloom);        

        
        fpp.addFilter(bloom);
        viewPort.addProcessor(fpp);
        
 //       initInputs();
        

        
        
        
  }
  

@Override
    public void simpleUpdate(float tpf){
 

   //    rootNode.rotate(0, tpf * 0.25f, 0);
    
    
  
    }
    
    
    }
  


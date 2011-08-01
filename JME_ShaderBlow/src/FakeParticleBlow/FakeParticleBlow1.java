

package FakeParticleBlow;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.TextureKey;
import com.jme3.material.Material;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;

/** Sample 6 - how to give an object's surface a material and texture.
 * How to make objects transparent, or let colors "leak" through partially
 * transparent textures. How to make bumpy and shiny surfaces.  */
public class FakeParticleBlow1 extends SimpleApplication {

    Spatial fire;

    
  public static void main(String[] args) {
    FakeParticleBlow1 app = new FakeParticleBlow1();
    app.start();
  }

    
    
  @Override
  public void simpleInitApp() {

//      TextureKey skyhi = new TextureKey("Textures/Water256.dds", true);
//        skyhi.setGenerateMips(true);
//        skyhi.setAsCube(true);
//      final  Texture texhi = assetManager.loadTexture(skyhi);
  
      
      TextureKey skylow = new TextureKey("Textures/Water32.dds", true);
        skylow.setGenerateMips(true);
        skylow.setAsCube(true);
      final  Texture texlow = assetManager.loadTexture(skylow);
   
          rootNode.attachChild(SkyFactory.createSky(assetManager, texlow, false));
      
       fire = assetManager.loadModel("Models/FakeParticleBlow/FakeParticleBlow.j3o");


       Material mat = assetManager.loadMaterial("Materials/FakeParticleBlow/FakeParticleBlow.j3m");
       fire.setMaterial(mat);
        
    fire.setQueueBucket(Bucket.Transparent); 


      rootNode.attachChild(fire);

      flyCam.setMoveSpeed(5);   
  
  }
  

@Override
    public void simpleUpdate(float tpf){
 

    }
    
    
    }
  


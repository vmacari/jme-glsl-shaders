

package FakeParticleBlow;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.TextureKey;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;


public class FakeParticleBlow extends SimpleApplication {

    Spatial fire;

    
  public static void main(String[] args) {
    FakeParticleBlow app = new FakeParticleBlow();
    app.start();
  }
    private Material mat;
    
    
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
      mat = new Material(assetManager, "MatDefs/FakeParticleBlow/FakeParticleBlow.j3md");

        Texture tex = assetManager.loadTexture("Models/FakeParticleBlow/particles.png");
        tex.setWrap(Texture.WrapMode.Repeat);      
      mat.setTexture("MaskMap", assetManager.loadTexture("Models/FakeParticleBlow/mask.png"));
      mat.setTexture("AniTexMap", tex);
      
      mat.setFloat("m_TimeSpeed", 1.0f);
      mat.setBoolean("Animation_Y", true);
//      mat.setBoolean("Animation_X", true);
      mat.setBoolean("Change_Direction", true);
      
      
      mat.setColor("BaseColor", new ColorRGBA(2.0f,1.0f,0.5f,1.0f));
        mat.getAdditionalRenderState().setBlendMode(BlendMode.Additive); 
        mat.getAdditionalRenderState().setFaceCullMode(FaceCullMode.Off);

        
    fire.setQueueBucket(Bucket.Transparent); 

      fire.setMaterial(mat);
      rootNode.attachChild(fire);

      flyCam.setMoveSpeed(5);   
  
  }
  

@Override
    public void simpleUpdate(float tpf){
 

    }
    
    
    }
  


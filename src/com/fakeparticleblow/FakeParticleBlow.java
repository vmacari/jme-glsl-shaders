
package com.fakeparticleblow;

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
public class FakeParticleBlow extends SimpleApplication {

    

    
  public static void main(String[] args) {
    FakeParticleBlow app = new FakeParticleBlow();
    app.start();
  }

    
    
  @Override
  public void simpleInitApp() {

      
      TextureKey skylow = new TextureKey("ShaderBlow/Textures/Water32.dds", true);
        skylow.setGenerateMips(true);
        skylow.setAsCube(true);
      final  Texture texlow = assetManager.loadTexture(skylow);
   
       rootNode.attachChild(SkyFactory.createSky(assetManager, texlow, false));

       
      Spatial fire = assetManager.loadModel("ShaderBlow/Models/FakeParticleBlow/FakeParticleBlow.j3o");
       Material mat = assetManager.loadMaterial("ShaderBlow/Materials/FakeParticleBlow/FakeParticleBlow.j3m");
       mat.getAdditionalRenderState().setDepthTest(true);
       mat.getAdditionalRenderState().setDepthWrite(false);
       fire.setMaterial(mat);
       fire.setQueueBucket(Bucket.Transparent); 
      rootNode.attachChild(fire);

       Spatial fire2 = assetManager.loadModel("ShaderBlow/Models/FakeParticleBlow/FakeParticleBlow.j3o");
       Material mat2 = assetManager.loadMaterial("ShaderBlow/Materials/FakeParticleBlow/FakeParticleBlow_2.j3m");
       mat2.getAdditionalRenderState().setDepthTest(true);
       mat2.getAdditionalRenderState().setDepthWrite(false);       
       fire2.setMaterial(mat2);
       fire2.setQueueBucket(Bucket.Transparent); 
       fire2.setLocalTranslation(2, 0, 0);
       rootNode.attachChild(fire2);      
       


      flyCam.setMoveSpeed(5);   
  
  }
  

@Override
    public void simpleUpdate(float tpf){
 

    }
    
    
    }
  


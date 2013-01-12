
package com.lightblow;


import com.jme3.app.SimpleApplication;
import com.jme3.asset.TextureKey;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import com.jme3.util.TangentBinormalGenerator;


public class LightBlow_LightMap_Dirty extends SimpleApplication {


    
    
  public static void main(String[] args) {
    LightBlow_LightMap_Dirty app = new LightBlow_LightMap_Dirty();
    app.start();
  }
    

    
    
  @Override
  public void simpleInitApp() {

      TextureKey skyhi = new TextureKey("ShaderBlow/Textures/Water256.dds", true);
        skyhi.setGenerateMips(true);
        skyhi.setAsCube(true);
      final  Texture texhi = assetManager.loadTexture(skyhi);

      
//      TextureKey skylow = new TextureKey("Textures/Water32.dds", true);
//        skylow.setGenerateMips(true);
//        skylow.setAsCube(true);
      final  Texture texlow = assetManager.loadTexture(skyhi);
         rootNode.attachChild(SkyFactory.createSky(assetManager, texlow, false));

         
         
    Spatial char_boy = assetManager.loadModel("ShaderBlow/Models/LightBlow/lightmap/lightmap.mesh.xml");
    Material mat = assetManager.loadMaterial("ShaderBlow/Materials/LightBlow/lightmap/lightmap.j3m");
    char_boy.setMaterial(mat);
    TangentBinormalGenerator.generate(char_boy);
    rootNode.attachChild(char_boy);
    
    
    Spatial char_boy2 = assetManager.loadModel("ShaderBlow/Models/LightBlow/lightmap/lightmap.mesh.xml");
    Material mat2 = assetManager.loadMaterial("ShaderBlow/Materials/LightBlow/lightmap/lightmap_dirty.j3m");
    char_boy2.setMaterial(mat2);
    TangentBinormalGenerator.generate(char_boy2);
    char_boy2.move(7f,0f,0f);
    rootNode.attachChild(char_boy2);    

    
        flyCam.setMoveSpeed(25);   
     

        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.8f, -0.6f, -0.08f).normalizeLocal());
        dl.setColor(new ColorRGBA(1,1,1,1));
        rootNode.addLight(dl);
        
        AmbientLight al = new AmbientLight();
        al.setColor(new ColorRGBA(1.0f,1.0f,1.7f,1));
        rootNode.addLight(al);         
    
        
        
  }
  

@Override
    public void simpleUpdate(float tpf){
 

    
    
  
    }
    
    
    }
  


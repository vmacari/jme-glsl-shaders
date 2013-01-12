

package com.lightblow;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Caps;
import com.jme3.scene.Spatial;
import com.jme3.util.TangentBinormalGenerator;


public class LightBlow_Toon_PostEdges extends SimpleApplication {



    
  public static void main(String[] args) {
    LightBlow_Toon_PostEdges app = new LightBlow_Toon_PostEdges();
    app.start();
    
    
  }

  

        public void setupFilters(){

        if (renderer.getCaps().contains(Caps.GLSL100)){
        CartoonEdgeProcessor cartoonEdgeProcess = new CartoonEdgeProcessor();
        viewPort.addProcessor(cartoonEdgeProcess);
        }
    }
    

        
  @Override
  public void simpleInitApp() {

         
       Spatial toon = assetManager.loadModel("ShaderBlow/Models/ToonBlow/toon.obj");
       Material mat = assetManager.loadMaterial("ShaderBlow/Materials/LightBlow/Toon_System/Toon_Base.j3m");
       toon.setMaterial(mat);
       TangentBinormalGenerator.generate(toon);
       rootNode.attachChild(toon);
       
       Spatial toon2 = assetManager.loadModel("ShaderBlow/Models/ToonBlow/toon.obj");
       Material mat2 = assetManager.loadMaterial("ShaderBlow/Materials/LightBlow/Toon_System/Toon_Base_Specular.j3m");
       toon2.setMaterial(mat2);
       TangentBinormalGenerator.generate(toon2);
       toon2.setLocalTranslation(-2f, 0, 0);
       rootNode.attachChild(toon2);
//
//       Spatial toon3 = assetManager.loadModel("Models/ToonBlow/toon.obj");
//       Material mat3 = assetManager.loadMaterial("Materials/LightBlow/Toon_System/Toon_Base.j3m");
//       toon3.setMaterial(mat3);
//       FixedTangentBinormalGenerator.generate(toon3);
//       toon3.setLocalTranslation(-4f, 0, 0);
//       rootNode.attachChild(toon3);       
   
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.8f, -0.6f, -0.08f).normalizeLocal());
        dl.setColor(new ColorRGBA(1,1,1,1));
        rootNode.addLight(dl);
        
        AmbientLight al = new AmbientLight();
        al.setColor(new ColorRGBA(1.5f,1.5f,1.5f,1.0f));
        rootNode.addLight(al);
        flyCam.setMoveSpeed(5);   
     
setupFilters();
        
        
  }
  

@Override
    public void simpleUpdate(float tpf){
 
    viewPort.setBackgroundColor(ColorRGBA.Gray);
    
    
    }
    
    
    }
  


package LightBlow;

import FixedTangentBinormalGenerator.FixedTangentBinormalGenerator;
import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.CartoonEdgeFilter;
import com.jme3.renderer.Caps;
import com.jme3.scene.Spatial;
import com.jme3.util.TangentBinormalGenerator;


public class LightBlow_Toon_PostEdges_JMEFilter extends SimpleApplication {

    Spatial toon;
    FilterPostProcessor fpp;
    
  public static void main(String[] args) {
    LightBlow_Toon_PostEdges_JMEFilter app = new LightBlow_Toon_PostEdges_JMEFilter();
    app.start();
    
    
  }
    private Material mat;


        public void setupFilters(){
        if (renderer.getCaps().contains(Caps.GLSL120)){
            fpp=new FilterPostProcessor(assetManager);
            CartoonEdgeFilter toon=new CartoonEdgeFilter();
            fpp.addFilter(toon);
            viewPort.addProcessor(fpp);
            
                
        }
    }
    

        
  @Override
  public void simpleInitApp() {

         
       Spatial toon = assetManager.loadModel("Models/ToonBlow/toon.obj");
       Material mat = assetManager.loadMaterial("Materials/LightBlow/Toon_System/Toon_Base.j3m");
       toon.setMaterial(mat);
       FixedTangentBinormalGenerator.generate(toon);
       rootNode.attachChild(toon);
       
       Spatial toon2 = assetManager.loadModel("Models/ToonBlow/toon.obj");
       Material mat2 = assetManager.loadMaterial("Materials/LightBlow/Toon_System/Toon_Base_Specular.j3m");
       toon2.setMaterial(mat2);
       FixedTangentBinormalGenerator.generate(toon2);
       toon2.setLocalTranslation(-2f, 0, 0);
       rootNode.attachChild(toon2);

   
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
  


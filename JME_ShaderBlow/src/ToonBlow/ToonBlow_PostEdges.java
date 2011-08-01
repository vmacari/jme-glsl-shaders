

package ToonBlow;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetKey;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Caps;
import com.jme3.scene.Spatial;
import com.jme3.util.TangentBinormalGenerator;


public class ToonBlow_PostEdges extends SimpleApplication {

    Spatial toon;

    
  public static void main(String[] args) {
    ToonBlow_PostEdges app = new ToonBlow_PostEdges();
    app.start();
    
    
  }

  

        public void setupFilters(){

        if (renderer.getCaps().contains(Caps.GLSL120)){
        CartoonEdgeProcessor cartoonEdgeProcess = new CartoonEdgeProcessor();
        viewPort.addProcessor(cartoonEdgeProcess);
        }
    }
    

        
  @Override
  public void simpleInitApp() {

         
       toon = assetManager.loadModel("Models/ToonBlow/toon.obj");
       Material mat = assetManager.loadMaterial("Materials/ToonBlow/ToonBlow_PostEdges.j3m");
       toon.setMaterial(mat);
       TangentBinormalGenerator.generate(toon);
       rootNode.attachChild(toon);

   
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.8f, -0.6f, -0.08f).normalizeLocal());
        dl.setColor(new ColorRGBA(1,1,1,1));
        rootNode.addLight(dl);
 
  

        flyCam.setMoveSpeed(5);   
     
setupFilters();
        
        
  }
  

@Override
    public void simpleUpdate(float tpf){
 
    viewPort.setBackgroundColor(ColorRGBA.Gray);
    
    
    }
    
    
    }
  


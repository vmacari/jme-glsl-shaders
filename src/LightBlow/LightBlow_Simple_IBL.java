package LightBlow;


import FixedTangentBinormalGenerator.FixedTangentBinormalGenerator;
import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.*;
import com.jme3.scene.*;
import com.jme3.scene.Node;
import com.jme3.scene.shape.*;
import com.jme3.util.TangentBinormalGenerator;


public class LightBlow_Simple_IBL extends SimpleApplication {

    Geometry geom_a;
    Material mat_box;
    Node ndmd;
    
    // models
     Spatial obj01;
     Spatial obj02;
     Spatial obj03;
     Spatial ledder;
    
     // collision shapes
     Geometry obj01_l;
     Geometry obj02_l;
     Geometry obj03_l;
     Geometry ledder_l;
     
      
    public static void main(String[] args) {
        LightBlow_Simple_IBL app = new LightBlow_Simple_IBL();
        app.start();
    }

    
    
     public void Models () {
        
         
         
        // Material
        Material mat = assetManager.loadMaterial("Materials/LightBlow/Simple_IBL/Simple_IBL.j3m"); 
        
           
        Mesh sph_test = new Sphere(20, 20, 5);
        Geometry geo_test = new Geometry("geo_test", sph_test);
        geo_test.setMaterial(mat);
        FixedTangentBinormalGenerator.generate(geo_test);
        geo_test.setLocalTranslation(0, 0, -20);
        geo_test.rotate(1.6f, 0, 0);
        rootNode.attachChild(geo_test);
          
        Mesh box = new Box(3, 3, 3);
        Geometry geo_test2 = new Geometry("geo_test2", box);
        geo_test2.setMaterial(mat);
        FixedTangentBinormalGenerator.generate(geo_test2);
        geo_test2.setLocalTranslation(-8, 0, -20);
        geo_test2.rotate(1.6f, 0, 0);
        rootNode.attachChild(geo_test2);
        
        
        }
     
     
    
    @Override
    public void simpleInitApp() {
        
        Models();
             
       
        // Add a light Source
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.8f, -0.6f, -0.08f).normalizeLocal());
        dl.setColor(new ColorRGBA(1.0f,1.0f,1.0f,1));
        rootNode.addLight(dl);
        
        flyCam.setMoveSpeed(40);
        viewPort.setBackgroundColor(ColorRGBA.Gray);

}


    }





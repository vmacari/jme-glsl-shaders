package Displace;


import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;



public class Displace extends SimpleApplication {

    public static void main(String[] args) {
        Displace app = new Displace();
        app.start();
    }

    Geometry geom;     
   
              
    @Override
    public void simpleInitApp() {
        Sphere sph = new Sphere(30, 30, 1);
        geom = new Geometry("Box", sph);
        geom.updateModelBound();

        Material mat = new Material(assetManager, "MatDefs/Displace/Displace.j3md");
        mat.setBoolean("DeformY_Ripple", true);
        mat.setFloat("SpeedX", 5f);
        mat.setFloat("SpeedY", 5f);
        mat.setFloat("SpeedZ", 5f);
        mat.setFloat("SizeX", 2f);
        mat.setFloat("SizeY", 2f);
        mat.setFloat("SizeZ", 2f);

        geom.setMaterial(mat);
        geom.setLocalTranslation(0,2,1);
        rootNode.attachChild(geom);
        
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.8f, -0.6f, -0.08f).normalizeLocal());
        dl.setColor(new ColorRGBA(1,1,1,1));
        rootNode.addLight(dl);        
      
        
        flyCam.setMoveSpeed(30);
        viewPort.setBackgroundColor(ColorRGBA.Gray);   
        
    }

    
     
      
@Override
public void simpleUpdate(float tpf)
{
          
 }

}

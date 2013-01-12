import com.filter.grayscale.GrayScaleFilter;
import java.io.File;
 
import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.HttpZipLocator;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.util.SkyFactory;
import com.jme3.util.TangentBinormalGenerator;
 
public class TestGrayScale extends SimpleApplication {
 
    private FilterPostProcessor fpp;
    private boolean enabled = true;
    private GrayScaleFilter grayScale;
 
    // set default for applets
    private static boolean useHttp = true;
 
    public static void main(final String[] args) {
        final File file = new File("wildhouse.zip");
        if (file.exists()) {
            TestGrayScale.useHttp = false;
        }
        final TestGrayScale app = new TestGrayScale();
        app.start();
    }
 
    @Override
    public void simpleInitApp() {
        this.flyCam.setMoveSpeed(10);
 
        // create the geometry and attach it
        // load the level from zip or http zip

    Spatial char_boy = assetManager.loadModel("ShaderBlow/Models/LightBlow/jme_lightblow.mesh.xml");
    Material mat = assetManager.loadMaterial("ShaderBlow/Materials/LightBlow/Shading_System/LightBlow_ibl.j3m");
    char_boy.setMaterial(mat);
    TangentBinormalGenerator.generate(char_boy);
    rootNode.attachChild(char_boy);        
        
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.8f, -0.6f, -0.08f).normalizeLocal());
        dl.setColor(new ColorRGBA(1, 1, 1, 1));
        rootNode.addLight(dl);

        AmbientLight al = new AmbientLight();
        al.setColor(new ColorRGBA(1.5f, 1.5f, 1.5f, 1.0f));
        rootNode.addLight(al);


        flyCam.setMoveSpeed(15);
 
        this.fpp = new FilterPostProcessor(this.assetManager);
        this.fpp.setNumSamples(4);
        this.grayScale = new GrayScaleFilter();
        this.fpp.addFilter(this.grayScale);
        this.viewPort.addProcessor(this.fpp);
        this.initInputs();
    }
 
    private void initInputs() {
        this.inputManager.addMapping("toggle", new KeyTrigger(KeyInput.KEY_SPACE));
 
        final ActionListener acl = new ActionListener() {
 
            @Override
            public void onAction(final String name, final boolean keyPressed, final float tpf) {
                if (name.equals("toggle") && keyPressed) {
                    if (TestGrayScale.this.enabled) {
                        TestGrayScale.this.enabled = false;
                        TestGrayScale.this.viewPort.removeProcessor(TestGrayScale.this.fpp);
                    } else {
                        TestGrayScale.this.enabled = true;
                        TestGrayScale.this.viewPort.addProcessor(TestGrayScale.this.fpp);
                    }
                }
 
            }
        };
 
        this.inputManager.addListener(acl, "toggle");
 
    }
}
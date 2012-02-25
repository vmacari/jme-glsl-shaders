package SoftParticles;

import com.jme3.app.SimpleApplication;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import java.util.logging.Level;

public class SoftParticles extends SimpleApplication
{
    static SoftParticles app;

    public static void main(String[] args)
    {
        java.util.logging.Logger.getLogger("").setLevel(Level.WARNING);

        app = new SoftParticles();
        app.setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        settings.put("Width", 1024);
        settings.put("Height", 768);
        settings.put("Title", "Test");
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp()
    {
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f).normalizeLocal());
        rootNode.addLight(sun);

        cam.setLocation(new Vector3f(0, 1, 2));
        flyCam.setMoveSpeed(10);


        SoftParticlesProcessor spp = new SoftParticlesProcessor(assetManager, settings.getWidth(), settings.getHeight());
        viewPort.addProcessor(spp);


        //------------
        ParticleEmitter fire = new ParticleEmitter("Emitter", ParticleMesh.Type.Triangle, 30);

        Material material;
        if (true) // if float textures supported, use soft particles  //TODO check
        {
            material = new Material(assetManager, "MatDefs/SoftParticles/SoftParticle.j3md");
            material.setTexture("DepthTex", spp.GetTexture());
            material.setFloat("Power", 1.0f); // 0=hard edges, higher->smoother
        } else
        {
            material = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        }

//        material.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/flame.png"));
        fire.setMaterial(material);
//        fire.setImagesX(2);
//        fire.setImagesY(2); // 2x2 texture animation
//        fire.setParticlesPerSec(10);
        fire.setEndColor(new ColorRGBA(1f, 0f, 0f, 1f)); // red
        fire.setStartColor(new ColorRGBA(1f, 1f, 0f, 0.5f)); // yellow
        fire.setStartSize(0.5f);
        fire.setEndSize(1.5f);
        fire.setGravity(0.5f, -0.05f, 0);
        fire.setLowLife(0.9f);
        fire.setHighLife(20f);
        fire.setLocalTranslation(0, 0.1f, 0);
        rootNode.attachChild(fire);


        // -------- floor
        Box b = new Box(Vector3f.ZERO, 10, 0.1f, 10);
        Geometry geom = new Geometry("Box", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setColor("Color", ColorRGBA.Blue);
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain_Textures/pattern_69/pattern_69_diffus.png"));
        geom.setMaterial(mat);
        rootNode.attachChild(geom);


        //debug
        /*
        Picture p = new Picture("Picture");
        p.setPosition(0, settings.getHeight() - settings.getHeight() / 2);
        p.setWidth(settings.getWidth() / 2);
        p.setHeight(settings.getHeight() / 2);
        p.setTexture(assetManager, spp.GetTexture(), false);
        rootNode.attachChild(p);
         */
    }

    @Override
    public void simpleUpdate(float tpf)
    {
    }

    @Override
    public void simpleRender(RenderManager rm)
    {
    }
}

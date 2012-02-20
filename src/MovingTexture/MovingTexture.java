package MovingTexture;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Sphere.TextureMode;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.MagFilter;
import com.jme3.texture.Texture.MinFilter;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.util.TangentBinormalGenerator;

/**
 * test
 * @author normenhansen
 */
public class MovingTexture extends SimpleApplication {
	Quaternion q = new Quaternion();
	Texture tex_Lava, tex_Clouds;
	Material mat_Lava, mat_Clouds;
	
	float lavaSpeed = 0.3f;
	float lavaDirection = FastMath.HALF_PI+0.25f;
	float lavaIncX = 0, lavaIncY = 0;
	
	float cloudsSpeed = 0.3f;
	float cloudsDirection = FastMath.HALF_PI+0.25f;
	float cloudsIncX = 0, cloudsIncY = 0;
	float cloudsAlpha = 0;
	boolean cloudsSwitch = false;
	
    public static void main(String[] args) {
        MovingTexture app = new MovingTexture();
        
        //set vSinc on to get stable 60 fps
        AppSettings aps = new AppSettings(true);
        aps.setVSync(true);
        app.setSettings(aps);
        app.start();
    }

    @Override
    public void simpleInitApp() {
    	// Lava
		Box b = new Box(Vector3f.ZERO, 1, 1, 1);
        Geometry geom = new Geometry("Box", b);
		geom.setLocalRotation(q.set(0.12f,0.12f,0.12f,0f));
        
		tex_Lava = assetManager.loadTexture("Textures/MovingTexture/Lava1.jpg");
		tex_Lava.setMinFilter(MinFilter.NearestNoMipMaps);
		tex_Lava.setMagFilter(MagFilter.Nearest);
		tex_Lava.setWrap(WrapMode.Repeat);
		
		mat_Lava = new Material(assetManager, "MatDefs/MovingTexture/MovingTexture.j3md");
		mat_Lava.setTexture("ColorMap", tex_Lava);
		mat_Lava.setVector2("Offset", Vector2f.ZERO);
		mat_Lava.setFloat("Alpha", 1f);
		mat_Lava.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		
        geom.setMaterial(mat_Lava);
        rootNode.attachChild(geom);
		
		// Clouds
		Box b2 = new Box(Vector3f.ZERO, 1.2f, 1.2f, 1.2f);
        Geometry geom2 = new Geometry("Box2", b2);
		geom2.setLocalRotation(q.set(0.12f,0.12f,0.12f,0f));
		geom2.setQueueBucket(Bucket.Transparent);
		
		tex_Clouds = assetManager.loadTexture("Textures/MovingTexture/Clouds1.png");
		tex_Clouds.setMinFilter(MinFilter.NearestNoMipMaps);
		tex_Clouds.setMagFilter(MagFilter.Nearest);
		tex_Clouds.setWrap(WrapMode.Repeat);
		
		mat_Clouds = new Material(assetManager, "MatDefs/MovingTexture/MovingTexture.j3md");
		mat_Clouds.setTexture("ColorMap", tex_Clouds);
		mat_Clouds.setVector2("Offset", Vector2f.ZERO);
		mat_Clouds.setFloat("Alpha", 1f);
		mat_Clouds.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		
        geom2.setMaterial(mat_Clouds);
		rootNode.attachChild(geom2);
                
        viewPort.setBackgroundColor(ColorRGBA.Gray);
        flyCam.setMoveSpeed(30);                
                
    }

    @Override
    public void simpleUpdate(float tpf) {
        // Update Lava
		lavaIncX += ((lavaSpeed*tpf)*FastMath.sin(lavaDirection));
		lavaIncY += ((lavaSpeed*tpf)*FastMath.cos(lavaDirection));
		if (lavaIncX > 1) lavaIncX -= 1;
		if (lavaIncX < 1) lavaIncX += 1;
		if (lavaIncY > 1) lavaIncY -= 1;
		if (lavaIncY < 1) lavaIncY += 1;
		
		//Update Clouds
		if (cloudsSwitch) {
			cloudsAlpha += .01f;
			if (cloudsAlpha > 1) {
				cloudsSwitch = false;
				cloudsSpeed = ((float)Math.random()*.7f)+0.3f;
				cloudsDirection = ((float)Math.random()*FastMath.PI)-FastMath.HALF_PI;
			}
		} else {
			cloudsAlpha -= .01f;
			if (cloudsAlpha < 0) cloudsSwitch = true;
		}
		cloudsIncX += ((cloudsSpeed*tpf)*FastMath.sin(cloudsDirection));
		cloudsIncY += ((cloudsSpeed*tpf)*FastMath.cos(cloudsDirection));
		if (cloudsIncX > 1) cloudsIncX -= 1;
		if (cloudsIncX < 1) cloudsIncX += 1;
		if (cloudsIncY > 1) cloudsIncY -= 1;
		if (cloudsIncY < 1) cloudsIncY += 1;
		
		mat_Lava.setVector2("Offset", new Vector2f(lavaIncX,lavaIncY));
		mat_Clouds.setVector2("Offset", new Vector2f(cloudsIncX,cloudsIncY));
		mat_Clouds.setFloat("Alpha", cloudsAlpha);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}

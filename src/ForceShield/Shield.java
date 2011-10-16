package ForceShield;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapText;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;

public class Shield extends SimpleApplication implements ActionListener {
	Material material;
	ForceShieldControl forceShieldControl;
	
	public static void main(String[] args) {
		new Shield().start();
	}
	
	@Override
	public void simpleInitApp() {
		initCrossHairs();
		
		DirectionalLight sun = new DirectionalLight();
		sun.setDirection(new Vector3f(1,-1,0));
		rootNode.addLight(sun);

                Box box = new Box(1, 1, 1);
                Geometry cube = new Geometry("cube", box);
                cube.setLocalScale(0.5f,0.5f,0.5f);
                Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                mat1.setColor("m_Color", ColorRGBA.randomColor());
                cube.setMaterial(mat1);
                rootNode.attachChild(cube);

                
                Sphere sphere = new Sphere(30, 30, 1.2f);
                Geometry shield = new Geometry("the Floor", sphere);
                Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                mat2.setColor("m_Color", new ColorRGBA(1.0f,0.0f,0.0f,0.3f));
                shield.setMaterial(mat2);
                mat1.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
                shield.setQueueBucket(Bucket.Transparent);
		
                		
		forceShieldControl = new ForceShieldControl(assetManager, 0.5f);
		shield.addControl(forceShieldControl);
		forceShieldControl.setEffectSize(1f);
		forceShieldControl.setColor(new ColorRGBA(0, 0, 1, 3));
		forceShieldControl.setTexture(assetManager.loadTexture("Textures/ForceShield/fs_texture.png"));
		forceShieldControl.setVisibility(0.1f);
		
		rootNode.attachChild(shield);
		
		flyCam.setMoveSpeed(10);
		
		inputManager.addMapping("FIRE", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
		inputManager.addListener(this, "FIRE");
                
                viewPort.setBackgroundColor(ColorRGBA.Gray);
	}

	@Override
	public void onAction(String name, boolean isPressed, float arg) {
		if (name.equals("FIRE") && isPressed){
			CollisionResults crs = new CollisionResults();
			rootNode.collideWith(new Ray(cam.getLocation(), cam.getDirection()), crs);
			if (crs.getClosestCollision() != null){
				System.out.println("Hit at "+crs.getClosestCollision().getContactPoint());
				forceShieldControl.registerHit(crs.getClosestCollision().getContactPoint());
			}
		}
	}
	
	  protected void initCrossHairs() {
		    guiNode.detachAllChildren();
		    guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
		    BitmapText ch = new BitmapText(guiFont, false);
		    ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
		    ch.setText("+"); // crosshairs
		    ch.setLocalTranslation( // center
		      settings.getWidth()/2 - guiFont.getCharSet().getRenderedSize()/3*2,
		      settings.getHeight()/2 + ch.getLineHeight()/2, 0);
		    guiNode.attachChild(ch);
		  }

}

package ForceShield;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;


public class ForceShieldTest extends SimpleApplication {

  public static void main(String[] args) {
    ForceShieldTest app = new ForceShieldTest();

        //set vSinc on to get stable 60 fps
        AppSettings aps = new AppSettings(true);
        aps.setVSync(true);
        app.setSettings(aps);
    
    app.start();
  }
  
  Node cubechar;
  Node shootables;
  Geometry mark;
  
  Boolean shoot = false;
  Vector3f vectry;   
  Vector3f vectry2; 
  Vector3f vecmove;
  float vecdist2;
  float   move;
  float remainingDist;
  ForceShieldControl fsc;    
  boolean eff;
  
  @Override
  public void simpleInitApp() {
    initCrossHairs(); // a "+" in the middle of the screen to help aiming
    initKeys();       // load custom key mappings
    initMark();       // a red sphere to mark the hit

   Spatial shield1 = makeShield();



    Texture tx = assetManager.loadTexture("Textures/ForceShield/fs_texture.png");  
   
    /** create four colored boxes and a floor to shoot at: */
    shootables = new Node("Shootables");
    rootNode.attachChild(shootables);
    shootables.attachChild(shield1);
   
    fsc = new ForceShieldControl(assetManager);
    fsc.setSpatial(shield1);
    fsc.setEffectSize(1.2f);
    fsc.setColor( new ColorRGBA(1.0f,0.2f,0.2f,0.9f));
    fsc.setVisibility(0.1f);
    fsc.getMaterial();
    fsc.setTexture(tx);
    fsc.setEnabled(true);

    
    
    
    cubechar = new Node();
    cubechar.attachChild(makeCube("Character", 0, 0.5f, 0));
    rootNode.attachChild(cubechar);
//    cubechar.setLocalTranslation(0,-3.8f,0);
    
    flyCam.setMoveSpeed(30);
//    cam.setLocation(new Vector3f(0.0f, 35.0f, 10.0f));
//    cam.lookAtDirection(new Vector3f(-0.009993265f, -0.9400059f, -0.34101164f), Vector3f.UNIT_Y);
    
    viewPort.setBackgroundColor(ColorRGBA.Gray);   
    
  }

  /** Declaring the "Shoot" action and mapping to its triggers. */
  public void initKeys() {
    inputManager.addMapping("Shoot", new KeyTrigger(KeyInput.KEY_SPACE), // trigger 1: spacebar
      new MouseButtonTrigger(MouseInput.BUTTON_LEFT)); // trigger 2: left-button click
    inputManager.addListener(actionListener, "Shoot");
  }
  /** Defining the "Shoot" action: Determine what was hit and how to respond. */
  public ActionListener actionListener = new ActionListener() {

    public void onAction(String name, boolean keyPressed, float tpf) {
      if (name.equals("Shoot") && !keyPressed) {
        // 1. Reset results list.
        CollisionResults results = new CollisionResults();
        // 2. Aim the ray from cam loc to cam direction.
        Ray ray = new Ray(cam.getLocation(), cam.getDirection());
        // 3. Collect intersections between Ray and Shootables in results list.
        shootables.collideWith(ray, results);
        // 4. Print the results
        System.out.println("----- Collisions? " + results.size() + "-----");
        for (int i = 0; i < results.size(); i++) {
          // For each hit, we know distance, impact point, name of geometry.
          float dist = results.getCollision(i).getDistance();
          Vector3f pt = results.getCollision(i).getContactPoint();
          String hit = results.getCollision(i).getGeometry().getName();
          System.out.println("* Collision #" + i);
          System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
        }
        
        if (results.size() > 0) {
          // The closest collision point is what was truly hit:
          CollisionResult closest = results.getClosestCollision();
          
         // fsc.registerHit(closest.getContactPoint());
          
          
          
          mark.setLocalTranslation(closest.getContactPoint());
          
         fsc.registerHit(mark.getWorldTranslation()); 
          
       shoot = true;
       
        } 
          
      }
       
    }
  };

  
  protected Geometry makeCube(String name, float x, float y, float z) {
    Box box = new Box(new Vector3f(x, y, z), 1, 1, 1);
    Geometry cube = new Geometry(name, box);
    cube.setLocalScale(0.5f,0.5f,0.5f);
    Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    mat1.setColor("m_Color", ColorRGBA.randomColor());
    cube.setMaterial(mat1);
    return cube;
  }

  
  protected Geometry makeShield() {
    Sphere sphere = new Sphere(30, 30, 1.2f);
    Geometry shield = new Geometry("the Floor", sphere);
    Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    mat1.setColor("m_Color", new ColorRGBA(1.0f,0.0f,0.0f,0.3f));
    shield.setMaterial(mat1);
    mat1.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
    shield.setQueueBucket(Bucket.Transparent);
    return shield;
  }

  
  protected void initMark() {
    Sphere sphere = new Sphere(30, 30, 0.05f);
    mark = new Geometry("BOOM!", sphere);
    Material mark_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    mark_mat.setColor("m_Color", ColorRGBA.Red);
    mark.setMaterial(mark_mat);
    mark.setLocalTranslation(0, -3.8f, 0);
    rootNode.attachChild(mark);
  }


  /** A centred plus sign to help the player aim. */
  protected void initCrossHairs() {
    guiNode.detachAllChildren();
    guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
    BitmapText ch = new BitmapText(guiFont, false);
    ch.setSize(guiFont.getCharSet().getRenderedSize()*1.5f);
    ch.setText("+"); // crosshairs
    ch.setLocalTranslation( // center
      settings.getWidth() / 2 - guiFont.getCharSet().getRenderedSize() / 3 * 2,
      settings.getHeight() / 2 + ch.getLineHeight() / 2, 0);
    guiNode.attachChild(ch);
  }

        
        @Override
public void simpleUpdate(float tpf)
{
                  
          fsc.updateCollisionAlpha();
          fsc.updateCollisionPoints();
       

}

}

package com.dissolve;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.*;
import com.jme3.light.*;
import com.jme3.math.*;
import com.jme3.texture.*;

/**
 * 
 * @author thetoucher
 */

public class Dissolve extends SimpleApplication {

    // speed of animation
    private float speed = .125f;
    
    private float count = 0;
    private int dir = 1;

    private Vector2f DSParams, DSParamsInv, DSParamsBurn;
    
    public static void main(String[] args) {
        Dissolve app = new Dissolve();
        app.start();
    }
    

    @Override
    public void simpleInitApp() {
        
        Texture t;
        Material mat;
        
        cam.setLocation(new Vector3f(0,1.5f,10f));
       // flyCam.setEnabled(false);
        
        
        // reusable params
        DSParams = new Vector2f(0,0); // standard
        DSParamsInv = new Vector2f(0,1); // inverted
        DSParamsBurn = new Vector2f(0,0); // used for offset organic burn map
        
        
        
        // linear dissolve               
        addTestCube(-3f,3f,assetManager.loadTexture("ShaderBlow/Textures/Dissolve/linear.png"), DSParams);

        // organic dissolve   
        addTestCube(0,3f,assetManager.loadTexture("ShaderBlow/Textures/Dissolve/burnMap.png"), DSParamsInv);
        
        // pixel dissolve        
        t = assetManager.loadTexture("ShaderBlow/Textures/Dissolve/pixelMap.png");
        t.setMagFilter(Texture.MagFilter.Nearest); // this is needed to retain the crisp pixelated look
        addTestCube(3f, 3f, t, DSParams);        
               
        // organic growth
        mat = addTestCube(-3f,0,assetManager.loadTexture("ShaderBlow/Textures/Dissolve/growMap.png"), DSParamsInv).getMaterial();
        mat.setColor("Ambient", ColorRGBA.Green);
        mat.setTexture("DiffuseMap", assetManager.loadTexture("ShaderBlow/Textures/Dissolve/growMap.png"));

        addTestCube(-3f,0,assetManager.loadTexture("ShaderBlow/Textures/Dissolve/growMap.png"), DSParams);
    
        // texture mask
        mat = addTestCube(0,0,assetManager.loadTexture("ShaderBlow/Textures/Dissolve/streetBurn.png"), DSParams).getMaterial();
        mat.setTexture("DiffuseMap", assetManager.loadTexture("ShaderBlow/Textures/Dissolve/streetClean.png"));
        mat.setColor("Ambient",  ColorRGBA.White);
               
        mat = addTestCube(0f,0f,assetManager.loadTexture("ShaderBlow/Textures/Dissolve/streetBurn.png"), DSParamsInv).getMaterial();
        mat.setTexture("DiffuseMap", assetManager.loadTexture("ShaderBlow/Textures/Dissolve/street.png"));
        mat.setColor("Ambient",  ColorRGBA.White);

        // organic burn
        addTestCube(3f, 0, assetManager.loadTexture("ShaderBlow/Textures/Dissolve/burnMap.png"), DSParamsBurn).getMaterial().setColor("Ambient",  ColorRGBA.Red);
        addTestCube(3f, 0, assetManager.loadTexture("ShaderBlow/Textures/Dissolve/burnMap.png"), DSParams);


        AmbientLight a = new AmbientLight();
        a.setColor(ColorRGBA.White);
        rootNode.addLight(a);
        
        viewPort.setBackgroundColor(ColorRGBA.Gray);
        flyCam.setMoveSpeed(5);   
       
    }

    private Geometry addTestCube(float xPos, float yPos, Texture map, Vector2f DSParams) {
        Box b = new Box(Vector3f.ZERO, 1, 1, 1);
        Geometry geom = new Geometry("Box", b);
        geom.setLocalTranslation(new Vector3f(xPos,yPos,0));

        Material mat = new Material(assetManager, "ShaderBlow/MatDefs/Dissolve/Lighting.j3md");
        mat.setColor("Ambient",  ColorRGBA.Blue);
        mat.setColor("Diffuse",  ColorRGBA.White);
        mat.setColor("Specular", ColorRGBA.Black);
        mat.setBoolean("UseMaterialColors", true);
        mat.setTexture("DissolveMap", map);
        mat.setVector2("DissolveParams", DSParams);
        
        geom.setMaterial(mat);
        rootNode.attachChild(geom);
        
        return geom;
    }
    
    
    @Override
    public void simpleUpdate(float tpf) {
       
       count+=tpf*speed*dir;
       
       // animation ossolation
       if (count > 1f) {
           dir = -1;
       } else if (count < 0) {
           dir = 1;
       }
       
       // update the dissolve amounts
       DSParams.setX(count);
       DSParamsInv.setX(count);
       DSParamsBurn.setX(count-.05f);
     }
       
}

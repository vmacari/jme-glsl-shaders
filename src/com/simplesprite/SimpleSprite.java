package com.simplesprite;

import MatCap.*;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;
import com.jme3.util.TangentBinormalGenerator;


public class SimpleSprite extends SimpleApplication {

    
  public static void main(String[] args) {
    SimpleSprite app = new SimpleSprite();
    app.start();
  }

  
    
    
  @Override
  public void simpleInitApp() {

    Spatial char_boy1 = assetManager.loadModel("Models/SimpleSprite/SimpleSprite.blend");
    Material mat1 = assetManager.loadMaterial("Materials/SimpleSprite/SimpleSprite_1.j3m");
    char_boy1.setMaterial(mat1);
    char_boy1.setLocalTranslation(0,0,0);
    TangentBinormalGenerator.generate(char_boy1);
    rootNode.attachChild(char_boy1);

    Spatial char_boy2 = assetManager.loadModel("Models/SimpleSprite/SimpleSprite.blend");
    Material mat2 = assetManager.loadMaterial("Materials/SimpleSprite/SimpleSprite_2.j3m");
    char_boy2.setMaterial(mat2);
    char_boy2.setLocalTranslation(1,0,0);
    char_boy2.setLocalScale(0.5f, 1, 1);    
    TangentBinormalGenerator.generate(char_boy2);
    rootNode.attachChild(char_boy2);    

//        Spatial char_boy3 = assetManager.loadModel("Models/LightBlow/jme_lightblow.obj");
//    Material mat3 = assetManager.loadMaterial("Materials/MatCap/MatCap2.j3m");
//    char_boy3.setMaterial(mat3);
//    char_boy3.setLocalTranslation(-1,0,0);
//    TangentBinormalGenerator.generate(char_boy3);
//    rootNode.attachChild(char_boy3);
//
//    Spatial char_boy4 = assetManager.loadModel("Models/LightBlow/jme_lightblow.obj");
//    Material mat4 = assetManager.loadMaterial("Materials/MatCap/MatCapBump2.j3m");
//    char_boy4.setMaterial(mat4);
//    char_boy4.setLocalTranslation(-2,0,0);
//    TangentBinormalGenerator.generate(char_boy4);
//    rootNode.attachChild(char_boy4);    


      flyCam.setMoveSpeed(10);   
      viewPort.setBackgroundColor(ColorRGBA.Gray);      
  
  }
  

@Override
    public void simpleUpdate(float tpf){
 

    }
    
    
    }
  


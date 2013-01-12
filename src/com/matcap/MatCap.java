package com.matcap;


import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;
import com.jme3.util.TangentBinormalGenerator;


public class MatCap extends SimpleApplication {

    Spatial fire;

    
  public static void main(String[] args) {
    MatCap app = new MatCap();
    app.start();
  }

  
    
    
  @Override
  public void simpleInitApp() {

    Spatial char_boy1 = assetManager.loadModel("ShaderBlow/Models/LightBlow/jme_lightblow.mesh.xml");
    Material mat1 = assetManager.loadMaterial("ShaderBlow/Materials/MatCap/MatCap1.j3m");
    char_boy1.setMaterial(mat1);
    char_boy1.setLocalTranslation(0,0,0);
    TangentBinormalGenerator.generate(char_boy1);
    rootNode.attachChild(char_boy1);

    Spatial char_boy2 = assetManager.loadModel("ShaderBlow/Models/LightBlow/jme_lightblow.mesh.xml");
    Material mat2 = assetManager.loadMaterial("ShaderBlow/Materials/MatCap/MatCapBump1.j3m");
    char_boy2.setMaterial(mat2);
    char_boy2.setLocalTranslation(1,0,0);
    TangentBinormalGenerator.generate(char_boy2);
    rootNode.attachChild(char_boy2);    

        Spatial char_boy3 = assetManager.loadModel("ShaderBlow/Models/LightBlow/jme_lightblow.mesh.xml");
    Material mat3 = assetManager.loadMaterial("ShaderBlow/Materials/MatCap/MatCap2.j3m");
    char_boy3.setMaterial(mat3);
    char_boy3.setLocalTranslation(-1,0,0);
    TangentBinormalGenerator.generate(char_boy3);
    rootNode.attachChild(char_boy3);

    Spatial char_boy4 = assetManager.loadModel("ShaderBlow/Models/LightBlow/jme_lightblow.mesh.xml");
    Material mat4 = assetManager.loadMaterial("ShaderBlow/Materials/MatCap/MatCapBump2.j3m");
    char_boy4.setMaterial(mat4);
    char_boy4.setLocalTranslation(-2,0,0);
    TangentBinormalGenerator.generate(char_boy4);
    rootNode.attachChild(char_boy4);    


      flyCam.setMoveSpeed(10);   
      viewPort.setBackgroundColor(ColorRGBA.Gray);      
  
  }
  

@Override
    public void simpleUpdate(float tpf){
 

    }
    
    
    }
  


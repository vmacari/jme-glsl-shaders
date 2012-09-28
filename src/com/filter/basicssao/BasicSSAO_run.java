package com.filter.basicssao;

import com.lightblow.*;
import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.CartoonEdgeFilter;
import com.jme3.renderer.Caps;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.util.TangentBinormalGenerator;

public class BasicSSAO_run extends SimpleApplication {


    FilterPostProcessor fpp;

    public static void main(String[] args) {
        BasicSSAO_run app = new BasicSSAO_run();
        app.start();


    }
    private Material mat;

    public void setupFilters() {
        fpp = new FilterPostProcessor(assetManager);

//        BasicSSAO ssao = new BasicSSAO();
//        ssao.scaleSettings(0.25f); // or whatever works for your model scale        


        // In vars: reflection-radius, intensity, scale, bias
        BasicSSAO ssao = new BasicSSAO(0.15f, 5.5f, 0.5f, 0.025f);
        // Add in detail pass - this doubles the number of samples taken and halves performance.  But, allows for smoothing artifacting while keeping detail
        ssao.setUseDetailPass(true);
        // Add distance falloff and set distance/rate of falloff
        ssao.setUseDistanceFalloff(true);
        ssao.setFalloffStartDistance(50f);
        ssao.setFalloffRate(4.0f);

        fpp.addFilter(ssao);
        viewPort.addProcessor(fpp);

    }

    @Override
    public void simpleInitApp() {


        Spatial char_boy = assetManager.loadModel("Models/LightBlow/jme_lightblow.mesh.xml");
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        char_boy.setMaterial(mat);
        TangentBinormalGenerator.generate(char_boy);
        rootNode.attachChild(char_boy);


        Box b = new Box(Vector3f.ZERO, 1, 1, 1);
        Geometry geom = new Geometry("Box", b);
        geom.setMaterial(mat);
        geom.setLocalTranslation(0, 2, 1);
        rootNode.attachChild(geom);

        Spatial char_boy2 = assetManager.loadModel("Models/LightBlow/jme_lightblow.mesh.xml");
        Material mat2 = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        char_boy2.setMaterial(mat2);
        char_boy2.setLocalTranslation(-2f, 0, 0);
        TangentBinormalGenerator.generate(char_boy2);
        rootNode.attachChild(char_boy2);


        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.8f, -0.6f, -0.08f).normalizeLocal());
        dl.setColor(new ColorRGBA(1, 1, 1, 1));
        rootNode.addLight(dl);

        AmbientLight al = new AmbientLight();
        al.setColor(new ColorRGBA(1.5f, 1.5f, 1.5f, 1.0f));
        rootNode.addLight(al);


        flyCam.setMoveSpeed(15);

        setupFilters();


    }

    @Override
    public void simpleUpdate(float tpf) {
        viewPort.setBackgroundColor(ColorRGBA.Gray);



    }
}

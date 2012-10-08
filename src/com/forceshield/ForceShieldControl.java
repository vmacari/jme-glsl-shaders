package com.forceshield;
import java.io.IOException;
import java.util.ArrayList;

import com.jme3.asset.AssetManager;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.shader.VarType;
import com.jme3.texture.Texture;
/**
 * Force shield effect. It sets material to controlled object.
 * If you experience problems, try higher polygon object.
 * @author Stanislav Fifik 
 */
public class ForceShieldControl implements Control {

	private Material material;
	private float maxTime = 0.5f;
	private ArrayList<Vector3f> collisions = new ArrayList<Vector3f>();
	private ArrayList<Float> collisionTimes = new ArrayList<Float>(); 
	private Spatial model;
	private boolean numChanged = false;
	private boolean enabled = true;
        private boolean work = false;
        private float timer = 0;
        private float timerSize;
	/**
	 * Max number of hits displayed
	 * I've experienced crashes with 7 or 8 hits 
	 */
	private final int MAX_HITS = 4;
	

	public ForceShieldControl(AssetManager assetManager){
		material = new Material(assetManager,"MatDefs/ForceShield/ForceShield.j3md");
		material.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
		material.setFloat("MaxDistance", 1);
                timerSize = 4f;
	}
	
	/**
	 * 
	 * @param assetManager
	 * @param duration - effect duration (Default is 0.5s)
	 */
	public ForceShieldControl(AssetManager assetManager, float duration) {
		this(assetManager);
		this.maxTime = duration;
	}
	
	@Override
	public void update(float tpf) {
            if (work && enabled) {
                
//                System.out.println(timer);
                
                if (timer > timerSize) {
                    timer = 0f;
                    work = false;
                    return;
                }
                
                timer += tpf * 3f;
                
		for (int i = 0; i < collisionTimes.size(); i++) {
			float time = collisionTimes.get(i);
			time -= tpf;
			if (time <= 0){
				collisionTimes.remove(i);
				collisions.remove(i--);
				numChanged = true;
				continue;
			}
			collisionTimes.set(i, time);
		}
		if (numChanged)
			updateCollisionPoints();
                        updateCollisionAlpha();
		
		numChanged = false;
                
            }
	}
	
	/**
	 * Adds hit to display.
	 * @param position - world space position
	 */
	public void registerHit(Vector3f position){
		if (!enabled)
			return;
            
                timer = 0f;
                work = true;
		Vector3f lposition = new Vector3f();
		model.worldToLocal(position, lposition);
		collisions.add(new Vector3f(lposition.x, lposition.y, lposition.z));
		collisionTimes.add(maxTime);
		numChanged = true;
		updateCollisionPoints();
	}
	
	
	/**
	 * Color of the shield
	 * @param color
	 */
	public void setColor(ColorRGBA color){
		material.setColor("Color", color);
	}
	
	/**
	 * Visibility of inactive shield
	 * @param percent
	 */
	public void setVisibility(float percent){
		material.setFloat("MinAlpha", percent);
	}
	
	/**
	 * Shield texture
	 * @param texture
	 */
	public void setTexture(Texture texture){
		material.setTexture("ColorMap", texture);
	}
	
	/**
	 * material displaying effect
	 */
	public Material getMaterial(){
		return material;
	}
	
	/**
	 * Maximum distance from contact point where effect is visible 
	 * Has to be set after setting control to object!
	 * @param size
	 */
	public void setEffectSize(float size){
		material.setFloat("MaxDistance", size/model.getLocalScale().x);
	}
	
	protected void updateCollisionAlpha(){
		float[] alphas = new float[Math.min(collisionTimes.size(),MAX_HITS)];
		for (int i = 0; i < alphas.length && i<MAX_HITS; i++) {
			alphas[i] = collisionTimes.get(collisions.size()-1-i)/maxTime;
		}
		material.setParam("CollisionAlphas", VarType.FloatArray, alphas);
	}
	
	protected void updateCollisionPoints(){
		Vector3f[] collisionsArray = new Vector3f[Math.min(collisions.size(),MAX_HITS)];
		for (int i = 0; i < collisions.size() && i < MAX_HITS; i++) {
			collisionsArray[i] = collisions.get(collisions.size()-1-i);
		}
		material.setParam("Collisions", VarType.Vector3Array, collisionsArray);
		material.setInt("CollisionNum",Math.min(collisions.size(),MAX_HITS));
	}
	
	@Override
	public void read(JmeImporter arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(JmeExporter arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Control cloneForSpatial(Spatial spatial) {
		return null;
	}

	
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void render(RenderManager arg0, ViewPort arg1) {
		
	}

	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public void setSpatial(Spatial model) {
		this.model = model;
		model.setMaterial(material);
		model.setQueueBucket(Bucket.Transparent);
		
	}

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filter.grayscale;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.post.Filter;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
 
public class GrayScaleFilter extends Filter {
 
    @Override
    protected Material getMaterial() {
        return this.material;
    }
 
    @Override
    protected void initFilter(final AssetManager manager, final RenderManager renderManager, final ViewPort vp,
            final int w, final int h) {
        this.material = new Material(manager, "ShaderBlow/MatDefs/Filters/GrayScale/GrayScale.j3md");
    }
 
}

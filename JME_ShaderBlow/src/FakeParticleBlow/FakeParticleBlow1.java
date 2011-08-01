/*
 * Copyright (c) 2009-2010 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package FakeParticleBlow;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetKey;
import com.jme3.asset.TextureKey;
import com.jme3.material.Material;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;

/** Sample 6 - how to give an object's surface a material and texture.
 * How to make objects transparent, or let colors "leak" through partially
 * transparent textures. How to make bumpy and shiny surfaces.  */
public class FakeParticleBlow1 extends SimpleApplication {

    Spatial fire;

    
  public static void main(String[] args) {
    FakeParticleBlow1 app = new FakeParticleBlow1();
    app.start();
  }

    
    
  @Override
  public void simpleInitApp() {

//      TextureKey skyhi = new TextureKey("Textures/Water256.dds", true);
//        skyhi.setGenerateMips(true);
//        skyhi.setAsCube(true);
//      final  Texture texhi = assetManager.loadTexture(skyhi);
  
      
      TextureKey skylow = new TextureKey("Textures/Water32.dds", true);
        skylow.setGenerateMips(true);
        skylow.setAsCube(true);
      final  Texture texlow = assetManager.loadTexture(skylow);
   
          rootNode.attachChild(SkyFactory.createSky(assetManager, texlow, false));
      
       fire = assetManager.loadModel("Models/FakeParticleBlow/FakeParticleBlow.j3o");
       fire.setMaterial( (Material) assetManager.loadAsset(new AssetKey("Materials/FakeParticleBlow/FakeParticleBlow.j3m")));


        
    fire.setQueueBucket(Bucket.Transparent); 


      rootNode.attachChild(fire);

      flyCam.setMoveSpeed(5);   
  
  }
  

@Override
    public void simpleUpdate(float tpf){
 

    }
    
    
    }
  


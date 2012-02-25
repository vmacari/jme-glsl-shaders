package SoftParticles;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.post.SceneProcessor;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.Renderer;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.texture.FrameBuffer;
import com.jme3.texture.Image.Format;
import com.jme3.texture.Texture2D;

public class SoftParticlesProcessor implements SceneProcessor
{
    private RenderManager renderManager;
    private ViewPort viewPort;
    private Material material;
    private FrameBuffer fb;
    Texture2D fbTex;

    public SoftParticlesProcessor(AssetManager manager, int w, int h)
    {
        //setup framebuffer
        fb = new FrameBuffer(w, h, 1);

        fbTex = new Texture2D(w, h, Format.RGB32F); // float texture needed!
        fb.setDepthBuffer(Format.Depth);
        fb.setColorTexture(fbTex);
        material  = new Material(manager, "MatDefs/SoftParticles/SoftParticlesDepthW.j3md");
    }

    public void initialize(RenderManager rm, ViewPort vp)
    {
        renderManager = rm;
        viewPort = vp;
        reshape(vp, vp.getCamera().getWidth(), vp.getCamera().getHeight());
    }

    public Texture2D GetTexture()
    {
        return fbTex;
    }

    public boolean isInitialized()
    {
        return viewPort != null;
    }

    public void postQueue(RenderQueue rq)
    {
        Renderer r = renderManager.getRenderer();
        r.setFrameBuffer(fb);
        r.clearBuffers(true, true, false);
        renderManager.setForcedMaterial(material);
        rq.renderQueue(RenderQueue.Bucket.Opaque, renderManager, viewPort.getCamera(), false);
        renderManager.setForcedMaterial(null);
        renderManager.getRenderer().setFrameBuffer(null);
    }

    public void postFrame(FrameBuffer out)
    {
    }

    public void preFrame(float tpf)
    {
    }

    public void cleanup()
    {
    }

    public void reshape(ViewPort vp, int w, int h)
    {
        this.viewPort = vp;
    }
}

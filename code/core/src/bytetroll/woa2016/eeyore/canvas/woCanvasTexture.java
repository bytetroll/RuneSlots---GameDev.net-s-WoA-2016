package bytetroll.woa2016.eeyore.canvas;

import bytetroll.woa2016.eeyore.canvas.internal.woCanvasElementData;
import bytetroll.woa2016.eeyore.woCanvasTextureAnimation;
import bytetroll.woa2016.io.woAsset;

import bytetroll.woa2016.memory.woDestructible;
import bytetroll.woa2016.memory.woDestructor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class woCanvasTexture extends Image implements woDestructible {
    public woCanvasTexture() {
        woDestructor.AddDestructible(this);
    }

    @Override
    public void Destructor() {
        texture.dispose();
    }

    public woCanvasTexture(Texture texture) {
        super(texture);
        this.texture = texture;
    }

    public woCanvasTexture(woCanvasTextureAnimation anim) {
        super(anim);
        this.texture = texture;
    }

    public void Dispose() {
        texture.dispose();
    }

    public woCanvasTexture(woAsset tex) {
        Texture newTex = AssetAsTexture(tex);
        if(texture != null) {
            texture.dispose();
        }
        texture = newTex;
    }

    public woCanvasTexture(woCanvasElementData data) {
        texture = ElementDataAsTexture(data);
    }

    private Texture ElementDataAsTexture(Object data) {
        return ((woCanvasTexture)data).AsTexture();
    }

    public woCanvasTexture FromAsset(woAsset tex) {
        Texture newTex = AssetAsTexture(tex);
        texture.dispose();
        return new woCanvasTexture(newTex);
    }

    public Image AsImage() {
        return new Image(texture);
    }

    public Texture AsTexture() {
        return texture;
    }

    private Texture AssetAsTexture(woAsset tex) {
        return new Texture(tex.AsLibGdxHandle());
    }

    private Texture texture = null;
}

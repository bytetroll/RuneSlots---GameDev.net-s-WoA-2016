package bytetroll.woa2016.eeyore.canvas;

import bytetroll.woa2016.eeyore.canvas.internal.woCanvasElementData;
import bytetroll.woa2016.io.woAsset;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class woCanvasTexture extends Image {
    public woCanvasTexture() {
    }

    public woCanvasTexture(Texture texture) {
        super(texture);
        this.texture = texture;
    }

    public void Dispose() {
        texture.dispose();
    }

    public woCanvasTexture(woAsset tex) {
        texture = AssetAsTexture(tex);
    }

    public woCanvasTexture(woCanvasElementData data) {
        texture = ElementDataAsTexture(data);
    }

    private Texture ElementDataAsTexture(Object data) {
        return ((woCanvasTexture)data).AsTexture();
    }

    public woCanvasTexture FromAsset(woAsset tex) {
        return new woCanvasTexture(AssetAsTexture(tex));
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

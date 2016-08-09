package bytetroll.woa2016.eeyore.canvas;

import bytetroll.woa2016.io.woAsset;

import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class woCanvasTexture extends Image {
    public woCanvasTexture(Texture texture) {
        super(texture);
        this.texture = texture;
    }

    public woCanvasTexture(woAsset tex) {
        AssetAsTexture(tex);
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

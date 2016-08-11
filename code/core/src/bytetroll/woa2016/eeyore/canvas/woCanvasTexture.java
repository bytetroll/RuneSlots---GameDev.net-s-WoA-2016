package bytetroll.woa2016.eeyore.canvas;

import bytetroll.woa2016.eeyore.canvas.internal.woCanvasElementData;
import bytetroll.woa2016.eeyore.canvas.internal.woCanvasElementDataType;
import bytetroll.woa2016.io.woAsset;

import bytetroll.woa2016.memory.woDestructible;
import bytetroll.woa2016.memory.woDestructor;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class woCanvasTexture extends Image implements woDestructible {
    public woCanvasTexture() {
    }

    public woCanvasTexture(Texture texture) {
        super(texture);
        this.texture = texture;

        woDestructor.AddDestructible(this);
    }

    //==================================================================================================================
    //>> BEGIN DESTRUCTIBLE INTERFACE
    //==================================================================================================================s
    @Override
    public void Destruct() {
        texture.dispose();
    }
    //==================================================================================================================
    //>> END DESTRUCTIBLE INTERFACE
    //==================================================================================================================

    public woCanvasTexture(woAsset tex) {
        texture = AssetAsTexture(tex);

        woDestructor.AddDestructible(this);
    }

    public woCanvasTexture(woCanvasElementData data) {
        texture = ElementDataAsTexture(data);

        woDestructor.AddDestructible(this);
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

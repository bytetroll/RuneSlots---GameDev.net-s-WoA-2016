package bytetroll.woa2016.eeyore.canvas;

import bytetroll.woa2016.io.woAsset;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class woCanvasTexture extends Image {
    public woCanvasTexture(Texture texture) {
        super(texture);
        this.texture = texture;
    }

    public woCanvasTexture(woAsset texture) {
        this.texture = new Texture(texture.AsLibGdxHandle());
    }

    public void Flush() {
        texture.dispose();
    }

    public Texture InternalTexture() {
        return texture;
    }

    private Texture texture = null;
}

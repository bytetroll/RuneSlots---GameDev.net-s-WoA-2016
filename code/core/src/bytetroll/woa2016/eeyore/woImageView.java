package bytetroll.woa2016.eeyore;

import bytetroll.woa2016.eeyore.canvas.woCanvasElement;
import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class woImageView extends Image {
    public woImageView(woCanvasTexture texture) {
        this.texture = texture;
    }



    private woCanvasTexture texture = null;
}
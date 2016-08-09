package bytetroll.woa2016.eeyore;

import bytetroll.woa2016.eeyore.canvas.internal.specialized.woCanvasElementDataTypeImage;
import bytetroll.woa2016.eeyore.canvas.woCanvasElement;
import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;
import bytetroll.woa2016.runtime.reflect.annot.woOverridable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class woImageView extends woCanvasElement {
    public woImageView(woCanvasTexture texture) {
        super.Data.set(new woCanvasElementDataTypeImage(texture));
    }

    @Override
    public void Think(float delta) {
        super.Think(delta);
    }

    @Override
    public void Draw() {
        super.Draw();
    }
}
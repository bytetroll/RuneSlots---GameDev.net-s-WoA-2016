package bytetroll.woa2016.eeyore.canvas.internal.specialized;

import bytetroll.woa2016.eeyore.canvas.internal.woCanvasElementData;
import bytetroll.woa2016.eeyore.canvas.internal.woCanvasElementDataType;
import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class woCanvasElementDataTypeImage extends Image implements woCanvasElementData {
    public woCanvasElementDataTypeImage(woCanvasTexture texture) {
        woCanvasElementData.Type.Set(woCanvasElementDataType.IMAGE);
        woCanvasElementData.Buffer.Set(texture);
    }

    @Override
    public void Destruct() {
    }
}

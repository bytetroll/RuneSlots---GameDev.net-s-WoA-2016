package bytetroll.woa2016.eeyore.canvas.internal.specialized;

import bytetroll.woa2016.eeyore.canvas.internal.woCanvasElementData;
import bytetroll.woa2016.eeyore.canvas.internal.woCanvasElementDataType;
import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class woCanvasElementDataTypeImage extends Image implements woCanvasElementData {
    public woCanvasElementDataTypeImage(woCanvasTexture texture) {
        woCanvasElementData.Type.set(woCanvasElementDataType.IMAGE);
        woCanvasElementData.Data.set(this);

    }
}

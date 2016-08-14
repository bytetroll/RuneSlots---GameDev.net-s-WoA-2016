package bytetroll.woa2016.eeyore.canvas.internal.specialized;

import bytetroll.woa2016.eeyore.canvas.internal.woCanvasElementData;
import bytetroll.woa2016.eeyore.canvas.internal.woCanvasElementDataType;
import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;

import bytetroll.woa2016.idoms.woProperty;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class woCanvasElementDataTypeImage extends Image implements woCanvasElementData {
    public woProperty<woCanvasTexture> Buffer = new woProperty<>(null);
    public woProperty<woCanvasElementDataType> Type = new woProperty<>(null);

    public woCanvasElementDataTypeImage(woCanvasTexture texture) {
        super(texture.AsTexture());

        Type.Set(woCanvasElementDataType.IMAGE);
        Buffer.Set(texture);
    }

    @Override
    public woCanvasTexture GetElementBuffer() {
        return Buffer.Get();
    }

    @Override
    public woCanvasElementDataType GetElementDataType() {
        return Type.Get();
    }
}

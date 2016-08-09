package bytetroll.woa2016.eeyore.canvas.internal;

import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;
import bytetroll.woa2016.idoms.woProperty;

public interface woCanvasElementData {
    public woProperty<woCanvasTexture> Buffer = new woProperty<>(null);
    public woProperty<woCanvasElementDataType> Type = new woProperty<>(woCanvasElementDataType.UNKNOWN);
}

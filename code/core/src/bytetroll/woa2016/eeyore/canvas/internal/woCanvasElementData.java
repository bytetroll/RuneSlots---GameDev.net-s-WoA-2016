package bytetroll.woa2016.eeyore.canvas.internal;

import bytetroll.woa2016.idoms.woProperty;

public interface woCanvasElementData {
    public woProperty<Object> Data = new woProperty<>(null);
    public woProperty<woCanvasElementDataType> Type = new woProperty<>(woCanvasElementDataType.UNKNOWN);
}

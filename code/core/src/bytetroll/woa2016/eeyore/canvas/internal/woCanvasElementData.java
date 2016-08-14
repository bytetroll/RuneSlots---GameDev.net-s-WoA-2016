package bytetroll.woa2016.eeyore.canvas.internal;

import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;
import bytetroll.woa2016.idoms.woProperty;
import bytetroll.woa2016.memory.woDestructible;
import bytetroll.woa2016.runtime.reflect.annot.woOverridable;

public interface woCanvasElementData {
    @woOverridable
    public woCanvasTexture GetElementBuffer();

    @woOverridable
    public woCanvasElementDataType GetElementDataType();
}

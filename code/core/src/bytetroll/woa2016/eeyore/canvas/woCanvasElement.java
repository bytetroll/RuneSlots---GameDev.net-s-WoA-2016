package bytetroll.woa2016.eeyore.canvas;

// Base class for anything that needs to be added to a canvas.  This is the parent
// for all UI controls.

import bytetroll.woa2016.eeyore.canvas.internal.woCanvasElementData;
import bytetroll.woa2016.idoms.woProperty;
import bytetroll.woa2016.math.woVector2;
import bytetroll.woa2016.runtime.reflect.annot.woOverridable;

public abstract class woCanvasElement {
    //----------------------------------------------------------------------------
    // BEGIN PROPERTIES
    //----------------------------------------------------------------------------
    public final woProperty<woVector2> Position = new woProperty<>(woVector2.VEC2_ZERO);
    public final woProperty<String> Name = new woProperty<>(null);
    public final woProperty<woCanvasElementData> Data = new woProperty<>(null);

    //----------------------------------------------------------------------------
    // END PROPERTIES
    //----------------------------------------------------------------------------
    public woCanvasElement() {
    }

    public woCanvasElement(String name, woCanvasElementData data) {
        Name.set(name);
        Data.set(data);
    }

    public void ChangePosition(woVector2 pos) {
        Position.set(pos);
    }

    @woOverridable
    public void Think(float delta) {
    }

    @woOverridable
    public void Draw() {
    }

    @woOverridable
    public void Invalidate() {
    }

    protected woCanvasElement(String name, woVector2 pos, woCanvasElementData data) {
        Name.set(name);
        Position.set(pos);
        Data.set(data);
    }
}

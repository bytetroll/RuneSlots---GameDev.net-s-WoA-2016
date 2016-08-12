package bytetroll.woa2016.eeyore.canvas.internal;

import bytetroll.woa2016.math.woVector2;
import bytetroll.woa2016.runtime.reflect.annot.woOverridable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public interface woCanvasElementInputListener {
    @woOverridable
    public boolean OnKeyDown(int keyCode);


    @woOverridable
    public boolean OnKeyUp(int keyCode);


    @woOverridable
    public boolean OnKeyTyped(char ch);


    @woOverridable
    public boolean OnMouseUp(String elemName, woVector2 pos, int pointer, int button);

    @woOverridable
    public boolean OnMouseDown(String elemName, woVector2 pos, int pointer, int button);

    @woOverridable
    public boolean OnTouchDragged(String elemName, woVector2 pos, int pointer);

    @woOverridable
    public boolean OnMouseEnter(String elemName, woVector2 pos);

    @woOverridable
    public boolean OnMouseLeave(String elemName, woVector2 pos);

    @woOverridable
    public boolean OnMouseMoved(String elemName, woVector2 pos);

    @woOverridable
    public boolean OnScrolled(String elemName, int amount);

}

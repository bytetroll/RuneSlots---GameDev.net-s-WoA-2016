package bytetroll.woa2016.eeyore.event;

import bytetroll.woa2016.math.woVector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public interface woCanvasEventListener {
    public boolean OnTouchDown(InputEvent event, woVector2 pos, int pointer, int button);
    public boolean OnTouchUp(InputEvent event, woVector2 pos, int pointer, int button);
}

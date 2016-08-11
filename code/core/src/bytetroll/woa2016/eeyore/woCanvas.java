package bytetroll.woa2016.eeyore;

import bytetroll.woa2016.eeyore.canvas.woCanvasElement;
import bytetroll.woa2016.math.woVector2;
import bytetroll.woa2016.runtime.reflect.annot.woOverridable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

// A canvas is responsible for putting all registered UI elements on the screen.
// Generally, a game will contain one canvas for the UI, which will register, draw,
// and update the containing elements.

public class woCanvas extends Actor implements InputProcessor {
    public woCanvas(Stage scene) {
        this.scene = scene;
        Gdx.input.setInputProcessor(this);
    }

    public void AddElement(woCanvasElement elem) {
        sceneElements.add(elem);
    }


    public void Think(float delta) {
        for(woCanvasElement elem : sceneElements) {
            elem.Think(delta);
        }
    }

    public void Draw() {
        for(woCanvasElement elem : sceneElements) {
            elem.Draw(scene);
        }
    }

    public boolean keyDown(int keycode) {
        return false;
    }

    public boolean keyUp(int keycode) {
        return false;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int x, int y, int pointer, int button) {
        // Todo: determine which element was clicked on here so that we don't just call all listeners
        for(woCanvasElement elem : sceneElements) {
            elem.InputHook.Get().OnMouseDown(elem.Name.Get(), new woVector2(x, y), pointer, button);
        }

        return true;
    }

    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved(int x, int y) {
        return false;
    }

    public boolean scrolled(int amount) {
        return false;
    }

    private Stage scene = null;
    private static List<woCanvasElement> sceneElements = new ArrayList<>();
}
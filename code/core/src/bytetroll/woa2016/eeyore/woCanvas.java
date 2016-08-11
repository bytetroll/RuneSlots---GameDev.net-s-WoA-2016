package bytetroll.woa2016.eeyore;

import bytetroll.woa2016.eeyore.canvas.internal.specialized.woCanvasElementDataTypeImage;
import bytetroll.woa2016.eeyore.canvas.woCanvasElement;
import bytetroll.woa2016.math.woVector2;
import bytetroll.woa2016.runtime.reflect.annot.woOverridable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
    public woCanvas(Stage scene, Camera camera) {
        this.scene = scene;
        this.camera = camera;
        Gdx.input.setInputProcessor(this);
    }

    public void AddElement(woCanvasElement elem) {
        elem.Scene.Set(scene);
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

    public boolean keyDown(int keyCode) {
        for(woCanvasElement elem : sceneElements) {
            elem.InputHook.Get().OnKeyDown(keyCode);
        }

        return true;
    }

    public boolean keyUp(int keyCode) {
        for(woCanvasElement elem : sceneElements) {
            elem.InputHook.Get().OnKeyUp(keyCode);
        }

        return true;
    }

    public boolean keyTyped(char ch) {
        for(woCanvasElement elem : sceneElements) {
            elem.InputHook.Get().OnKeyTyped(ch);
        }

        return true;
    }

    public boolean touchDown(int x, int y, int pointer, int button) {
        for(woCanvasElement elem : sceneElements) {
            final float eX = elem.Position.Get().x;
            final float eY = elem.Position.Get().y;
            final float eWidth = ((woCanvasElementDataTypeImage) elem.ElementData.Get()).Buffer.Get().AsTexture().getWidth();
            final float eHeight = ((woCanvasElementDataTypeImage) elem.ElementData.Get()).Buffer.Get().AsTexture().getHeight();

            final Vector3 proj = camera.unproject(new Vector3(x, y, 0.0f));
            Rectangle rect = new Rectangle(eX, eY, eWidth, eHeight);

            if(rect.contains(new Vector2(proj.x, proj.y))) {
                if(elem.InputHook.Get() != null) {
                    elem.InputHook.Get().OnMouseDown(elem.Name.Get(), new woVector2(x, y), pointer, button);
                }

            }
        }

        return true;
    }

    public boolean touchUp(int x, int y, int pointer, int button) {
        for(woCanvasElement elem : sceneElements) {
            final float eX = elem.Position.Get().x;
            final float eY = elem.Position.Get().y;
            final float eWidth = ((woCanvasElementDataTypeImage) elem.ElementData.Get()).Buffer.Get().AsTexture().getWidth();
            final float eHeight = ((woCanvasElementDataTypeImage) elem.ElementData.Get()).Buffer.Get().AsTexture().getHeight();

            final Vector3 proj = camera.unproject(new Vector3(x, y, 0.0f));
            Rectangle rect = new Rectangle(eX, eY, eWidth, eHeight);

            if(rect.contains(new Vector2(proj.x, proj.y))) {
                if(elem.InputHook.Get() != null) {
                    elem.InputHook.Get().OnMouseUp(elem.Name.Get(), new woVector2(x, y), pointer, button);
                }

            }
        }

        return true;
    }

    public boolean touchDragged(int x, int y, int pointer) {
        return true;
    }

    public boolean mouseMoved(int x, int y) {
        return false;
    }

    public boolean scrolled(int amount) {
        return false;
    }

    private Stage scene = null;
    private Camera camera = null;
    private static List<woCanvasElement> sceneElements = new ArrayList<>();
}
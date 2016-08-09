package bytetroll.woa2016.eeyore;

import bytetroll.woa2016.eeyore.canvas.woCanvasElement;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

// A canvas is responsible for putting all registered UI elements on the screen.
// Generally, a game will contain one canvas for the UI, which will register, draw,
// and update the containing elements.

public class woCanvas extends Actor {
    public woCanvas(Stage scene) {
        this.scene = scene;
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

    private Stage scene = null;
    private static List<woCanvasElement> sceneElements = new ArrayList<>();
}
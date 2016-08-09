package bytetroll.woa2016.eeyore.controls;

import bytetroll.woa2016.eeyore.canvas.woCanvasElement;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

// A canvas is responsible for putting all registered UI elements on the screen.
// Generally, a game will contain one canvas for the UI, which will register, draw,
// and update the containing elements.

public class woCanvas {
    public woCanvas() {
    }

    public woCanvas(Stage scene) {
        this.scene = scene;
    }

    private Stage scene = null;
    private List<woCanvasElement> sceneElements = new ArrayList<>();
}
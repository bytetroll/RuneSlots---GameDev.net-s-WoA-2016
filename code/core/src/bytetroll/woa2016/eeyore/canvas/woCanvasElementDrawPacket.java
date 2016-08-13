package bytetroll.woa2016.eeyore.canvas;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class woCanvasElementDrawPacket {
    public woCanvasElementDrawPacket(Stage scene) {
        this.scene = scene;

        root = scene.getRoot();
        batch = scene.getBatch();
        camera = scene.getCamera();
    }

    public Group root = null;
    public Batch batch = null;
    public Camera camera = null;

    private Stage scene = null;
}
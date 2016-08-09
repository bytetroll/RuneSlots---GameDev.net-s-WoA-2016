package bytetroll.woa2016.eeyore.canvas;

// Base class for anything that needs to be added to a canvas.  This is the parent
// for all UI controls.

import bytetroll.woa2016.eeyore.canvas.internal.woCanvasElementData;
import bytetroll.woa2016.idoms.woProperty;
import bytetroll.woa2016.math.woVector2;
import bytetroll.woa2016.runtime.reflect.annot.woOverridable;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class woCanvasElement {
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

    //----------------------------------------------------------------------------
    // BEGIN PROPERTIES
    //----------------------------------------------------------------------------
    public final woProperty<woVector2> Position = new woProperty<>(woVector2.VEC2_ZERO);
    public final woProperty<String> Name = new woProperty<>(null);
    public final woProperty<woCanvasElementData> ElementData = new woProperty<>(null);
    //----------------------------------------------------------------------------
    // END PROPERTIES
    //----------------------------------------------------------------------------
    public woCanvasElement() {

    }

    public woCanvasElement(String name, woCanvasElementData data) {
        Name.Set(name);
        ElementData.Set(data);
    }

    public void ChangePosition(woVector2 pos) {
        Position.Set(pos);
    }

    public void Draw(Stage scene) {
        Draw(new woCanvasElementDrawPacket(scene));
    }

    public void BeginDrawing(woCanvasElementDrawPacket packet) {
        packet.camera.update();

        if(!packet.root.isVisible()) {
            return;
        }

        if(packet.batch != null) {
            packet.batch.setProjectionMatrix(packet.camera.combined);
            packet.batch.begin();
        }
    }

    public void EndDrawing(woCanvasElementDrawPacket packet) {
        packet.root.draw(packet.batch, 1);
        packet.batch.end();
    }

    @woOverridable
    public void Draw(woCanvasElementDrawPacket packet) {
    }

    @woOverridable
    public void Think(float delta) {
    }

    @woOverridable
    public void Invalidate() {
    }

    protected woCanvasElement(String name, woVector2 pos, woCanvasElementData data) {
        Name.Set(name);
        Position.Set(pos);
        ElementData.Set(data);
    }
}

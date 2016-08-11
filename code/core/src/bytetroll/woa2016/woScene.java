package bytetroll.woa2016;

import bytetroll.woa2016.eeyore.canvas.woCanvasElement;
import bytetroll.woa2016.eeyore.woCanvas;
import bytetroll.woa2016.memory.woDestructible;
import bytetroll.woa2016.memory.woDestructor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class woScene implements woDestructible {
    public woScene() {
        Gdx.input.setInputProcessor(scene);

        ui = new woCanvas(scene, scene.getCamera());

        woDestructor.AddDestructible(this);
    }

    //==================================================================================================================
    //>> BEGIN DESTRUCTIBLE INTERFACE
    //==================================================================================================================
    @Override
    public void Destruct() {
        scene.dispose();
    }
    //==================================================================================================================
    //>> END DESTRUCTIBLE INTERFACE
    //==================================================================================================================

    public void SpawnActor(Actor actor) {
        scene.addActor(actor);
    }

    public void SpawnActor(woCanvas canvas) {
        ui = canvas;
    }

    public void SpawnActor(woCanvasElement elem) {
        ui.AddElement(elem);
    }

    public void Think(float delta) {
        scene.act();

        if(ui != null) {
            ui.Think(delta);
        }
    }

    public void Draw() {
        scene.draw();

        if(ui != null) {
            ui.Draw();
        }
    }

    //public static Stage GetWorld() {
    //    return scene;
    //}

    private Stage scene = new Stage();
    private woCanvas ui = null;
}
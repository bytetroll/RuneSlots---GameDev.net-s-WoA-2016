package bytetroll.woa2016.world;

import bytetroll.woa2016.eeyore.canvas.woCanvasElement;
import bytetroll.woa2016.eeyore.woCanvas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class woScene {
    public woScene() {
        Gdx.input.setInputProcessor(scene);

        ui = new woCanvas(scene, scene.getCamera());
    }

    public void SpawnActor(Actor actor) {
        scene.addActor(actor);

        final woActor act = (woActor)actor;
        act.Scene.Set(scene);
        actors.add(act);
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

        for(woActor actor : actors) {
            actor.Think(delta);
        }
    }

    public void Draw() {
        scene.draw();

        if(ui != null) {
            ui.Draw();
        }

        for(woActor actor : actors) {
            actor.Draw();
        }
    }

    public woCanvasElement FindCanvasActor(woCanvasElement elem) {
        for(woCanvasElement e : ui.Elements()) {
            if(e.equals(elem)) {
                return e;
            }
        }

        return null;
    }

    //public static Stage GetWorld() {
    //    return scene;
    //}

    private Stage scene = new Stage();
    private woCanvas ui = null;

    private Array<woActor> actors = new Array<>();
}
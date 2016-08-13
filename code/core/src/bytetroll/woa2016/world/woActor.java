package bytetroll.woa2016.world;

import bytetroll.woa2016.idoms.woProperty;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class woActor extends Actor implements woActorInterface {
    public final woProperty<Stage> Scene = new woProperty<>(null);

    public woActor() {
    }

    public void Think(float delta) {
    }

    public void Draw() {
    }
}

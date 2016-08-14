package bytetroll.woa2016.ohshit;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BackgroundButton extends Image {
    public int background_id;
    public Stage stage;

    public BackgroundButton(int x, int y, int background_id_param, Stage stage_param) {
        super(new AnimationDrawable(1, 1, 64, 64, 0, "runic/runic_ui_button.png", 0.1f));

        this.stage = stage_param;
        this.background_id = background_id_param;

        setPosition(x, y);
//        addCaptureListener(new InputListener() {
//
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                //Cleaning
//                Background previous_bg = (Background) stage.getActors().items[0];
//                previous_bg.texture.dispose();
//                System.gc();
//
//                //Creating new
//                Texture texture_background = new Texture("runic/runic_ui_button_click.png");
//                Background background = new Background(texture_background);
//                stage.getActors().items[0] = background;
//                return true;
//            }
//
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//
//            }
//        });
    }

    @Override
    public void act(float delta) {
        ((AnimationDrawable) this.getDrawable()).act(delta);
        super.act(delta);
    }

    public void animateLines(int lines) {
        ((AnimationDrawable) getDrawable()).animateRow(lines - 1, true);
    }
}

package bytetroll.woa2016.ohshit;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Powerup extends Image {
    public int powerup_id;
    public Stage stage;
    public SlotLogic slot_logic;
    public int value;

    public Skin skin;
    public Label label;
    public Label label_credits;

    public Powerup(int x, int y, int powerup_id_param, Skin skin_param, Label label_credits_param) {
        //super(new AnimationDrawable(1, 1, 64, 64, 0, "runic/placeholder/powerup_btn" + powerup_id_param + ".png", 0.1f));

        this.skin = skin_param;
        this.powerup_id = powerup_id_param;
        this.label_credits = label_credits_param;
        label = new Label("x0", skin);
        label.setPosition(x, y);
        value = 0;

        setPosition(x, y);
        addCaptureListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                slot_logic.credits -= 10;
                value += 5;
                label_credits.setText(slot_logic.getCreditsString());
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });
    }

    @Override
    public void act(float delta) {
        ((AnimationDrawable) this.getDrawable()).act(delta);
        super.act(delta);
        label.setText("x" + value);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        label.draw(batch, parentAlpha);
    }

    public void animateLines(int lines) {
        ((AnimationDrawable) getDrawable()).animateRow(lines - 1, true);
    }
}

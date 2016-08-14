package bytetroll.woa2016.ohshit;

import bytetroll.woa2016.woGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Handle extends Image {
    public String state;

    private Sound spinSound = null; // Never dispised of.
    public Handle(int x, int y, final SlotLogic slot_logic, final woGame activity) {
        super(new AnimationDrawable(4, 4, 128, 128, 0, "runic/runic_ui_handle.png", 0.1f));

        spinSound = Gdx.audio.newSound(Gdx.files.internal("runic/runic_audio_spin.mp3"));

        setPosition(x, y);
        addCaptureListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(slot_logic.canRoll()) ((AnimationDrawable) getDrawable()).animateRow(1, false);

                spinSound.play();

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(slot_logic.canRoll()) {
                    slot_logic.beginRoll();

                    Runnable myRunnable = new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(1500);
                            } catch(InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            ((AnimationDrawable) getDrawable()).animateRow(2, false);
                            slot_logic.endRoll();
                        }
                    };

                    Thread thread = new Thread(myRunnable);
                    thread.start();
                }
            }
        });
        state = "animating";
        ((AnimationDrawable) getDrawable()).animateRow(0, true);
    }

    @Override
    public void act(float delta) {
        ((AnimationDrawable) this.getDrawable()).act(delta);
        super.act(delta);
    }
}
package bytetroll.woa2016.ohshit;

;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Bet extends Image {
    public ArrayList<Double> bets;
    public int current_bet;
    public Label label_total_bet;
    public SlotLogic slot_logic;

    public Bet(int x, int y, ArrayList<Double> bets_param, Label label_total_bet_param) {
        super(new AnimationDrawable(1, 4, 128, 64, 0, "runic/runic_ui_bet_slider.png", 0.1f));

        this.bets = bets_param;
        this.label_total_bet = label_total_bet_param;
        setPosition(x, y);

        addCaptureListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                current_bet++;
                if(current_bet >= bets.size()) current_bet = 0;
                animateBet(current_bet);
                label_total_bet.setText(slot_logic.getTotalBetString());
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });
        current_bet = 0;
        animateBet(current_bet);
    }

    @Override
    public void act(float delta) {
        ((AnimationDrawable) this.getDrawable()).act(delta);
        super.act(delta);
    }

    public void animateBet(int bet_pos) {
        ((AnimationDrawable) getDrawable()).animateRow(bet_pos, true);
    }

    public double getValue() {
        return bets.get(current_bet);
    }
}

package bytetroll.woa2016.ohshit;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class SlotLogic {
    public ArrayList<ArrayList<Box>> boxes;
    public ArrayList<Powerup> powerups;
    public Handle handle;
    public Lines lines;
    public Bet bet;
    public float credits;
    public float credits_saved;
    public Label label_credits;
    public Label label_total_bet;
    public Label label_last_prize;
    public double last_prize;
    public boolean is_rolling = false;

    private final AndroidFunctionsInterface androidFunctions;

    public SlotLogic(ArrayList<ArrayList<Box>> boxes, float credits, Lines lines, Bet bet, Label label_credits, Label label_total_bet, Label label_last_prize, ArrayList<Powerup> powerups, AndroidFunctionsInterface androidFunctions_param) {
        this.boxes = boxes;
        this.credits = credits;
        this.credits_saved = credits;
        this.lines = lines;
        this.bet = bet;
        this.label_credits = label_credits;
        this.label_total_bet = label_total_bet;
        this.label_last_prize = label_last_prize;
        this.powerups = powerups;
        this.androidFunctions = androidFunctions_param;
        last_prize = 0;
    }

    public void beginRoll() {
        is_rolling = true;

        lines.hideLinesUI();
        for(int i = 0; i < powerups.size(); i++) {
            powerups.get(i).value--;
            if(powerups.get(i).value < 0) powerups.get(i).value = 0;
            powerups.get(i).act(0);
        }
        credits -= getTotalBet();
        for(int i = 0; i < boxes.size(); i++) {
            for(int j = 0; j < boxes.get(i).size(); j++)
                boxes.get(i).get(j).beginRoll();
        }
        label_credits.setText(getCreditsString());
    }

    public void endRoll() {
        for(int i = 0; i < boxes.size(); i++) {
            for(int j = 0; j < boxes.get(i).size(); j++)
                boxes.get(i).get(j).endRoll();
        }

        double prize = 0;
        if(lines.getValue() == 1) {
            prize += winCombo6(1);
            prize += winCombo5(1);
            prize += winCombo4(1);
            prize += winCombo3(1);
            prize += winCombo2(1);
        }

        if(lines.getValue() == 2) {
            prize += winCombo6(0);
            prize += winCombo5(0);
            prize += winCombo4(0);
            prize += winCombo3(0);
            prize += winCombo2(0);

            prize += winCombo6(2);
            prize += winCombo5(2);
            prize += winCombo4(2);
            prize += winCombo3(2);
            prize += winCombo2(2);
        }

        if(lines.getValue() == 3) {
            prize += winCombo6(0);
            prize += winCombo5(0);
            prize += winCombo4(0);
            prize += winCombo3(0);
            prize += winCombo2(0);

            prize += winCombo6(1);
            prize += winCombo5(1);
            prize += winCombo4(1);
            prize += winCombo3(1);
            prize += winCombo2(1);

            prize += winCombo6(2);
            prize += winCombo5(2);
            prize += winCombo4(2);
            prize += winCombo3(2);
            prize += winCombo2(2);
        }

        if(prize != 0) {
            credits += prize;
            last_prize = prize;
        }

        label_last_prize.setText(getLastPrizeString());
        label_credits.setText(getCreditsString());
        androidFunctions.SubmitScore(credits);

        is_rolling = false;
    }

    public double winCombo6(int row) {
        double prize = 0;
        for(int col = 0; col < 1; col++) {
            if(boxes.get(col).get(row).current_row == boxes.get(col + 1).get(row).current_row && boxes.get(col).get(row).current_row == boxes.get(col + 2).get(row).current_row && boxes.get(col).get(row).current_row == boxes.get(col + 3).get(row).current_row && boxes.get(col).get(row).current_row == boxes.get(col + 4).get(row).current_row && boxes.get(col).get(row).current_row == boxes.get(col + 5).get(row).current_row

                    && boxes.get(col).get(row).current_effect == "" && boxes.get(col + 1).get(row).current_effect == "" && boxes.get(col + 2).get(row).current_effect == "" && boxes.get(col + 3).get(row).current_effect == "" && boxes.get(col + 4).get(row).current_effect == "" && boxes.get(col + 5).get(row).current_effect == "") {
                double new_prize = boxes.get(col).get(row).getPrize(6);
                if(new_prize != 0) {
                    prize += new_prize;
                    boxes.get(col).get(row).current_effect = "bigger size";
                    boxes.get(col + 1).get(row).current_effect = "bigger size";
                    boxes.get(col + 2).get(row).current_effect = "bigger size";
                    boxes.get(col + 3).get(row).current_effect = "bigger size";
                    boxes.get(col + 4).get(row).current_effect = "bigger size";
                    boxes.get(col + 5).get(row).current_effect = "bigger size";
                }
            }
        }
        return prize;
    }

    public double winCombo5(int row) {
        double prize = 0;
        for(int col = 0; col < 2; col++) {
            if(boxes.get(col).get(row).current_row == boxes.get(col + 1).get(row).current_row && boxes.get(col).get(row).current_row == boxes.get(col + 2).get(row).current_row && boxes.get(col).get(row).current_row == boxes.get(col + 3).get(row).current_row && boxes.get(col).get(row).current_row == boxes.get(col + 4).get(row).current_row

                    && boxes.get(col).get(row).current_effect == "" && boxes.get(col + 1).get(row).current_effect == "" && boxes.get(col + 2).get(row).current_effect == "" && boxes.get(col + 3).get(row).current_effect == "" && boxes.get(col + 4).get(row).current_effect == "") {
                double new_prize = boxes.get(col).get(row).getPrize(5);
                if(new_prize != 0) {
                    prize += new_prize;
                    boxes.get(col).get(row).current_effect = "bigger size";
                    boxes.get(col + 1).get(row).current_effect = "bigger size";
                    boxes.get(col + 2).get(row).current_effect = "bigger size";
                    boxes.get(col + 3).get(row).current_effect = "bigger size";
                    boxes.get(col + 4).get(row).current_effect = "bigger size";
                }
            }
        }
        return prize;
    }

    public double winCombo4(int row) {
        double prize = 0;
        for(int col = 0; col < 3; col++) {
            if(boxes.get(col).get(row).current_row == boxes.get(col + 1).get(row).current_row && boxes.get(col).get(row).current_row == boxes.get(col + 2).get(row).current_row && boxes.get(col).get(row).current_row == boxes.get(col + 3).get(row).current_row

                    && boxes.get(col).get(row).current_effect == "" && boxes.get(col + 1).get(row).current_effect == "" && boxes.get(col + 2).get(row).current_effect == "" && boxes.get(col + 3).get(row).current_effect == "") {
                double new_prize = boxes.get(col).get(row).getPrize(4);
                if(new_prize != 0) {
                    prize += new_prize;
                    boxes.get(col).get(row).current_effect = "bigger size";
                    boxes.get(col + 1).get(row).current_effect = "bigger size";
                    boxes.get(col + 2).get(row).current_effect = "bigger size";
                    boxes.get(col + 3).get(row).current_effect = "bigger size";
                }
            }
        }
        return prize;
    }

    public double winCombo3(int row) {
        double prize = 0;
        for(int col = 0; col < 4; col++) {
            if(boxes.get(col).get(row).current_row == boxes.get(col + 1).get(row).current_row && boxes.get(col).get(row).current_row == boxes.get(col + 2).get(row).current_row

                    && boxes.get(col).get(row).current_effect == "" && boxes.get(col + 1).get(row).current_effect == "" && boxes.get(col + 2).get(row).current_effect == "") {
                double new_prize = boxes.get(col).get(row).getPrize(3);
                if(new_prize != 0) {
                    prize += new_prize;
                    boxes.get(col).get(row).current_effect = "bigger size";
                    boxes.get(col + 1).get(row).current_effect = "bigger size";
                    boxes.get(col + 2).get(row).current_effect = "bigger size";
                }
            }
        }
        return prize;
    }

    public double winCombo2(int row) {
        double prize = 0;
        for(int col = 0; col < 5; col++) {
            if(boxes.get(col).get(row).current_row == boxes.get(col + 1).get(row).current_row

                    && boxes.get(col).get(row).current_effect == "" && boxes.get(col + 1).get(row).current_effect == "") {
                double new_prize = boxes.get(col).get(row).getPrize(2);
                if(new_prize != 0) {
                    prize += new_prize;
                    boxes.get(col).get(row).current_effect = "bigger size";
                    boxes.get(col + 1).get(row).current_effect = "bigger size";
                }
            }
        }
        return prize;
    }

    public String getCreditsString() {
        return "Your coin:\n" + (int)credits;
    }

    public String getTotalBetString() {
        return "Coin bet:\n" + (int)getTotalBet();
    }

    public String getLastPrizeString() {
        return "Last winnings:\n" + (int)last_prize;
    }

    public double getTotalBet() {
        return lines.getValue() * bet.getValue();
    }

    public boolean canRoll() {
        return getTotalBet() <= credits && !is_rolling;
    }
}

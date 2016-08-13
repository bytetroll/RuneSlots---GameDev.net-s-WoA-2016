package bytetroll.woa2016.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class woSlotSlider extends Image {
    public woSlotSlider(int x, int y, int currentValue) {
        //super(new woDrawableAnimation());
    }

    private String currentEffect = null;
    private int currentRow = 0;

    private int originalX = 0;
    private int originalY = 0;
    private float originalWidth = 0.0f;
    private float originalHeight = 0.0f;

    private Animation anim = null;
}

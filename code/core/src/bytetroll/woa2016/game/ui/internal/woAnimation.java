package bytetroll.woa2016.game.ui.internal;

import bytetroll.woa2016.io.woAsset;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;

public class woAnimation extends BaseDrawable {
    public woAnimation(int frameColumns, int frameRows, int width, int height, int currentRowAnim, woAsset texture) {
        this.frameColumns = frameColumns;
        this.frameRows = frameRows;
        this.width = width;
        this.height = height;
        this.currentRowAnim = currentRowAnim;
        this.texture = texture;

        super.setMinWidth(width);
        super.setMinWidth(height);

        looped = false;
    }

    public Animation[] Animations(woAsset texture, float frameDuration) {
        final Texture tex = new Texture(texture.)
    }

    private int width = 0;
    private int height = 0;

    private int frameColumns = 0;
    private int frameRows = 0;

    private float stateTime = 0.0f;

    private int currentRowAnim;

    private boolean looped = false;

    private Animation anims[] = null;
    private woAsset texture = null;
}

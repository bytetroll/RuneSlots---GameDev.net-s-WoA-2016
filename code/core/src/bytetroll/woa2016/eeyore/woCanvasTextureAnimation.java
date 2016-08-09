package bytetroll.woa2016.eeyore;

import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;
import bytetroll.woa2016.io.woAsset;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;


public class woCanvasTextureAnimation extends BaseDrawable {
    public woCanvasTextureAnimation(int frameColumns, int frameRows, int width, int height, int currentRowAnim,
            woCanvasTexture texture) {
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

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        batch.draw(anims[currentRowAnim].getKeyFrame(stateTime, looped), x, y, width, height);
    }

    public Animation[] Animations(woAsset texture, float frameDuration) {
        final Texture tex = new Texture(texture.AsLibGdxHandle());
        TextureRegion[][] texRegions = TextureRegion.split(tex, (tex.getWidth() / frameColumns),
                (tex.getHeight() / frameRows));
        Animation[] anims = new Animation[frameRows];

        for(int i = 0; i < frameRows; i++) {
            TextureRegion texRegion[] = new TextureRegion[frameColumns];

            for(int j = 0; j < frameColumns; j++) {
                texRegion[j] = texRegions[i][j];
            }

            anims[i] = new Animation(frameDuration, texRegion);
        }

        return anims;
    }

    public void Think(float delta) {
        stateTime += delta;
    }

    public void Reset() {
        stateTime = 0;
    }

    public void AnimateRow(int row, boolean looped) {
        Reset();

        this.looped = looped;
        currentRowAnim = row;
    }

    private int width = 0;
    private int height = 0;

    private int frameColumns = 0;
    private int frameRows = 0;

    private float stateTime = 0.0f;

    private int currentRowAnim;

    private boolean looped = false;

    private Animation anims[] = null;
    private woCanvasTexture texture = null;
}

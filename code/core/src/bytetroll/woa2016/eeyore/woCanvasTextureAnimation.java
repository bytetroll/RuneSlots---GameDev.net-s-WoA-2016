package bytetroll.woa2016.eeyore;

import bytetroll.woa2016.cli.woCLI;
import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;
import bytetroll.woa2016.io.woAsset;
import bytetroll.woa2016.math.woVector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;


public class woCanvasTextureAnimation extends BaseDrawable{
    public woCanvasTextureAnimation(woVector2 pos, int frameColumns, int frameRows, int width, int height, int currentRowAnim,
            woCanvasTexture texture, float frameDuration) {
        this.frameColumns = frameColumns;
        this.frameRows = frameRows;
        this.width = width;
        this.height = height;
        this.currentRowAnim = currentRowAnim;
        this.texture = texture;
        this.pos = pos;
        this.anims = Animations(texture, frameDuration);

        looped = false;
    }

    public void Draw() {
        Draw(new SpriteBatch(), pos.x, pos.y, width, height);
    }

    public void Draw(Batch batch, float x, float y, float width, float height) {
        batch.begin();
        if(anims != null) {
            batch.draw(anims[currentRowAnim].getKeyFrame(stateTime, looped), x, y, width, height);
        }
        batch.end();
    }

    public Animation[] Animations(woCanvasTexture texture, float frameDuration) {
        final Texture tex = texture.AsTexture();
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

        //SSSStex.dispose();

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

    private woVector2 pos = woVector2.VEC2_ZERO;

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

package bytetroll.woa2016.game;

import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;
import bytetroll.woa2016.eeyore.woCanvasTextureAnimation;
import bytetroll.woa2016.eeyore.woImageView;
import bytetroll.woa2016.idoms.woReadOnly;
import bytetroll.woa2016.io.woAsset;
import bytetroll.woa2016.io.woAssetHandler;
import bytetroll.woa2016.math.woVector2;
import bytetroll.woa2016.world.woActor;
import bytetroll.woa2016.world.woScene;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class woSlotSlider extends woActor {
    public woSlotSlider(woScene scene) {
        this.scene = scene;

        final woVector2 pos = new woVector2(500, 500);
        final int width = 400;
        final int height = 400;
        final int curval = 0;

        final woAsset animTex = woAssetHandler.Find("runic_ui_slot.png");
        box = new woImageView(animTex, pos);

        final woCanvasTexture tex = new woCanvasTexture(animTex);
        box.SetAnimation(new woCanvasTextureAnimation(pos, 4, 8, 128, 128, curval, tex, 0.1f));
        tex.Dispose();

        origPos = pos;
        origWidth = width;
        origHeight = height;

        curRow = curval;
        curEffect = woSlotSliderEffects.BiggerSize.Get();

        scene.SpawnActor(box);
    }

    @Override
    public void Think(float delta) {
        box.Think(delta);
    }

    private woImageView box = null;

    private String curEffect = null;
    private int curRow = 0;

    private woVector2 origPos = woVector2.VEC2_ZERO;
    private float origWidth = 0.0f;
    private float origHeight = 0.0f;

    private Animation anim = null;

    private woScene scene = null;
}

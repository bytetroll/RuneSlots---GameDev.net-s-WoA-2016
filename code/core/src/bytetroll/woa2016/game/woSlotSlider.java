package bytetroll.woa2016.game;

import bytetroll.woa2016.cli.woCLI;
import bytetroll.woa2016.eeyore.canvas.internal.specialized.woCanvasElementDataTypeImage;
import bytetroll.woa2016.eeyore.canvas.woCanvasElement;
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
    public woSlotSlider(woScene scene, woVector2 pos, int curVal) {
        this.scene = scene;

        final int width = 400;
        final int height = 400;

        final woAsset animTex = woAssetHandler.Find("runic_ui_slot.png");
        box = new woImageView(animTex, pos);

        final woCanvasTexture tex = new woCanvasTexture(animTex);
        box.SetAnimation(new woCanvasTextureAnimation(pos, 4, 8, 128, 128, curVal, tex, 0.1f));
        tex.Dispose();

        origPos = pos;
        origWidth = width;
        origHeight = height;

        curRow = curVal;
        curEffect = woSlotSliderEffects.BiggerSize.Get();

        scene.SpawnActor(box);
    }

    @Override
    public void Think(float delta) {
        final woVector2 scale = box.Scale();
        final int w = box.Width();
        final int h = box.Height();

        if(curEffect.equals(woSlotSliderEffects.BiggerSize.Get())) {
            box.SetScale(((float) (scale.x + 0.01)));
            box.Position.Set(new woVector2((origPos.x - (w * scale.x - w) / 2), (origPos.y - (h * scale.y - h) / 2)));
            if((float) (scale.x + 0.1) > 1.5) {
                curEffect = woSlotSliderEffects.SmallerSize.Get();
            }
        } else if(curEffect.equals(woSlotSliderEffects.SmallerSize.Get())) {
            box.SetScale(scale.x - 0.01f);
            box.Position.Set(new woVector2((origPos.x - (w * scale.x - w) / 2), (origPos.y - (h * scale.y - h) / 2)));
            if((float) (scale.x) < 1.0) {
                box.SetScale((float) 1.0);
                box.Position.Set(new woVector2((origPos.x - (w * scale.x - w) / 2), (origPos.y - (h * scale.y - h) / 2)));
                curEffect = woSlotSliderEffects.BiggerSize.Get();
            }
        } else {
            box.SetScale((float) 1.0);
            box.Position.Set(new woVector2((origPos.x - (w * scale.x - w) / 2), (origPos.y - (h * scale.y - h) / 2)));
        }

        //box.Think(delta);
    }

    @Override
    public void Draw() {
        //box.Draw(scene);
    }


    void BeginRoll() {
        curRow = 0;
        box.GetAnimation().AnimateRow(curRow, true);
    }

    void EndRoll() {
        curRow = (int) ((Math.random() * 1000) % 7 + 1);
        box.GetAnimation().AnimateRow(curRow, false);
        curEffect = woSlotSliderEffects.None.Get();
    }

    double getPrize(int combo) {
        if(curRow == 1) {
            if(combo == 6) return 1000;
            if(combo == 5) return 700;
            if(combo == 4) return 380;
            if(combo == 3) return 200;
            if(combo == 2) return 100;
        }

        if(curRow == 2) {
            if(combo == 6) return 800;
            if(combo == 5) return 500;
            if(combo == 4) return 180;
            if(combo == 3) return 80;
            if(combo == 2) return 20;
        }

        if(curRow == 3) {
            if(combo == 6) return 650;
            if(combo == 5) return 450;
            if(combo == 4) return 150;
            if(combo == 3) return 60;
            if(combo == 2) return 15;
        }

        if(curRow == 4) {
            if(combo == 6) return 450;
            if(combo == 5) return 250;
            if(combo == 4) return 90;
            if(combo == 3) return 40;
            if(combo == 2) return 10;
        }

        if(curRow == 5) {
            if(combo == 6) return 250;
            if(combo == 5) return 50;
            if(combo == 4) return 25;
            if(combo == 3) return 5;
        }

        if(curRow == 6) {
            if(combo == 6) return 50;
            if(combo == 5) return 25;
            if(combo == 4) return 5;
            if(combo == 3) return 3;
        }

        if(curRow == 7) {
            if(combo == 6) return 25;
            if(combo == 5) return 5;
            if(combo == 4) return 3;
        }
        return 0;
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

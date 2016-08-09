package bytetroll.woa2016.eeyore;

import bytetroll.woa2016.eeyore.canvas.internal.specialized.woCanvasElementDataTypeImage;
import bytetroll.woa2016.eeyore.canvas.woCanvasElement;
import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;
import bytetroll.woa2016.idoms.woProperty;
import bytetroll.woa2016.io.woAsset;
import bytetroll.woa2016.math.woVector2;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class woButton extends woCanvasElement {
    public final woProperty<woCanvasTexture> Texture = new woProperty<>(null);
    public final woProperty<String> ButtonText = new woProperty<>(null);

    public woButton(woAsset texture, woVector2 pos) {
        super.Data.set(new woCanvasElementDataTypeImage(new woCanvasTexture(texture)));
        super.Position.set(pos);

    }

    @Override
    public void Think(float delta) {
        super.Think(delta);
    }

    @Override
    public void Draw() {
        super.Draw();
    }

    /*
    public woButton(woAsset texture, woVector2 pos, Stage scene) {
        //super(new woCanvasTextureAnimation(1, 1, 64, 64, 0, texture));

        Texture.set(new woImageView(new woCanvasTexture(texture));

        this.scene = scene;

        super.setPosition(pos.x, pos.y);


        super.addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                woImageView prevBg = (woImageView)scene.getActors().items[0];
                prevBg.Fl

                woRuntime.GarbageCollector.BeginCollection();
            }
        });
    }
    */
}

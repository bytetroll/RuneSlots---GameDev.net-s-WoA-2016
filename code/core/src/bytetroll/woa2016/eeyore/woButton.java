package bytetroll.woa2016.eeyore;

import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;
import bytetroll.woa2016.io.woAsset;
import bytetroll.woa2016.math.woVector2;
import bytetroll.woa2016.runtime.woRuntime;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class woButton extends Image {
    public woButton(woAsset texture, woVector2 pos, Stage scene) {
        //super(new woCanvasTextureAnimation(1, 1, 64, 64, 0, texture));

        button = new woImageView(new woCanvasTexture(texture));

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

    private Stage scene = null;
    private woImageView button = null;
}

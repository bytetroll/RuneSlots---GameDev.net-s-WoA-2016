package bytetroll.woa2016.game;

import bytetroll.woa2016.eeyore.canvas.woCanvasElementDrawPacket;
import bytetroll.woa2016.eeyore.woButton;
import bytetroll.woa2016.io.woAsset;
import bytetroll.woa2016.io.woAssetHandler;
import bytetroll.woa2016.math.woVector2;
import bytetroll.woa2016.world.woActor;
import bytetroll.woa2016.world.woScene;
import com.badlogic.gdx.scenes.scene2d.Stage;

public final class woRollButton extends woActor {
    public woRollButton(woScene scene) {
        super();

        final woAsset font = woAssetHandler.Find("runic_font_bitmap_default.fnt");
        final woAsset mouseEnterTex = woAssetHandler.Find("runic_ui_button_hover.png");
        final woAsset mouseLeaveTex = woAssetHandler.Find("runic_ui_button.png");

        button = new woButton("Roll", font, mouseEnterTex, mouseLeaveTex, new woVector2(600, 100));
        scene.SpawnActor(button);
    }

    @Override
    public void Think(float delta) {
        //button.Think(delta);
    }

    @Override
    public void Draw() {
        //button.Draw(new woCanvasElementDrawPacket(super.Scene.Get()));
    }

    private woButton button = null;
    private Stage scene = null;
}

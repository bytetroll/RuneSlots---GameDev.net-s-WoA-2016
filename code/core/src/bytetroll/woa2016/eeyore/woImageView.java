package bytetroll.woa2016.eeyore;

import bytetroll.woa2016.eeyore.canvas.internal.specialized.woCanvasElementDataTypeImage;
import bytetroll.woa2016.eeyore.canvas.woCanvasElement;
import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;
import bytetroll.woa2016.io.woAsset;
import bytetroll.woa2016.math.woVector2;
import bytetroll.woa2016.runtime.woRuntime;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class woImageView extends woCanvasElement {
    public woImageView(woAsset texture, woVector2 pos) {
        super.ElementData.Set(new woCanvasElementDataTypeImage(new woCanvasTexture(texture)));
        super.Position.Set(pos);
    }

    @Override
    public void Think(float delta) {
        super.Think(delta);
    }

    @Override
    public void Draw(woCanvasElementDrawPacket packet) {
        super.BeginDrawing(packet);
        packet.batch.draw(super.ElementData.Get().Buffer.Get().AsTexture(), super.Position.Get().x, super.Position.Get().y);
        super.EndDrawing(packet);
    }
}
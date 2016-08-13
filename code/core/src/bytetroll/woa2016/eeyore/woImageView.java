package bytetroll.woa2016.eeyore;

import bytetroll.woa2016.cli.woCLI;
import bytetroll.woa2016.eeyore.canvas.internal.specialized.woCanvasElementDataTypeImage;
import bytetroll.woa2016.eeyore.canvas.internal.woCanvasElementInputListener;
import bytetroll.woa2016.eeyore.canvas.woCanvasElement;
import bytetroll.woa2016.eeyore.canvas.woCanvasElementDrawPacket;
import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;
import bytetroll.woa2016.idoms.woProperty;
import bytetroll.woa2016.io.woAsset;
import bytetroll.woa2016.math.woVector2;
import bytetroll.woa2016.runtime.reflect.annot.woOverridable;
import bytetroll.woa2016.runtime.woRuntime;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class woImageView extends woCanvasElement implements woCanvasElementInputListener {
    public woProperty<Boolean> IsHovering = new woProperty<>(false);

    public woImageView(woAsset texture, woVector2 pos) {
        super(new woCanvasElementDataTypeImage(new woCanvasTexture(texture)));
        super.InputHook.Set(this);
        super.Position.Set(pos);
    }

    @Override
    public void Think(float delta) {
        super.Think(delta);
    }

    @Override
    public void Draw(woCanvasElementDrawPacket packet) {
        super.BeginDrawing(packet);
        packet.batch.draw(super.ElementData.Get().GetElementBuffer().AsTexture(), super.Position.Get().x, super.Position.Get().y);
        super.EndDrawing(packet);
    }

    @Override
    public boolean OnKeyDown(int keyCode) {
        return false;
    }


    @Override
    public boolean OnKeyUp(int keyCode) {
        return false;
    }


    @Override
    public boolean OnKeyTyped(char ch) {
        return false;
    }


    @Override
    public boolean OnMouseUp(String elemName, woVector2 pos, int pointer, int button) {
        return false;
    }

    @Override
    public boolean OnMouseDown(String elemName, woVector2 pos, int pointer, int button) {
        return false;
    }

    @Override
    public boolean OnTouchDragged(String elemName, woVector2 pos, int pointer) {
        return false;
    }

    @Override
    public boolean OnMouseEnter(String elemName, woVector2 pos) {
        if(elemName.equals(super.Name.Get())) {
            woCLI.PrintLine("OnMouseEnter " + super.Name.Get());
            IsHovering.Set(true);
        }
        return true;
    }

    @Override
    public boolean OnMouseLeave(String elemName, woVector2 pos) {
        if(elemName.equals(super.Name.Get())) {
            woCLI.PrintLine("OnMouseLeave " + elemName);
            IsHovering.Set(false);
        }
        return true;
    }

    @Override
    public boolean OnMouseMoved(String elemName, woVector2 pos) {
        return false;
    }

    @Override
    public boolean OnScrolled(String elemName, int amount) {
        return false;
    }

    @Override
    public boolean IsHovering() { return IsHovering.Get(); }
}
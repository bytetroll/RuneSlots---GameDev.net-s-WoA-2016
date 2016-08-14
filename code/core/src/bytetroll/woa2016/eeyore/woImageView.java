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
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sun.corba.se.impl.ior.ByteBuffer;
import com.sun.imageio.plugins.common.ImageUtil;

import java.awt.image.BufferedImage;
import java.nio.ByteOrder;

public class woImageView extends woCanvasElement implements woCanvasElementInputListener {
    public woProperty<Boolean> IsHovering = new woProperty<>(false);
    public woProperty<woAsset> Tex = new woProperty<>(null);

    public woImageView(woAsset texture, woVector2 pos) {
        super(new woCanvasElementDataTypeImage(new woCanvasTexture(texture)));
        super.InputHook.Set(this);
        super.Position.Set(pos);

        Tex.Set(texture);
    }

    public woVector2 Scale() {
        return new woVector2(super.getScaleX(), super.getScaleY());
    }

    //public void SetScale(woVector2 scale) {
        /*
        Pixmap buf1 = new Pixmap(Tex.Get().gdxHandle);
        Pixmap buf2 = new Pixmap((int)scale.x, (int)scale.x, buf1.getFormat());
        buf2.drawPixmap(buf1,
                0, 0, buf1.getWidth(), buf1.getHeight(),
                0, 0, buf2.getWidth(), buf2.getHeight()
        );
        Texture t = new Texture(buf2);
        buf1.dispose();
        buf2.dispose();

        ((woCanvasElementDataTypeImage)super.ElementData.Get()).Buffer.Get().AsTexture().dispose();
        super.ElementData.Set(new woCanvasElementDataTypeImage(new woCanvasTexture(t)));

        t.dispose();
    */

        //((woCanvasElementDataTypeImage)super.ElementData.Get()).Buffer.Get().AsTexture().
    //

    public void SetScale(woVector2 scale) {
        super.setScale(scale.x, scale.y);
    }

    public void SetScale(float scale) {
        super.setScale(scale);
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

    public void SetAnimation(woCanvasTextureAnimation anim) {
        super.Animation.Set(anim);
    }

    public woCanvasTextureAnimation GetAnimation() {
        return super.Animation.Get();
    }
}
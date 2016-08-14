package bytetroll.woa2016.eeyore;

import bytetroll.woa2016.cli.woCLI;
import bytetroll.woa2016.eeyore.canvas.internal.specialized.woCanvasElementDataTypeImage;
import bytetroll.woa2016.eeyore.canvas.internal.util.woFontGlyphMapResolver;
import bytetroll.woa2016.eeyore.canvas.internal.woCanvasElementInputListener;
import bytetroll.woa2016.eeyore.canvas.internal.woTextRasterizationJob;
import bytetroll.woa2016.eeyore.canvas.internal.woTextRasterizer;
import bytetroll.woa2016.eeyore.canvas.woCanvasElement;
import bytetroll.woa2016.eeyore.canvas.woCanvasElementDrawPacket;
import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;
import bytetroll.woa2016.idoms.woProperty;
import bytetroll.woa2016.io.woAsset;
import bytetroll.woa2016.io.woAssetHandler;
import bytetroll.woa2016.math.woVector2;
import bytetroll.woa2016.memory.woDestructible;
import bytetroll.woa2016.runtime.reflect.annot.woOverridable;
import bytetroll.woa2016.runtime.woRuntime;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class woLabel extends woCanvasElement implements woCanvasElementInputListener {
    public woProperty<String> Text = new woProperty<String>(null);
    public woProperty<Boolean> IsHovering = new woProperty<>(false);

    public woLabel(String text, woVector2 pos, woAsset font, woAsset targetTex) {
        super(pos, new woCanvasElementDataTypeImage(new woCanvasTexture(targetTex)));

        Text.Set(text);

        this.font = font;
        this.targetTex = targetTex;

        // Lib GDX always stores their binary font files as a .fnt and an accompanying .png.
        fontGlyphImage = woFontGlyphMapResolver.Resolve(font);

        super.InputHook.Set(this);
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
        woCLI.PrintLine("Canvas element: " + elemName + " was cliked");
        return true;
    }


    @Override
    public boolean OnTouchDragged(String elemName, woVector2 pos, int pointer) {
        return false;
    }

    @Override
    public boolean OnMouseMoved(String elemName, woVector2 pos) {
        return false;
    }

    @Override
    public boolean OnMouseEnter(String elemName, woVector2 pos) {
        woCLI.PrintLine("Mouse Entered: " + elemName);
        return IsHovering.Set(true);
    }

    @Override
    public boolean OnMouseLeave(String elemName, woVector2 pos) {
        woCLI.PrintLine("Mouse Left: " + elemName);
        return IsHovering.Set(false);
    }

    @Override
    public boolean OnScrolled(String name, int amount) {
        return false;
    }

    @Override
    public void Think(float delta) {
        //super.Position.Set(new woVector2((super.Position.Get().x += delta) * 1.1f, super.Position.Get().y));
    }

    @Override
    public void Draw(woCanvasElementDrawPacket packet) {
        super.BeginDrawing(packet);
        packet.batch.draw(rasterizer.Rasterize(new woTextRasterizationJob(Text.Get(), targetTex, font, fontGlyphImage)).AsTexture(),
                super.Position.Get().x, super.Position.Get().y);
        super.EndDrawing(packet);
    }

    @Override
    public boolean IsHovering() {
        return IsHovering.Get();
    }

    public void SetAnimation(woCanvasTextureAnimation anim) {
        super.Animation.Set(anim);
    }

    public woCanvasTextureAnimation GetAnimation() {
        return super.Animation.Get();
    }

    private woTextRasterizer rasterizer = new woTextRasterizer();

    private woAsset font = null;
    private woAsset fontGlyphImage = null;
    private woAsset targetTex = null;
}
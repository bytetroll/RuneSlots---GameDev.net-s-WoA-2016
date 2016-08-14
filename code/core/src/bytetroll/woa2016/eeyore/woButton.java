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
import bytetroll.woa2016.math.woVector2;

public class woButton extends woCanvasElement implements woCanvasElementInputListener {
    public woProperty<woAsset> MouseEnterTexture = new woProperty<>(null);
    public woProperty<woAsset> MouseLeaveTexture = new woProperty<>(null);
    public woProperty<woAsset> MouseClickTexture = new woProperty<>(null);

    public woProperty<String> ButtonText = new woProperty<>(null);
    public woProperty<woAsset> Font = new woProperty<>(null);
    public woProperty<Boolean> IsHovering = new woProperty<>(false);
    public woProperty<Boolean> IsClicked = new woProperty<>(false);

    public woButton(String text, woAsset font, woAsset mouseEnterTexture, woAsset mouseLeaveTexture, woAsset mouseClickTexture, woVector2 pos ) {
        super(new woCanvasElementDataTypeImage(new woCanvasTexture(mouseEnterTexture)));
        super.Position.Set(pos);
        super.InputHook.Set(this);

        ButtonText.Set(text);
        Font.Set(font);
        MouseEnterTexture.Set(mouseEnterTexture);
        MouseLeaveTexture.Set(mouseLeaveTexture);
        MouseClickTexture.Set(mouseClickTexture);
    }

    @Override
    public void Think(float delta) {
        super.Think(delta);
    }

    @Override
    public void Draw(woCanvasElementDrawPacket packet) {
        super.BeginDrawing(packet);

        woAsset tex = IsHovering.Get() ? MouseEnterTexture.Get() : MouseLeaveTexture.Get();
        if(IsClicked.Get()) {
            tex = MouseClickTexture.Get();
        }

        packet.batch.draw(
                rasterizer.Rasterize(
                        new woTextRasterizationJob(
                                ButtonText.Get(),
                                tex,
                                Font.Get(),
                                woFontGlyphMapResolver.Resolve(
                                        Font.Get()
                                )
                        )
                ).AsTexture(),
                super.Position.Get().x,
                super.Position.Get().y
        );

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
        IsClicked.Set(false);
        return true;

    }

    @Override
    public boolean OnMouseDown(String elemName, woVector2 pos, int pointer, int button) {
        IsClicked.Set(true);
        /*
        previousFrameTex = (woCanvasTexture)super.Scene.Get().getActors().items[0];
        previousFrameTex.AsTexture().dispose();

        woRuntime.GarbageCollector.BeginCollection();

        woCanvasTexture newTex = new woCanvasTexture(MouseEnterTexture.Get());
        super.Scene.Get().getActors().items[0] = newTex;

        return true;
        */

        return true;
    }

    @Override
    public boolean OnMouseEnter(String elemName, woVector2 pos) {
        woCLI.PrintLine("Mouse Entered: " + elemName);
        IsHovering.Set(true);
        return true;
    }

    @Override
    public boolean OnMouseLeave(String elemName, woVector2 pos) {
        woCLI.PrintLine("Mouse Left: " + elemName);
        IsHovering.Set(false);
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
    public boolean OnScrolled(String name, int amount) {
        return false;
    }

    @Override
    public boolean IsHovering() { return IsHovering.Get(); }

    private woTextRasterizer rasterizer = new woTextRasterizer();

    private woCanvasTexture previousFrameTex = null;
}

package bytetroll.woa2016.eeyore;

import bytetroll.woa2016.cli.woCLI;
import bytetroll.woa2016.eeyore.canvas.internal.specialized.woCanvasElementDataTypeImage;
import bytetroll.woa2016.eeyore.canvas.internal.util.woFontGlyphMapResolver;
import bytetroll.woa2016.eeyore.canvas.internal.woCanvasElementInputListener;
import bytetroll.woa2016.eeyore.canvas.internal.woTextRasterizationJob;
import bytetroll.woa2016.eeyore.canvas.internal.woTextRasterizer;
import bytetroll.woa2016.eeyore.canvas.woCanvasElement;
import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;
import bytetroll.woa2016.idoms.woProperty;
import bytetroll.woa2016.io.woAsset;
import bytetroll.woa2016.math.woVector2;

public class woButton extends woCanvasElement implements woCanvasElementInputListener {
    public final woProperty<woAsset> FrameTexture = new woProperty<>(null);
    public final woProperty<String> ButtonText = new woProperty<>(null);
    public final woProperty<woAsset> Font = new woProperty<>(null);

    public woButton(String text, woAsset font, woAsset frameTexture, woVector2 pos ) {
        super(new woCanvasElementDataTypeImage(new woCanvasTexture(frameTexture)));
        super.Position.Set(pos);
        super.InputHook.Set(this);

        ButtonText.Set(text);
        FrameTexture.Set(frameTexture);
        Font.Set(font);
    }

    @Override
    public void Think(float delta) {
        super.Think(delta);
    }

    @Override
    public void Draw(woCanvasElementDrawPacket packet) {
        super.BeginDrawing(packet);
        packet.batch.draw(
                rasterizer.Rasterize(
                        new woTextRasterizationJob(
                                ButtonText.Get(),
                                FrameTexture.Get(),
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
        return false;

    }

    @Override
    public boolean OnMouseDown(String elemName, woVector2 pos, int pointer, int button) {
        woCLI.PrintLine("Canvas element: " + elemName + " was clkasjdlkfjskldjf");
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

    private woTextRasterizer rasterizer = new woTextRasterizer();
}

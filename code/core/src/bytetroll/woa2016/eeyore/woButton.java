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
import bytetroll.woa2016.runtime.reflect.annot.woOverridable;
import bytetroll.woa2016.runtime.woRuntime;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class woButton extends woCanvasElement implements woCanvasElementInputListener {
    public final woProperty<woAsset> FrameTexture = new woProperty<>(null);
    public final woProperty<String> ButtonText = new woProperty<>(null);
    public final woProperty<woAsset> Font = new woProperty<>(null);

    public woButton(String text, woAsset font, woAsset frameTexture, woVector2 pos ) {
        super(new woCanvasElementDataTypeImage(new woCanvasTexture(frameTexture)));
        super.Position.Set(pos);
        super.InputHook.Set(this);

        ButtonText.Set(text);
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
        /*
        previousFrameTex = (woCanvasTexture)super.Scene.Get().getActors().items[0];
        previousFrameTex.AsTexture().dispose();

        woRuntime.GarbageCollector.BeginCollection();

        woCanvasTexture newTex = new woCanvasTexture(FrameTexture.Get());
        super.Scene.Get().getActors().items[0] = newTex;

        return true;
        */

        return false;
    }


    @Override
    public boolean OnMouseEnter(String elemName, woVector2 pos) {
        return false;
    }

    @Override
    public boolean OnMouseLeave(String elemName, woVector2 pos) {
        return false;
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

    private woCanvasTexture previousFrameTex = null;
}

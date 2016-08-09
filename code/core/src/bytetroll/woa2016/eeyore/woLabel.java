package bytetroll.woa2016.eeyore;

import bytetroll.woa2016.eeyore.canvas.internal.woTextRasterizationJob;
import bytetroll.woa2016.eeyore.canvas.internal.woTextRasterizer;
import bytetroll.woa2016.eeyore.canvas.woCanvasElement;
import bytetroll.woa2016.idoms.woProperty;
import bytetroll.woa2016.io.woAsset;
import bytetroll.woa2016.io.woAssetHandler;

public class woLabel extends woCanvasElement {
    public final woProperty<String> Text = new woProperty<String>(null);

    public woLabel(String text, woAsset font, woAsset targetTex) {
        Text.Set(text);

        this.font = font;
        this.targetTex = targetTex;

        // Lib GDX always stores their binary font files as a .fnt and an accompanying .png.
        fontGlyphImage = woAssetHandler.Find(font.name.replaceFirst("[.][^.]+$", "") + ".png");
    }

    @Override
    public void Draw(woCanvasElementDrawPacket packet) {
        super.BeginDrawing(packet);
        packet.batch.draw(rasterizer.Rasterize(new woTextRasterizationJob(Text.Get(), targetTex, font, fontGlyphImage)).AsTexture(),
                super.Position.Get().x, super.Position.Get().y);
        super.EndDrawing(packet);
    }

    private woTextRasterizer rasterizer = new woTextRasterizer();

    private woAsset font = null;
    private woAsset fontGlyphImage = null;
    private woAsset targetTex = null;
}
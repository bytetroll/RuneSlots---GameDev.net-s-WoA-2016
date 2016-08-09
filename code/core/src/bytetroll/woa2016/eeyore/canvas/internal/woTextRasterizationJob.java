package bytetroll.woa2016.eeyore.canvas.internal;

import bytetroll.woa2016.io.woAsset;

public class woTextRasterizationJob {
    public woTextRasterizationJob(String text, woAsset targetTex, woAsset font, woAsset fontGlyphImage) {
        this.text = text;
        this.targetTex = targetTex;
        this.font = font;
        this.fontGlyphImage = fontGlyphImage;
    }

    public String text = null;
    public woAsset targetTex = null;
    public woAsset font;
    public woAsset fontGlyphImage = null;
}
package bytetroll.woa2016.eeyore.canvas.internal;

// Dynamically rasterizer text so that we can draw it to a woCanvasTexture.

import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class woTextRasterizer {
    public woCanvasTexture Rasterize(woTextRasterizationJob job) {
        final Pixmap texPixmap = new Pixmap(job.targetTex.AsLibGdxHandle());
        final BitmapFont font = new BitmapFont(job.font.AsLibGdxHandle(), job.fontGlyphImage.AsLibGdxHandle(), false);
        final BitmapFont.BitmapFontData fontData = font.getData();
        final Pixmap fontPixmap = new Pixmap(job.fontGlyphImage.AsLibGdxHandle());

        for(char ch : job.text.toCharArray()) {
            final BitmapFont.Glyph glyph = fontData.getGlyph(ch);

            texPixmap.drawPixmap(fontPixmap, ((texPixmap.getWidth() - glyph.width) / 2),
                    ((texPixmap.getHeight() - glyph.height) / 2), glyph.srcX, glyph.srcY, glyph.width, glyph.height);
        }

        return new woCanvasTexture(new Texture(texPixmap));
    }
}

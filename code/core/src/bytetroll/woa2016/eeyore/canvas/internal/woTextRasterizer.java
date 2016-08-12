package bytetroll.woa2016.eeyore.canvas.internal;

// Dynamically rasterizer text so that we can draw it to a woCanvasTexture.

import bytetroll.woa2016.eeyore.canvas.woCanvasTexture;
import bytetroll.woa2016.math.woVector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class woTextRasterizer {
    public woCanvasTexture Rasterize(woTextRasterizationJob job) {

        final Pixmap texPixmap = new Pixmap(job.targetTex.AsLibGdxHandle());
        final BitmapFont font = new BitmapFont(job.font.AsLibGdxHandle(), job.fontGlyphImage.AsLibGdxHandle(), false);
        final BitmapFont.BitmapFontData fontData = font.getData();
        final Pixmap fontPixmap = new Pixmap(job.fontGlyphImage.AsLibGdxHandle());

        woVector2 TexDimens = new woVector2(0, 0);
        int HeightTarget = 0;
        for (char ch : job.text.toCharArray()) {
            final BitmapFont.Glyph glyph = fontData.getGlyph(ch);
            TexDimens.x += glyph.width;
            HeightTarget = Math.max(HeightTarget, glyph.height);
        }
        float Scale = Math.min(1, texPixmap.getWidth() / TexDimens.x);

        woVector2 CenterOffset = new woVector2(
                (texPixmap.getWidth() - TexDimens.x) / 2.0f,
                (texPixmap.getHeight() - (float)HeightTarget) / 2.0f
        );

        int Offset = 0;
        for (char ch : job.text.toCharArray()) {
            final BitmapFont.Glyph glyph = fontData.getGlyph(ch);

            // Blit glyph
            texPixmap.drawPixmap(fontPixmap,
                    Offset + (int)CenterOffset.x,
                    (HeightTarget - glyph.height) + (int)CenterOffset.y,
                    glyph.srcX, glyph.srcY, glyph.width, glyph.height);

            // Increment
            Offset += glyph.width * Scale;
        }

        return new woCanvasTexture(new Texture(texPixmap));


        //final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(job.font.AsLibGdxHandle());
        //FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
    }
}

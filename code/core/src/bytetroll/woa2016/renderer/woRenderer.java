package bytetroll.woa2016.renderer;

import bytetroll.woa2016.io.woAsset;
import bytetroll.woa2016.math.woVector2;
import bytetroll.woa2016.memory.woDestructible;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class woRenderer implements woDestructible {
    @Override
    public void Destruct() {
        batch.dispose();
    }

    public static void DrawText(String text, woVector2 pos, BitmapFont bitmap) {
        batch.begin();
        bitmap.draw(batch, text, pos.x, pos.y);
        batch.end();
    }

    public static BitmapFont RasterizeTrueTypeFont(String text, woAsset font, int fontSize, Color fontColor,
            Color shadowColor, int shadowOffsetX, int shadowOffsetY) {
        final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(font.AsLibGdxHandle());

        FreeTypeFontGenerator.FreeTypeFontParameter  param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.characters = text;
        param.size = fontSize;
        param.color = fontColor == null ? Color.WHITE : fontColor;
        param.shadowColor = shadowColor; // If this is null, shadow isn't rendered.
        param.shadowOffsetX = shadowOffsetX;
        param.shadowOffsetY = shadowOffsetY;

        BitmapFont rasterized = generator.generateFont(param);
        generator.dispose();

        return rasterized;
    }

    private static SpriteBatch batch = new SpriteBatch();
}

package bytetroll.woa2016.eeyore.canvas.internal.util;

import bytetroll.woa2016.io.woAsset;
import bytetroll.woa2016.io.woAssetHandler;
import org.apache.commons.io.FilenameUtils;

public class woFontGlyphMapResolver {
    public static woAsset Resolve(woAsset font) {
        final String glpyhMapName = FilenameUtils.removeExtension(font.name) + ".png";
        return woAssetHandler.Find(glpyhMapName);
    }
}

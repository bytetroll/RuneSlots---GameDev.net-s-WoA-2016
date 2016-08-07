package bytetroll.woa2016.io;

import java.util.ArrayList;
import java.util.List;

public class woAssetHandler {
    public static woAsset Find(String name) {
        final woAsset asset = AttemptCacheFind(name);
        if(asset != null) {
            return asset;
        }

        // @Todo: load asset here.
    }

    private static woAsset AttemptCacheFind(String name) {
        int i = 0;
        do {
            final woAsset asset = cachedAssets.get(i);
            if(asset.name.equals(name)) {
                return asset;
            }
            i++;
        } while(i != cachedAssets.size());
        return null;
    }

    private static List<woAsset> cachedAssets = new ArrayList<>();
}

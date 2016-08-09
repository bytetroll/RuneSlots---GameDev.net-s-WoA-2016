package bytetroll.woa2016.io;

import bytetroll.woa2016.runtime.woRuntime;

import java.util.ArrayList;
import java.util.List;

public class woAssetHandler {
    public static woAsset Find(String name) {
        try {
            final woAsset asset = AttemptCacheFind(name);
            if(asset != null) {
                return asset;
            }

            for(woAssetArchive archive : woAssetArchiveHandler.Cache()) {
                for(woAsset packedAsset : archive.Files()) {
                    if(packedAsset.name.contains(name)) {
                        cachedAssets.add(packedAsset);
                        return packedAsset;
                    }
                }

                //throw new Exception("woAssetArchive failed to locate any asset with name \"" + name + "\"" +  " in " +
                        //"any" + " packed archive.");
            }
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }

        return null; // Never executed.
    }

    private static woAsset AttemptCacheFind(String name) {
        try {
            int i = 0;
            do {
                final woAsset asset = cachedAssets.get(i);
                if (asset.name.equals(name)) {
                    return asset;
                }
                i++;
            } while (i != cachedAssets.size());
            return null;
        } catch(Exception except) {
            // Ignore thrown exception from chacedAssets.Get if the file can't be found.
        }

        return null;
    }

    private static List<woAsset> cachedAssets = new ArrayList<>();
}

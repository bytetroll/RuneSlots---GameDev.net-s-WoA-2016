package bytetroll.woa2016.io;

import bytetroll.woa2016.runtime.woRuntime;

import java.util.ArrayList;
import java.util.List;

public class woAssetArchiveHandler {
    public static void RegisterAssetDirectory(String path) {
        assetDir = path;
    }

    public static void LoadArchive(String path) {
        try {
            if(assetDir == null) {
                throw new Exception("woAssetArchiveHandler tried to load asset archive from an unspecified asset " +
                        "archive directory." );
            }

            cachedArchives.add(new woAssetArchive(path));
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }
    }

    public static void UnloadArchive(String name) {
        try {
            int i = 0;
            do {
                final woAssetArchive archive = cachedArchives.get(i);

                if (name.equals(woPath.GetFilename(archive.Path()))) {
                    cachedArchives.remove(i);
                    return;
                }

                i++;
            } while (i != cachedArchives.size());

            throw new Exception("woAssetArchiveHandler tried to unload an asset archive that doesn't exist");
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }
    }

    public static void FlushCache() {
        cachedArchives.clear();
    }

    public static woAssetArchive FindArchiveByName(String path) {
        try {
            int i = 0;
            do {
                final woAssetArchive archive = cachedArchives.get(i);

                if (path.equals(archive.Path())) {
                    return archive;
                }

                i++;
            } while (i != cachedArchives.size());

            throw new Exception("woAssetArchiveHandler tried to find an asset archive that didn't exist.");
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }

        return null; // Never executed.
    }

    public static String assetDir = null;
    private static List<woAssetArchive> cachedArchives = new ArrayList<>();
}

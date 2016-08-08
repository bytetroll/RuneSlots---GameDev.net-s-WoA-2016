package bytetroll.woa2016.io;

import bytetroll.woa2016.runtime.woRuntime;
import bytetroll.woa2016.sys.woSys;

import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class woAssetArchive {
    public woAssetArchive(String path) {
        this.path = path;

        try {
            archive = new ZipFile(path);

            int i = 0;
            for(Enumeration iter = archive.entries(); iter.hasMoreElements();) {
                final ZipEntry file = (ZipEntry)iter.nextElement();

                if(woSys.IsOSX()) {
                    // Skip Apple's crapp meta folders that automatically get packed into all zips.
                    if(file.getName().contains("__MACOSX")) {
                        continue;
                    }
                }

                woAsset asset = new woAsset();
                asset.name = woPath.GetFilename(file.getName());
                asset.archiveName = woPath.GetFilename(path);
                asset.data.stream = archive.getInputStream(file);

                files.add(asset);
            }
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }
    }

    public String Path() {
        return path;
    }

    public List<woAsset> Files() {
        return files;
    }

    private String path;

    private ZipFile archive = null;
    private List<woAsset> files = new ArrayList<>();
}

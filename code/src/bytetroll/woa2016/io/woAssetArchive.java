package bytetroll.woa2016.io;

import bytetroll.woa2016.runtime.woRuntime;

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

                woAsset asset = new woAsset();
                asset.name = file.getName();
                asset.archiveName = woPath.GetFilename(path);
                asset.data = archive.getInputStream(file);
            }
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }
    }

    public String Path() {
        return path;
    }

    private String path;

    private ZipFile archive = null;
    private List<woAsset> files = new ArrayList<>();
}

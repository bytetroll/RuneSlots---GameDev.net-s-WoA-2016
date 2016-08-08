package bytetroll.woa2016.io;

import bytetroll.woa2016.cli.woCLI;
import bytetroll.woa2016.runtime.woRuntime;
import bytetroll.woa2016.sys.woSys;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class woAssetArchive {
    public woAssetArchive(String path) {
        this.path = path;

        final woFile archive = new woFile(path);
        try {
            ZipInputStream zStream = new ZipInputStream(new FileInputStream(archive.handle));
            ZipEntry file = null;

            while((file = zStream.getNextEntry()) != null) {
                if(woSys.IsOSX()) {
                    // Skip Apple's crapp meta folders that automatically get packed into all zips.
                    if(file.getName().contains("__MACOSX")) {
                        continue;
                    }
                }

                byte buffer[] = new byte[(int)file.getSize()];

                zStream.read(buffer);

                // This is a shitty way to have to do this, but not matter what, Java will not
                // Stop extracting files that have been read.
                final String extractedPath = woRuntime.ExecutionPath() + file.getName();
                if(woIO.FileExists(extractedPath)) {
                    woIO.DeleteFile(extractedPath);

                    woCLI.PrintLine("Deleted file " + extractedPath);
                }

                woAsset asset = new woAsset();
                asset.name = woPath.GetFilename(file.getName());
                asset.archiveName = woPath.GetFilename(path);
                asset.data.stream = new ByteArrayInputStream(buffer);

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

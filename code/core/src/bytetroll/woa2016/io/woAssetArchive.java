package bytetroll.woa2016.io;

import bytetroll.woa2016.cli.woCLI;
import bytetroll.woa2016.runtime.woRuntime;
import bytetroll.woa2016.sys.woSys;

import com.badlogic.gdx.files.FileHandle;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class woAssetArchive {
    public woAssetArchive(String path) {
        this.path = path;

        try {
            final ZipFile archive = new ZipFile(path);
            Enumeration<? extends ZipEntry> entries = archive.entries();

            while(entries.hasMoreElements()) {
                final ZipEntry file = entries.nextElement();

                if(woSys.IsOSX()) {
                    // Skip Apple's crapp meta folders that automatically get packed into all zips.
                    if(file.getName().contains("__MACOSX")) {
                        continue;
                    }
                }


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

                final InputStream iStream = archive.getInputStream(file);
                asset.gdxHandle = new FileHandle(BuildVirtualFile(iStream));
                asset.data.stream = woDataStream.InputStreamToByteInputStream(iStream);

                files.add(asset);
            }

//            final woFile archive = new woFile(path);
//            ZipInputStream zStream = new ZipInputStream(new FileInputStream(archive.handle));
//            ZipEntry file = null;
//
//            while((file = zStream.getNextEntry()) != null) {
//                if(woSys.IsOSX()) {
//                    // Skip Apple's crapp meta folders that automatically get packed into all zips.
//                    if(file.getName().contains("__MACOSX")) {
//                        continue;
//                    }
//                }
//
//                byte buffer[] = new byte[/*(int)file.getSize()*/ 8388608]; // 8mb file buffer.
//                zStream.read(buffer);
//
//                // This is a shitty way to have to do this, but not matter what, Java will not
//                // Stop extracting files that have been read.
//                final String extractedPath = woRuntime.ExecutionPath() + file.getName();
//                if(woIO.FileExists(extractedPath)) {
//                    woIO.DeleteFile(extractedPath);
//
//                    woCLI.PrintLine("Deleted file " + extractedPath);
//                }
//
//                woAsset asset = new woAsset();
//                asset.name = woPath.GetFilename(file.getName());
//                asset.archiveName = woPath.GetFilename(path);
//                asset.gdxHandle = new FileHandle(BuildVirtualFile(LookupFile(zStream, file.getName())));
//                asset.data.stream = new ByteArrayInputStream(buffer);
//
//                files.add(asset);
//            }
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

    private ZipInputStream LookupFile(ZipInputStream zStream, String entry) {
        // Iterating the zStream will position the input stream iterator to the
        // currently processed entry, which is controlled by getNextEntry.
        try {
            for (ZipEntry ent; (ent = zStream.getNextEntry()) != null; ) {
                if (ent.getName().equals(entry)) {
                    return zStream;
                }
            }
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }

        return null; // Never executed.
    }

    /*
    // Have to be careful with this.  Right now, it is our only option, but it could cause memory to run low
    // on larger files.
    private InputStream ConvertZStreamToIStream(ZipInputStream in) {
        try {
            final int bufferSize = 2048;
            int count = 0;
            byte data[] = new byte[bufferSize];
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            while ((count = in.read(data, 0, bufferSize)) != -1) {
                out.write(data);
            }

            return new ByteArrayInputStream(out.toByteArray());
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }

        return null; // Never executed.
    }
    */

    private File BuildVirtualFile(InputStream in) {
        try {
            final File tempFile = File.createTempFile(("runtime_" + tempFileCount), ".fuj");
            tempFile.deleteOnExit();

            try(FileOutputStream out = new FileOutputStream(tempFile)) {
                IOUtils.copy(in, out);
            }

            ++tempFileCount;

            return tempFile;
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }

        return null; //Never executes.
    }

    private String path;

    private ZipFile archive = null;
    private List<woAsset> files = new ArrayList<>();

    private int tempFileCount = 0;
}

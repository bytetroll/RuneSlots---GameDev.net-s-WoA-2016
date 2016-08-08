package bytetroll.woa2016.io;

import java.nio.file.Path;
import java.nio.file.Paths;

public class woPath {
    public static String GetFilename(String filePath) {
        return new woFile(filePath).info.nameWithExt;
    }

    public static String GetFileExt(String filePath) {
        return new woFile(filePath).info.ext;
    }

    public static Path FromString(String path) {
        return Paths.get(path);
    }
}

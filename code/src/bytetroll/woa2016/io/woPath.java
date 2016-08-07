package bytetroll.woa2016.io;

import java.nio.file.Path;
import java.nio.file.Paths;

public class woPath {
    public static String GetFilename(String filePath) {
        final Path path = Paths.get(filePath);
        return path.getFileName().toString();
    }
}

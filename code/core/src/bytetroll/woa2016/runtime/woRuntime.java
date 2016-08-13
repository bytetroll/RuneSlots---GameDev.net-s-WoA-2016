package bytetroll.woa2016.runtime;

import bytetroll.woa2016.runtime.reflect.annot.woReadOnly;
import bytetroll.woa2016.runtime.reflect.internal.woGarbageCollector;
import com.badlogic.gdx.utils.TimeUtils;

import java.io.File;
import java.net.URLDecoder;
import java.security.CodeSource;

public class woRuntime {
    @woReadOnly
    public static final woGarbageCollector GarbageCollector = new woGarbageCollector();

    public static void HandleException(Exception except) {
        woDebug.LogFatal(except.getMessage());
        except.printStackTrace();
        System.exit(-1);
    }

    public static <T extends Throwable> void SuppressException(final T except) {
    }

    public static String ExecutionPath() {
        CodeSource codeSource = woRuntime.class.getProtectionDomain().getCodeSource();
        File jarFile;

        try {
            if (codeSource.getLocation() != null) {
                jarFile = new File(codeSource.getLocation().toURI());
            } else {
                String path = woRuntime.class.getResource(woRuntime.class.getSimpleName() + ".class").getPath();
                String jarFilePath = path.substring(path.indexOf(":") + 1, path.indexOf("!"));
                jarFilePath = URLDecoder.decode(jarFilePath, "UTF-8");
                jarFile = new File(jarFilePath);

                return jarFile.getParentFile().getAbsolutePath();
            }
        } catch(Exception except) {
            HandleException(except);
        }

        return null; // Never executed.
    }

    public static long GenerateTimestamp() {
        return TimeUtils.millis();
    }
}

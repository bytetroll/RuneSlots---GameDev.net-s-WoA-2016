package bytetroll.woa2016.runtime;

import java.lang.annotation.Annotation;

public class woRuntime {
    public static void HandleException(Exception except) {
        woDebug.LogFatal(except.getMessage());
        except.printStackTrace();
        System.exit(-1);
    }

    public static <T extends Throwable> void SuppressException(final T except) {
    }
}

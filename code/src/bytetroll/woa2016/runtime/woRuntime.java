package bytetroll.woa2016.runtime;

import java.lang.annotation.Annotation;

public class woRuntime {
    public static void HandleException(Exception except) {
        except.printStackTrace();
        System.exit(-1);
    }
}

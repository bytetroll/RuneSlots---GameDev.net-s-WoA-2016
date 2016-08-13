package bytetroll.woa2016.devcli.internal;

import bytetroll.woa2016.devcli.annot.woDevCLI;
import com.badlogic.gdx.utils.reflect.Method;

public class woExecutorSandbox {
    public static boolean ExecuteAllowed(woConsoleInterface console, Method callback) {
        return !console.AllowCVarExecution.Get() && callback.isAnnotationPresent(woDevCLI.class);
    }

    public static boolean DisplayAllowed(woConsoleInterface console, Method callback) {
        return !console.AllowCVarListing.Get() && callback.isAnnotationPresent(woDevCLI.class);
    }
}

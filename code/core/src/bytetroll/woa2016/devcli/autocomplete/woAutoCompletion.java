package bytetroll.woa2016.devcli.autocomplete;

import bytetroll.woa2016.devcli.internal.woConsoleCommand;
import bytetroll.woa2016.devcli.internal.woExecutor;
import bytetroll.woa2016.devcli.internal.woExecutorSandbox;
import bytetroll.woa2016.idoms.woProperty;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Method;

public class woAutoCompletion {
    public final woProperty<String> PossibilitySetName = new woProperty<String>(null, true);

    public woAutoCompletion() {
    }

    public void Associate(woExecutor exec, woConsoleCommand cmd) {
        FlushCache();

        PossibilitySetName.Set(cmd.Name.Get());

        Array<Method> callbacks = FindAllAnnotated(exec);
        for(Method meth : callbacks) {
            final String methName = meth.getName();

            if(methName.toLowerCase().startsWith(PossibilitySetName.Get()) && woExecutorSandbox.DisplayAllowed(exec.TargetConsole.Get(), meth)) {
                possibilityCache.add(methName);
            }
        }
    }

    public void FlushCache() {
        possibilityCache.clear();
        PossibilitySetName.Set("");

        cacheIterator = null;
    }

    public boolean CacheEmpty() {
        return possibilityCache.size == 0;
    }

    public boolean SetWith(String name) {
        return PossibilitySetName.equals(name);
    }

    public String NextPossibility() {
        if(!cacheIterator.hasNext) {
            cacheIterator.reset();

            return PossibilitySetName.Get();
        }

        return cacheIterator.next();
    }

    private Array<Method> FindAllAnnotated(woExecutor exec) {
        Array<Method> declared = new Array<>();

        // Walk our main class.
        Method[] methods = ClassReflection.getDeclaredMethods(exec.getClass());
        for(Method meth : methods) {
            if(meth.isPublic()) {
                declared.add(meth);
            }
        }

        // Walk our any super class.
        methods = ClassReflection.getDeclaredMethods(exec.getClass().getSuperclass());
        for(Method meth : methods) {
            if(meth.isPublic()) {
                declared.add(meth);
            }
        }

        return declared;
    }

    private ObjectSet<String> possibilityCache = new ObjectSet<>();
    private ObjectSet.ObjectSetIterator<String> cacheIterator = null;
}

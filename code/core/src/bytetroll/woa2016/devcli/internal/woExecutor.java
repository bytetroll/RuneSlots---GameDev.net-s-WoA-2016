package bytetroll.woa2016.devcli.internal;

import bytetroll.woa2016.idoms.woProperty;
import com.badlogic.gdx.Gdx;

public abstract class woExecutor {
    public final woProperty<woConsoleInterface> TargetConsole = new woProperty<>(null, true);

    // Default CVars
    public final void ExitApplication() {
        Gdx.app.exit();
    }

    public final void PrintCVars() {
        TargetConsole.Get().PrintAllCVars();
    }
}

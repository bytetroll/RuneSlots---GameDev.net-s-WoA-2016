package bytetroll.woa2016.devcli.internal;

import bytetroll.woa2016.idoms.woProperty;
import bytetroll.woa2016.idoms.woReadOnly;
import bytetroll.woa2016.math.woVector2;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public interface woConsoleInterface {
    public final woReadOnly<Integer> ALLOW_UNLIMITED = new woReadOnly<>(-1);

    //----------------------------------------------------------------------------
    // BEGIN PROPERTIES
    //----------------------------------------------------------------------------
    public final woProperty<Integer> MaxAllowedEntries = new woProperty<>(ALLOW_UNLIMITED.Get());
    public final woProperty<Boolean> LogToSystem = new woProperty<>(false);
    public final woProperty<Boolean> RetainOnRefresh = new woProperty<>(false);

    public final woProperty<Boolean> AllowCVarExecution = new woProperty<>(true);
    public final woProperty<Boolean> AllowCVarListing = new woProperty<>(true);

    public final woProperty<InputProcessor> InputHandler = new woProperty<>(null);

    public final woProperty<woVector2> Position = new woProperty<>(woVector2.VEC2_ZERO);
    public final woProperty<Float> PositionTakeupPercentage = new woProperty<>(100.0f);

    public final woProperty<Integer> Width = new woProperty<>(640);
    public final woProperty<Float> WidthTakeupPercentage = new woProperty<>(100.0f);

    public final woProperty<Integer> Height = new woProperty<>(480);
    public final woProperty<Float> HeightTakeupPercentage = new woProperty<>(100.0f);

    public final woProperty<Boolean> Disabled = new woProperty<>(false);
    public final woProperty<Integer> ShowKey = new woProperty<>(Input.Keys.GRAVE);

    public final woProperty<woExecutor> Executor = new woProperty<>(null);

    public final woProperty<Array<String>> ConsoleHistory = new woProperty<>(new Array<String>());
    //----------------------------------------------------------------------------
    // END PROPERTIES
    //----------------------------------------------------------------------------

    public void Draw();
    public boolean MouseInConsole(woVector2 mouseCoords);

    public void Write(woConsolePacket packet);

    public void PrintAllCVars();

    public void Clear();
    public void Refresh();

    public void Execute(String cmd);

    public void FlushInputStream();
}

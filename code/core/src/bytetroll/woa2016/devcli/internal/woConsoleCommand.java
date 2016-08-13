package bytetroll.woa2016.devcli.internal;

import bytetroll.woa2016.idoms.woProperty;
import com.badlogic.gdx.utils.Array;

public class woConsoleCommand {
    public final woProperty<String> Name = new woProperty<>(null);
    public final woProperty<Array<String>> Args = new woProperty<>(new Array<String>());

    public woConsoleCommand(String name, Array<String> args) {
        Name.Set(name);
        Args.Set(args);
    }
}

package bytetroll.woa2016.game.event;

import bytetroll.woa2016.idoms.woProperty;
import bytetroll.woa2016.idoms.woReadOnly;

public class woGameEvent {
    public static final woReadOnly<Object> NO_DATA = new woReadOnly<>(null);

    public final woProperty<String> Name = new woProperty<>(null, true);
    public final woProperty<Object> Data = new woProperty<>(null, true);

    public woGameEvent(String name, Object data) {
        Name.Set(name);
        Data.Set(data);
    }
}

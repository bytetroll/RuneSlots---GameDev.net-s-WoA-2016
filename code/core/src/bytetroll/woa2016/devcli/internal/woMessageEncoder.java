package bytetroll.woa2016.devcli.internal;

import bytetroll.woa2016.idoms.woProperty;
import bytetroll.woa2016.idoms.woReadOnly;
import com.badlogic.gdx.graphics.Color;

public final class woMessageEncoder {
    public static final woReadOnly<String> InfoPrefix = new woReadOnly<>("[Info]...: ");
    public static final woReadOnly<String> WarningPrefix = new woReadOnly<>("[Warning]: ");
    public static final woReadOnly<String> FatalPrefix = new woReadOnly<>("[Fatal]..: ");

    public static final woReadOnly<Color> InfoColor = new woReadOnly<>(Color.WHITE);
    public static final woReadOnly<Color> WarningColor = new woReadOnly<>(Color.YELLOW);
    public static final woReadOnly<Color> FatalColor = new woReadOnly<>(Color.RED);
    public static final woReadOnly<Color> CommandColor = new woReadOnly<>(Color.BLUE);
    public static final woReadOnly<Color> CommandSuccessColor = new woReadOnly<>(Color.GREEN);
    public static final woReadOnly<Color> CommandFailColor = new woReadOnly<>(Color.RED);
    public static final woReadOnly<Color> CLIPointerColor = new woReadOnly<>(Color.GREEN);
    public static final woReadOnly<Color> UnknownResultColor = new woReadOnly<>(Color.ORANGE);

    public static final woProperty<Color> TextColor = new woProperty<Color>(Color.WHITE);
    public static final woProperty<String> PrefixedMessage = new woProperty<>(null);

    public static woEncodedMessage Encode(woMessageType type, String msg) {
        switch(type) {
            case INFO:
                return new woEncodedMessage((InfoPrefix.Get() + msg), InfoColor.Get());
            case WARNING:
                return new woEncodedMessage((WarningPrefix.Get() + msg), WarningColor.Get());
            case FATAL:
                return new woEncodedMessage((FatalPrefix.Get() + msg), FatalColor.Get());
            default:
                return new woEncodedMessage(("\n\n\tUnknown: \t\n" + msg + "\n\n"), UnknownResultColor.Get());
        }
    }
}

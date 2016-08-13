package bytetroll.woa2016.devcli.internal;

import bytetroll.woa2016.idoms.woProperty;
import com.badlogic.gdx.graphics.Color;

public final class woEncodedMessage {
    public final woProperty<Color> TextColor = new woProperty<>(Color.WHITE);
    public final woProperty<String> EncodedString = new woProperty<>(null);
    public final woProperty<woMessageType> Type = new woProperty<>(null);

    public woEncodedMessage(String encoded, Color color) {
        TextColor.Set(color);
        EncodedString.Set(encoded);
    }
}

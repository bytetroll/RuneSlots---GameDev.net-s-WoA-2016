package bytetroll.woa2016.devcli.internal;

import bytetroll.woa2016.exec.woString;
import bytetroll.woa2016.idoms.woProperty;
import bytetroll.woa2016.runtime.woRuntime;
import com.badlogic.gdx.graphics.Color;

import javax.sound.midi.MidiDevice;

public class woConsolePacket {
    public final woProperty<String> Message = new woProperty<>(null);
    public final woProperty<Color> MessageColor = new woProperty<>(null);
    public final woProperty<Long> Timestamp = new woProperty<>(0L);
    public final woProperty<woMessageType> Type = new woProperty<woMessageType>(woMessageType.UNKNOWN);

    public woConsolePacket(woEncodedMessage encoded) {
        Message.Set(encoded.EncodedString.Get());
        MessageColor.Set(encoded.TextColor.Get());
        Timestamp.Set(woRuntime.GenerateTimestamp());

        if(woString.CaseInsensitiveContains(encoded.EncodedString.Get(), "INFO")) {
            Type.Set(woMessageType.INFO);
        } else if(woString.CaseInsensitiveContains(encoded.EncodedString.Get(), "WARNING")) {
            Type.Set(woMessageType.WARNING);
        } else if(woString.CaseInsensitiveContains(encoded.EncodedString.Get(), "FATAL")) {
            Type.Set(woMessageType.FATAL);
        } else {
            Type.Set(woMessageType.UNKNOWN);
        }
    }

    public woConsolePacket(String encodedMsg, Color color) {
        Message.Set(encodedMsg);
        MessageColor.Set(color);
        Timestamp.Set(woRuntime.GenerateTimestamp());
    }
}

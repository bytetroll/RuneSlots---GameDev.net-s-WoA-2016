package bytetroll.woa2016.devcli.steam;

import bytetroll.woa2016.devcli.internal.woConsolePacket;
import bytetroll.woa2016.runtime.woDebug;

public class woLogStreamFile implements woLogStream {
    @Override
    public void Write(woConsolePacket packet) {
        // For now, we will just log to our default debug log file.
        woDebug.LogFile.Get().LogRaw(packet.Message.Get(), packet.Type.Get().toString(), packet.Timestamp.Get());
    }

    @Override
    public void Flush() {
        // Do nothing.
    }
}

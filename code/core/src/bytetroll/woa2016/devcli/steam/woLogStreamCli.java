package bytetroll.woa2016.devcli.steam;

import bytetroll.woa2016.devcli.internal.woConsolePacket;
import bytetroll.woa2016.devcli.internal.woEncodedMessage;

public class woLogStreamCli implements woLogStream {
    @Override
    public void Write(woConsolePacket packet) {
    }

    @Override
    public void Flush() {
        // Do nothing.
    }
}

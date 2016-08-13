package bytetroll.woa2016.devcli.steam;

import bytetroll.woa2016.devcli.internal.woConsolePacket;

public interface woLogStream {
    public void Write(woConsolePacket packet);
    public void Flush();
}
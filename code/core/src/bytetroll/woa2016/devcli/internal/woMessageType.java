package bytetroll.woa2016.devcli.internal;

import bytetroll.woa2016.idoms.woProperty;
import com.badlogic.gdx.graphics.Color;

public enum woMessageType {
    UNKNOWN,

    INFO,
    WARNING,
    FATAL,

    COMMAND,
    COMMAND_SUCCESS,
    COMMAND_FAIL,

    CLI_POINTER
}
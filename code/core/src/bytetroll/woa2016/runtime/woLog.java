package bytetroll.woa2016.runtime;

import bytetroll.woa2016.io.woFile;
import bytetroll.woa2016.io.woIO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class woLog {
    public woLog(String path) {
        this.path = path;

        try {
            if(woIO.FileExists(path)) {
                woIO.DeleteFile(path);
            }

            logFile = woIO.CreateFile(path);

            fWriter = new FileWriter(logFile.handle);
            bWriter = new BufferedWriter(fWriter);
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }
    }

    public void Log(String message, woLogType type) {
        try {
            bWriter.write(String.format("[%s] %s :: %s\n", GenerateTimestamp(), type.toString(), message));
            bWriter.flush();
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }
    }

    public void LogRaw(String msg, String type, long timestamp) {
        try {
            bWriter.write(String.format("%s] $s :: $s\n", type, msg, timestamp));
            bWriter.flush();
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }
    }

    public String LogPath() {
        return path;
    }

    private String GenerateTimestamp() {
        return new SimpleDateFormat("HH.mm.ss").format(new Date());
    }

    private String path;

    private woFile logFile = null;

    // The wrtiers are never closed since we don't have a goold place to
    // close them.  We will just overlook this fact as Java is garbage
    // collected.
    private FileWriter fWriter = null;
    private BufferedWriter bWriter = null;
}

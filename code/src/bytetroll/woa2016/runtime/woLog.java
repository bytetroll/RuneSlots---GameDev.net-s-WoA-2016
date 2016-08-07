package bytetroll.woa2016.runtime;

import bytetroll.woa2016.io.woIO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.security.Timestamp;
import java.util.Date;

public class woLog {
    public woLog(String path) {
        this.path = path;

        try {
            file = new File(path);

            if(!woIO.FileExists(path)) {

            }
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }
    }

    public void Log(String message, woLogType type) {
    }

    public String LogPath() {
        return path;
    }

    private String GenerateTimestamp() {
        //return new Timestamp(new Date().getTime());
        return null;
    }

    private String path;

    private File file = null;
    private FileWriter fWriter = null;
    private BufferedWriter bWriter = null;
}

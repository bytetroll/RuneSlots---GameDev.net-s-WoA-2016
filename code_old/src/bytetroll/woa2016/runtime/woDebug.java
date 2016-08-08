package bytetroll.woa2016.runtime;

public class woDebug {
    public static void LogInfo(String msg) {
        logFile.Log(msg, woLogType.INFO);
    }

    public static void LogWarning(String msg) {
        logFile.Log(msg, woLogType.WARNING);
    }

    public static void LogFatal(String msg) {
        logFile.Log(msg, woLogType.FATAL);
    }

    private static woLog logFile = new woLog("woa2016.log");
}

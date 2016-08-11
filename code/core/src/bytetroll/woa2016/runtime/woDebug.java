package bytetroll.woa2016.runtime;

import bytetroll.woa2016.woBuildConfig;

public class woDebug {
    public static void LogInfo(String msg) {
        if(woBuildConfig.IsDebugBuild.Get()) {
            logFile.Log(msg, woLogType.INFO);
        }
    }

    public static void LogWarning(String msg) {
        if(woBuildConfig.IsDebugBuild.Get()) {
            logFile.Log(msg, woLogType.WARNING);
        }
    }

    public static void LogFatal(String msg) {
        if(woBuildConfig.IsDebugBuild.Get()) {
            logFile.Log(msg, woLogType.FATAL);
        }
    }

    private static woLog logFile = new woLog("woa2016.log");
}

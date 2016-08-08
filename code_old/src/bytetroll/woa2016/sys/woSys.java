package bytetroll.woa2016.sys;

public class woSys {
    public static boolean IsWindows() {
        return osTag.contains("win");
    }

    public static boolean IsOSX() {
        return osTag.contains("mac");
    }

    public static boolean IsLinux() {
        return osTag.contains("nix");
    }

    private static String osTag = System.getProperty("os.name").toLowerCase();
}

package bytetroll.woa2016.cli;

public class woCLI {
    public static void PrintRaw(String msg) {
        Print(msg);
    }

    public static void PrintLine(String msg) {
        Print(msg + System.lineSeparator());
    }

    private static void Print(String msg) {
        System.out.print(msg);
    }
}

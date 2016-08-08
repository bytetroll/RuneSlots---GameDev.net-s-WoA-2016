package bytetroll.woa2016.io;

import java.io.InputStream;
import java.util.Scanner;

public class woAssetDataStream {
    public static String AsString(InputStream stream) {
        final Scanner scanner = new Scanner(stream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}

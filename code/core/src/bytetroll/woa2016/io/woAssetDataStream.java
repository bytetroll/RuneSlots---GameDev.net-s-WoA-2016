package bytetroll.woa2016.io;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class woAssetDataStream {
    public String AsString() {
        final Scanner scanner = new Scanner(stream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    public ByteArrayInputStream stream = null;
}

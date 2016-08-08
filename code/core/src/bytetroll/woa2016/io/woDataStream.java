package bytetroll.woa2016.io;

import bytetroll.woa2016.runtime.woRuntime;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class woDataStream {
    public static ByteArrayInputStream InputStreamToByteInputStream(InputStream stream) {
        try {
            return new ByteArrayInputStream(IOUtils.toByteArray(stream));
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }

        return null; // Never executed.
    }
}

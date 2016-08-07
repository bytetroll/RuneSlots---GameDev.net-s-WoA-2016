package bytetroll.woa2016.io;

import java.io.InputStream;

public class woAsset {
    public woAsset() {
        this.info = new woFileInfo();
        this.data = null;
    }

    public woAsset(woAsset asset) {
        this.info = asset.info;
        this.data = asset.data;
    }

    public woFileInfo info;
    public InputStream data;
}

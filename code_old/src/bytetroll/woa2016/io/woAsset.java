package bytetroll.woa2016.io;

import java.io.InputStream;

public class woAsset {
    public woAsset() {
        name = null;
        archiveName = null;
        data = new woAssetDataStream();
    }

    public woAsset(woAsset asset) {
        this.name = asset.name;
        this.archiveName = asset.archiveName;
        this.data = asset.data;
    }

    public String name;
    public String archiveName;
    public woAssetDataStream data;
}

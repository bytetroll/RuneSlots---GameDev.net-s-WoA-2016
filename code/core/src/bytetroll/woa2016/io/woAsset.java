package bytetroll.woa2016.io;

import com.badlogic.gdx.files.FileHandle;

public class woAsset {
    public woAsset() {
        name = null;
        archiveName = null;
        gdxHandle = null;
        data = new woAssetDataStream();
    }

    public woAsset(woAsset asset) {
        this.name = asset.name;
        this.archiveName = asset.archiveName;
        this.data = asset.data;
        this.gdxHandle = asset.gdxHandle;
    }

    public FileHandle AsLibGdxHandle() {
        return gdxHandle;
    }

    public String name;
    public String archiveName;
    public woAssetDataStream data;

    public FileHandle gdxHandle = null;
}

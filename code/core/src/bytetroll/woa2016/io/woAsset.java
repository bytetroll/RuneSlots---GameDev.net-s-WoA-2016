package bytetroll.woa2016.io;

import com.badlogic.gdx.files.FileHandle;

public class woAsset {
    public woAsset() {
        name = null;
        archiveName = null;
        gdxHandle = null;
        virtualFilePath = null;
        data = new woAssetDataStream();
    }

    public woAsset(woAsset asset) {
        name = asset.name;
        archiveName = asset.archiveName;
        virtualFilePath = asset.virtualFilePath;
        data = asset.data;
        gdxHandle = asset.gdxHandle;
    }

    public FileHandle AsLibGdxHandle() {
        return gdxHandle;
    }

    public String name;
    public String archiveName;
    public String virtualFilePath = null;
    public woAssetDataStream data;

    public FileHandle gdxHandle = null;
}

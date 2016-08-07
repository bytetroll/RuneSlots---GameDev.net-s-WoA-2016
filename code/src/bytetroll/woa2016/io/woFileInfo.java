package bytetroll.woa2016.io;

public class woFileInfo {
    public woFileInfo() {
        this.name = null;
        this.nameWithExt = null;
        this.ext = null;
        this.parentDirPath = null;
        this.path = null;
    }

    public woFileInfo(String name, String nameWithExt, String ext, String containingDir, String path) {
        this.name = name;
        this.nameWithExt = nameWithExt;
        this.ext = ext;
        this.parentDirPath = containingDir;
        this.path = path;
    }

    public String name;
    public String nameWithExt;
    public String ext;
    public String parentDirPath;
    public String path;
}

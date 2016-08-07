package bytetroll.woa2016.io;

import bytetroll.woa2016.runtime.woRuntime;

import java.io.File;
import java.lang.annotation.*;
import java.lang.reflect.Field;

public class woFile {
    public static final String HIDDEN_FILE = "HIDDEN_FILE";

    //----------------------------------------------------------------------------
    // BEGIN ANNOTATION API
    //----------------------------------------------------------------------------
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.LOCAL_VARIABLE)
    public @interface CreateIfNone {
        //public boolean should() default false;
    }
    //----------------------------------------------------------------------------
    // END ANNOTATION API
    //----------------------------------------------------------------------------

    public woFile(String path) {
        path.replace("\\", "/");

        handle = new File(path);
        info = new woFileInfo();

        if(!handle.exists()) {
            try {
                /*
                if(getClass().isAnnotationPresent(CreateIfNone.class)) {
                    handle.createNewFile();
                } else {
                    throw new Exception("CreateIfNone instance was passed a path to a non-existant file.  If you want to " +
                            "create this file, add the @CreateIfNone annotation to this instance's decl. site.");
                }
                */

                handle.createNewFile();
            } catch(Exception except) {
                woRuntime.HandleException(except);
            }
        }

        String[] pathTokens = handle.getName().split("/.(?=[^/.]+$)");
        info.name = pathTokens[0];

        boolean isHiddenFile = false;
        if(pathTokens.length == 1) {
            // We have found a hidden file.  this is usually because we are on a posix system
            // and the filenamestarted with a "."
            info.ext = HIDDEN_FILE;
            isHiddenFile = true;
        } else {
            info.ext = pathTokens[1];
        }
        info.nameWithExt = pathTokens[0] + (isHiddenFile ? "" : pathTokens[1]);
        info.parentDirPath = handle.getParent();
        info.path = path;
    }

    private woFile() {
        // This is needed so that we can construct a new copy of this class with annotation data
        // for annotation checking.
    }

    public woFile(woFile file) {
        handle = file.handle;
        info = file.info;
    }

    public File handle = null;
    public woFileInfo info = null;
}

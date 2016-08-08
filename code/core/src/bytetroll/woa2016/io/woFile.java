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

        info.name = path.substring((path.lastIndexOf(File.separator) + 1), path.lastIndexOf("."));
        info.ext = path.substring(path.lastIndexOf(".") + 1);
        info.nameWithExt = info.name + "." + info.ext;
        info.parentDirPath = handle.getParent();
        info.path = path;

        /*
        String[] filenameTokens = handle.getName().split("/.(?=[^/.]+$)");
        info.name = filenameTokens[0];

        boolean isHiddenFile = false;
        if(filenameTokens.length == 0) {
            // We have found a hidden file.  this is usually because we are on a posix system
            // and the filenamestarted with a "."
            info.ext = HIDDEN_FILE;
            isHiddenFile = true;
        } else {
            info.ext = filenameTokens[1];
        }
        info.nameWithExt = filenameTokens[0] + (isHiddenFile ? "" : filenameTokens[1]);
        info.parentDirPath = handle.getParent();
        info.path = path;
        */
    }

    public woFile(woFile file) {
        handle = file.handle;
        info = file.info;
    }

    private woFile() {
        // This is needed so that we can construct a new copy of this class with annotation data
        // for annotation checking.
    }

    public File handle = null;
    public woFileInfo info = null;
}

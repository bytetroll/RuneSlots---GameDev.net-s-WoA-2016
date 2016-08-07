//---------------------------------------------------------------------------
//>> Copyright (C) 2008 - 2016 Execute Software, LLC.  All rights reserved.
//---------------------------------------------------------------------------
package bytetroll.woa2016.io;

import bytetroll.woa2016.runtime.woRuntime;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

import static java.nio.file.StandardCopyOption.*;

public class woIO {
    public static boolean FileExists(String path) {
        final File file = new File(path);
        return file.exists() && !file.isDirectory();
    }

    public static boolean DirectoryExists(String path) {
        final File dir = new File(path);
        return dir.exists() && dir.isDirectory();
    }

    public static void MakeDirectories(String path) {
        new File(path).mkdirs();
    }

    public static void CreateDirectory(String path) {
        new File(path).mkdir();
    }

    public static void DeleteFile(String path) {
        if(FileExists(path)) {
            new File(path).delete();
        }
    }

    public static String CreateFile(String path) {
        try {
            new File(path).createNewFile();
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }

        return path;
    }

    public static void DeleteDirectory(String path) {
        if(DirectoryExists(path)) {
            final File dir = new File(path);
            String[] dirFiles = dir.list();

            if(dirFiles.length == 0) {
                if(dir.delete()) {
                    return;
                }
            } else {
                for(int index = 0; index < dirFiles.length; index++) {
                    File delete = new File(path + "/" + dirFiles[index]);
                    if(delete.isFile() && !delete.isDirectory()) {
                        delete.delete();
                    } else if(delete.isDirectory()) {
                        DeleteDirectory("" + delete);
                    }
                }

                DeleteDirectory(path);
            }
        }
    }

    public static void Copy(String src, String dest) {
        try {
            Files.copy(new File(src).toPath(), new File(dest).toPath(), REPLACE_EXISTING);
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }
    }

    public static void Rename(String src, String dest) {
        try {
            Files.move(new File(src).toPath(), new File(src).toPath().resolveSibling(dest));
        } catch(Exception except) {
            woRuntime.HandleException(except);
        }
    }

    public static String[] ParseFile(String path) {
        ArrayList<String> lines = new ArrayList<>();

        if(FileExists(path)) {
            try {
                final File file = new File(path);
                final FileReader freader = new FileReader(file);
                final BufferedReader breader = new BufferedReader(freader);

                String line = breader.readLine();
                while(line != null) {
                    lines.add(line);
                    line = breader.readLine();
                }
                freader.close();
                breader.close();
            } catch(Exception except) {
                woRuntime.HandleException(except);
            }
        }

        return lines.toArray(new String[lines.size()]);
    }

    public static boolean DirectoryContainsFileWithType(String ext, String path) {
        File dir = new File(path);

        final File[] listing = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return filename.endsWith("." + ext);
            }
        });

        return listing.length == 1;
    }

    public static String DirectoryContainsFileWithName(String ext, String path) {
        File dir = new File(path);

        final File[] listing = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return filename.endsWith("." + ext);
            }
        });

        return listing[0] != null ? listing[0].getPath() : null;
    }
}

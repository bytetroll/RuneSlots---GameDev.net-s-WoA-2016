package bytetroll.woa2016;

import bytetroll.woa2016.io.*;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class woMain {
    public static void main(String[] args) {
        woRegistry.Load("woa2016/woa2016.reg");

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = woRegistry.FindString("windowing", "title", "woa2016");
        cfg.useGL30 = false;
        cfg.width = woRegistry.FindInt("windowing", "width", 640);

        cfg.height = woRegistry.FindInt("windowing", "height", 400);

        woAssetArchiveHandler.CacheAllInAssetDirectory();
        woAsset asset = woAssetHandler.Find("test2.txt");
        System.out.println(asset.data.AsString());
        asset = woAssetHandler.Find("test.txt");
        System.out.println(asset.data.AsString());

        new LwjglApplication(new woGame());
    }
}
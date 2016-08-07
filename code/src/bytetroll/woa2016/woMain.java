package bytetroll.woa2016;

import bytetroll.woa2016.runtime.woDebug;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class woMain {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "WoA 2016 | Team Bytetroll";
        cfg.useGL30 = false;
        cfg.width = 1200;
        cfg.height = 730;

        for(int i = 0; i < 5; i++) {
            woDebug.LogInfo("Info");
            woDebug.LogWarning("Warning");
            woDebug.LogFatal("Fatal");
        }

        new LwjglApplication(new woGame());
    }
}
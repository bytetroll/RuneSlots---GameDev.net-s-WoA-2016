package bytetroll.woa2016;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class woMain {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "WoA 2016 | Team Bytetroll";
        cfg.useGL30 = false;
        cfg.width = 1200;
        cfg.height = 730;

        new LwjglApplication(new woGame());
    }
}
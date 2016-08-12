package bytetroll.woa2016.desktop;

import bytetroll.woa2016.cli.woCLI;
import bytetroll.woa2016.runtime.reflect.annot.woReadOnly;
import bytetroll.woa2016.woGame;
import bytetroll.woa2016.io.woRegistry;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class DesktopLauncher {
	public static void main (String[] arg) {
        woRegistry.Load("runic/runic.wad");

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = woRegistry.FindString("windowing", "title", "woFramework Game");
        config.width = woRegistry.FindInt("windowing", "width", 800);
        config.height = woRegistry.FindInt("windowing", "height", 600);
		config.resizable = false;

		//System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

		new LwjglApplication(new woGame(), config);
	}
}

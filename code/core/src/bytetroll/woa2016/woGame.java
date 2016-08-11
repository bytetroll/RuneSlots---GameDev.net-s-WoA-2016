package bytetroll.woa2016;

import bytetroll.woa2016.audio.woStreamedSound;
import bytetroll.woa2016.eeyore.woImageView;
import bytetroll.woa2016.io.woAssetArchiveHandler;
import bytetroll.woa2016.io.woAssetHandler;
import bytetroll.woa2016.math.woVector2;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;

public class woGame extends ApplicationAdapter {
	@Override
	public void create () {
        woAssetArchiveHandler.CacheAllInAssetDirectory();

        scene = new woScene();

        // Spawn assets.
        new woStreamedSound(woAssetHandler.Find("woa_audio_background_loop.mp3")).Looped(true).Volume(100).Play();


        scene.SpawnActor(new woImageView(woAssetHandler.Find("woa_ui_slot_frame.png"), new woVector2(0, 0)));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        scene.Think(Gdx.graphics.getDeltaTime());
        scene.Draw();

        if(woBuildConfig.IsDebugBuild.Get()) {
            fps.log();
        }
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	@Override
	public void dispose () {
	    scene.Destroy();
	}

	private woScene scene = null;

    private FPSLogger fps = new FPSLogger();
}

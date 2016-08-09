package bytetroll.woa2016;

import bytetroll.woa2016.eeyore.woCanvas;
import bytetroll.woa2016.eeyore.woImageView;
import bytetroll.woa2016.eeyore.woLabel;
import bytetroll.woa2016.game.woScene;
import bytetroll.woa2016.io.woAsset;
import bytetroll.woa2016.io.woAssetArchiveHandler;
import bytetroll.woa2016.io.woAssetHandler;
import bytetroll.woa2016.math.woVector2;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class woGame extends ApplicationAdapter {
	@Override
	public void create () {
		woAssetArchiveHandler.CacheAllInAssetDirectory();

        scene = new woScene();

        final woAsset font = woAssetHandler.Find("default.fnt");
        final woAsset tex = woAssetHandler.Find("bg_btn5.png");
        scene.SpawnActor(new woLabel("Whoop!", font, tex));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        scene.Think(Gdx.graphics.getDeltaTime());
        scene.Draw();
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
}

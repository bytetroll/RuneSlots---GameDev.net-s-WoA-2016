package bytetroll.woa2016;

import bytetroll.woa2016.audio.woStreamedSound;
import bytetroll.woa2016.eeyore.woImageView;
import bytetroll.woa2016.eeyore.woLabel;
import bytetroll.woa2016.game.woRollButton;
import bytetroll.woa2016.game.woSlotSlider;
import bytetroll.woa2016.io.woAssetArchiveHandler;
import bytetroll.woa2016.io.woAssetHandler;
import bytetroll.woa2016.math.woVector2;

import bytetroll.woa2016.world.woScene;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;
import java.util.List;

public class woGame extends ApplicationAdapter {

	@Override
	public void create () {
        woAssetArchiveHandler.CacheAllInAssetDirectory();

        scene = new woScene();

        // Spawn assets.
        new woStreamedSound(woAssetHandler.Find("runic_audio_background_loop.mp3")).Looped(true).Volume(100).Play();

		scene.SpawnActor(new woImageView(woAssetHandler.Find("runic_ui_slot_frame.png"), new woVector2(0, 0)));
		scene.SpawnActor(new woRollButton(scene));

		for(int i = 0; i < 6; i++) {
			List<woSlotSlider> sliderCols = new ArrayList<>();

			for(int j = 0; j < 3; j++) {
				final woVector2 pos = new woVector2((256 + i), (384 - j));
				//final woVector2 pos = new woVector2(((128 + i) * 256), ((128 - j ) * 384));
				final int startPos = (int)(((Math.random() * 1000) % 7) + 1);

				scene.SpawnActor(new woSlotSlider(scene, pos, startPos));
			}
		}


		//l = new woLabel("9000", new woVector2(1050, 550), woAssetHandler.Find("runic_font_bitmap_default.fnt"), woAssetHandler.Find("runic_ui_text_frame.png"));
		//scene.SpawnActor(l);

		//scene.SpawnActor(new woLabel("Hello, World!", new woVector2(600, 600), woAssetHandler.Find("default.fnt"), woAssetHandler.Find("bg_btn1.png")));
		//scene.SpawnActor(new woButton("T", woAssetHandler.Find("default.fnt"), woAssetHandler.Find("bg_btn1.png"), new woVector2(256, 256)));

        /*
        woEventDispatcher.Subscribe(woGameEvents.BetChanged.Get(), new woGameEventCallback() {
            @Override
            public void Raised(woGameEvent event) {
                woCLI.PrintLine("Event= " + event.Name.Get() + " Data= " + event.Data.Get());
            }
        });

        woEventDispatcher.Raise(woGameEvents.BetChanged.Get(), new woGameEvent(woGameEvents.BetChanged.Get(), "Over 9000 - Freeza"));
        */
    }

    //int i = 0;
    @Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		//l.Text.Set(String.valueOf(i));
		//++i;

        scene.Think(Gdx.graphics.getDeltaTime());
        scene.Draw();

        //woAsset font = woAssetHandler.Find("score.ttf");
        //String text = "Hello, world!";
        //woRenderer.DrawText(text, new woVector2(100, 100), woRenderer.RasterizeTrueTypeFont(text, font, 42, Color.WHITE, Color.BLUE, 2, 2));

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
        //woDestructor.DestoryAll();
    }

	private woScene scene = null;
    // Misc.
    private FPSLogger fps = new FPSLogger();
}

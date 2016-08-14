package bytetroll.woa2016;

import java.util.ArrayList;

import bytetroll.woa2016.ohshit.*;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class woGame implements ApplicationListener {

    private Stage stage;

    SlotLogic slot_logic;

    SpriteBatch spriteBatch;
    BitmapFont font;

    boolean lock_reading_code = false;

    private final AndroidFunctionsInterface androidFunctions;

    int collect_insurer = 0;

    public woGame(AndroidFunctionsInterface desktopFunctions) {
        this.androidFunctions = desktopFunctions;
    }

    @Override
    public void create() {
        //Stage init
        //androidFunctions.SwarmInitiate();

//		int i_temp=0;
//		while(androidFunctions.getScore()==-1)
//		{
////			try {
////				Thread.sleep(1);
////			} catch (InterruptedException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////			i_temp++;
////			if(i_temp<10000)
////				break;
//		}

        androidFunctions.ShowLeaderboardSwarm();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Music music = Gdx.audio.newMusic(Gdx.files.internal("runic/runic_audio_background_loop.mp3"));
        music.setLooping(true);
        music.play();

        //font
        font = new BitmapFont(Gdx.files.internal("runic/runic_font_bitmap_default.fnt"), false);
        font.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        Skin uiSkin;
        uiSkin = new Skin();
        uiSkin.add("default", new BitmapFont());
        //Label style
        LabelStyle label_syle = new LabelStyle();
        label_syle.font = font;
        label_syle.fontColor = Color.BLACK;
        uiSkin.add("default", label_syle);

        Texture texture_background = new Texture("runic/runic_ui_slot_frame.png");
        Background background = new Background(texture_background);

        Texture texture_win = new Texture("runic/placeholders/win.png");
        Image win = new Image(texture_win);
        win.setPosition(0, 400);
        win.setVisible(false);

        //Lines ui

        ArrayList<Image> lines_ui = new ArrayList<Image>();

        Texture texture_line_ui1 = new Texture("runic/runic_ui_line.png");
        Image line_ui1 = new Image(texture_line_ui1);
        line_ui1.setPosition(0, 384);
        line_ui1.setVisible(false);
        lines_ui.add(line_ui1);

        Texture texture_line_ui2 = new Texture("runic/runic_ui_line.png");
        Image line_ui2 = new Image(texture_line_ui2);
        line_ui2.setPosition(0, 256);
        line_ui2.setVisible(false);
        lines_ui.add(line_ui2);

        Texture texture_line_ui3 = new Texture("runic/runic_ui_line.png");
        Image line_ui3 = new Image(texture_line_ui3);
        line_ui3.setPosition(0, 128);
        line_ui3.setVisible(false);
        lines_ui.add(line_ui3);

        //end Lines ui

        ArrayList<ArrayList<Box>> boxes = new ArrayList<ArrayList<Box>>();
        for(int i = 0; i < 6; i++) {
            ArrayList<Box> boxes_columns = new ArrayList<Box>();
            for(int j = 0; j < 3; j++) {
                Box box = new Box(256 + i * 128, 384 - j * 128, (int) ((Math.random() * 1000) % 7 + 1));
                boxes_columns.add(box);
            }
            boxes.add(boxes_columns);
        }

        Label label_credits = new Label("Credits:\n" + androidFunctions.getScore(), uiSkin);
        label_credits.setPosition(25, 160);
        Label label_total_bet = new Label("Total bet:\n1", uiSkin);
        label_total_bet.setPosition(25, 260);
        Label label_last_prize = new Label("Last prize:\n0", uiSkin);
        label_last_prize.setPosition(25, 360);

        Lines lines = new Lines(1050, 500, 3, label_total_bet, lines_ui);

        ArrayList<Double> bets = new ArrayList<Double>();
        bets.add(1.0);
        bets.add(5.0);
        bets.add(10.0);
        bets.add(20.0);
        Bet bet = new Bet(1050, 350, bets, label_total_bet);

        BackgroundButton bg_btn1 = new BackgroundButton(380, 600, 1, stage);
        BackgroundButton bg_btn2 = new BackgroundButton(480, 600, 2, stage);
        BackgroundButton bg_btn3 = new BackgroundButton(580, 600, 3, stage);
        BackgroundButton bg_btn4 = new BackgroundButton(680, 600, 4, stage);
        BackgroundButton bg_btn5 = new BackgroundButton(780, 600, 5, stage);
        BackgroundButton bg_btn6 = new BackgroundButton(880, 600, 6, stage);
        BackgroundButton bg_btn7 = new BackgroundButton(980, 600, 7, stage);
        BackgroundButton bg_btn8 = new BackgroundButton(1080, 600, 8, stage);

        ArrayList<Powerup> powerups = new ArrayList<Powerup>();
        /*powerups
		Powerup pb_btn1 = new Powerup(380, 50, 1,uiSkin,label_credits);
		Powerup pb_btn2 = new Powerup(480, 50, 2,uiSkin,label_credits);
		Powerup pb_btn3 = new Powerup(580, 50, 3,uiSkin,label_credits);
		Powerup pb_btn4 = new Powerup(680, 50, 4,uiSkin,label_credits);
		Powerup pb_btn5 = new Powerup(780, 50, 5,uiSkin,label_credits);
		Powerup pb_btn6 = new Powerup(880, 50, 6,uiSkin,label_credits);
		Powerup pb_btn7 = new Powerup(980, 50, 7,uiSkin,label_credits);
		powerups.add(pb_btn1);
		powerups.add(pb_btn2);
		powerups.add(pb_btn3);
		powerups.add(pb_btn4);
		powerups.add(pb_btn5);
		powerups.add(pb_btn6);
		powerups.add(pb_btn7);
		*/

        slot_logic = new SlotLogic(boxes, androidFunctions.getScore(), win, lines, bet, label_credits, label_total_bet, label_last_prize, powerups, androidFunctions);

        //Barcode reader



        //Collector
        final Label label_collect = new Label("Collect winnings.", uiSkin);
        label_collect.setPosition(0, 50);
        label_collect.setVisible(false);

        label_collect.addCaptureListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                collect_insurer++;
                if(collect_insurer > 5) {
                    collect_insurer = 0;
                    slot_logic.credits = 0;
                    slot_logic.label_credits.setText(slot_logic.getCreditsString());
                    label_collect.setVisible(false);
                }
            }
        });
        // end Collector

        //Collector button
        Texture texture_collector_button = new Texture("runic/placeholders/collector_button.png");
        Image collector_button = new Image(texture_collector_button);
        collector_button.setPosition(800, 0);
        collector_button.addCaptureListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                label_collect.setText("Your session winnings: " + slot_logic.credits + "");
                label_collect.setVisible(true);

            }
        });
        // end Collector button

        lines.slot_logic = slot_logic;
        bet.slot_logic = slot_logic;
		/* powerups
		pb_btn1.slot_logic=slot_logic;
		pb_btn2.slot_logic=slot_logic;
		pb_btn3.slot_logic=slot_logic;
		pb_btn4.slot_logic=slot_logic;
		pb_btn5.slot_logic=slot_logic;
		pb_btn6.slot_logic=slot_logic;
		pb_btn7.slot_logic=slot_logic;
		*/
        Handle handle = new Handle(1050, 200, slot_logic, this);
        slot_logic.handle = handle;

        //Add objects to the stage
        stage.addActor(background);
        for(int i = 0; i < boxes.size(); i++) {
            for(int j = 0; j < boxes.get(i).size(); j++)
                stage.addActor(boxes.get(i).get(j));
        }
        stage.addActor(handle);
        stage.addActor(lines);
        stage.addActor(bet);
        stage.addActor(win);
        stage.addActor(line_ui1);
        stage.addActor(line_ui2);
        stage.addActor(line_ui3);
        stage.addActor(label_credits);
        stage.addActor(label_total_bet);
        stage.addActor(label_last_prize);
        stage.addActor(bg_btn1);
        stage.addActor(bg_btn2);
        stage.addActor(bg_btn3);
        stage.addActor(bg_btn4);
        stage.addActor(bg_btn5);
        stage.addActor(bg_btn6);
        stage.addActor(bg_btn7);
        stage.addActor(bg_btn8);
        stage.addActor(collector_button);
        stage.addActor(label_collect);

		/* powerups
		stage.addActor(pb_btn1);
		stage.addActor(pb_btn2);
		stage.addActor(pb_btn3);
		stage.addActor(pb_btn4);
		stage.addActor(pb_btn5);
		stage.addActor(pb_btn6);
		stage.addActor(pb_btn7);
		*/

        //stage.addActor(button);
        //stage.addActor(introText);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);                                            // #14
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        //slot_logic.credits=androidFunctions.getScore();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        //stage.setViewport(1280, 736, true);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }
}


//
//package bytetroll.woa2016;
//
//import bytetroll.woa2016.audio.woStreamedSound;
//import bytetroll.woa2016.eeyore.woImageView;
//import bytetroll.woa2016.eeyore.woLabel;
//import bytetroll.woa2016.game.woRollButton;
//import bytetroll.woa2016.game.woSlotSlider;
//import bytetroll.woa2016.io.woAssetArchiveHandler;
//import bytetroll.woa2016.io.woAssetHandler;
//import bytetroll.woa2016.math.woVector2;
//
//import bytetroll.woa2016.memory.woDestructor;
//import bytetroll.woa2016.runtime.woRuntime;
//import bytetroll.woa2016.world.woScene;
//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.FPSLogger;
//import com.badlogic.gdx.graphics.GL20;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.concurrent.RunnableFuture;
//import java.util.concurrent.ScheduledThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//public class woGame extends ApplicationAdapter {
//
//	@Override
//	public void create () {
//        woAssetArchiveHandler.CacheAllInAssetDirectory();
//
//        scene = new woScene();
//
//        // Spawn assets.
//        new woStreamedSound(woAssetHandler.Find("runic_audio_background_loop.mp3")).Looped(true).Volume(100).Play();
//
//		scene.SpawnActor(new woImageView(woAssetHandler.Find("runic_ui_slot_frame.png"), new woVector2(0, 0)));
//		scene.SpawnActor(new woRollButton(scene));
//
//		for(int i = 0; i < 6; i++) {
//			List<woSlotSlider> sliderCols = new ArrayList<>();
//
//			for(int j = 0; j < 3; j++) {
//				final woVector2 pos = new woVector2((256 + i), (384 - j));
//				//final woVector2 pos = new woVector2(((128 + i) * 256), ((128 - j ) * 384));
//				final int startPos = (int)(((Math.random() * 1000) % 7) + 1);
//
//				scene.SpawnActor(new woSlotSlider(scene, pos, startPos));
//			}
//		}
//
//
//		//l = new woLabel("9000", new woVector2(1050, 550), woAssetHandler.Find("runic_font_bitmap_default.fnt"), woAssetHandler.Find("runic_ui_text_frame.png"));
//		//scene.SpawnActor(l);
//
//		//scene.SpawnActor(new woLabel("Hello, World!", new woVector2(600, 600), woAssetHandler.Find("default.fnt"), woAssetHandler.Find("bg_btn1.png")));
//		//scene.SpawnActor(new woButton("T", woAssetHandler.Find("default.fnt"), woAssetHandler.Find("bg_btn1.png"), new woVector2(256, 256)));
//
//        /*
//        woEventDispatcher.Subscribe(woGameEvents.BetChanged.Get(), new woGameEventCallback() {
//            @Override
//            public void Raised(woGameEvent event) {
//                woCLI.PrintLine("Event= " + event.Name.Get() + " Data= " + event.Data.Get());
//            }
//        });
//
//        woEventDispatcher.Raise(woGameEvents.BetChanged.Get(), new woGameEvent(woGameEvents.BetChanged.Get(), "Over 9000 - Freeza"));
//        */
//
//    }
//
//    //int i = 0;
//    @Override
//	public void render () {
//		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
//
//		//l.Text.Set(String.valueOf(i));
//		//++i;
//
//        scene.Think(Gdx.graphics.getDeltaTime());
//        scene.Draw();
//
//        //woAsset font = woAssetHandler.Find("score.ttf");
//        //String text = "Hello, world!";
//        //woRenderer.DrawText(text, new woVector2(100, 100), woRenderer.RasterizeTrueTypeFont(text, font, 42, Color.WHITE, Color.BLUE, 2, 2));
//
//        if(woBuildConfig.IsDebugBuild.Get()) {
//            fps.log();
//        }
//	}
//
//	@Override
//	public void resize(int width, int height) {
//	}
//
//	@Override
//	public void pause() {
//	}
//
//	@Override
//	public void resume() {
//	}
//
//	@Override
//	public void dispose () {
//        //woDestructor.DestoryAll();
//    }
//
//	private woScene scene = null;
//    // Misc.
//    private FPSLogger fps = new FPSLogger();
//}
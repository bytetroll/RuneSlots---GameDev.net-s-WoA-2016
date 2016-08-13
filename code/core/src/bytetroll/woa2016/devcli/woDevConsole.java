package bytetroll.woa2016.devcli;

import bytetroll.woa2016.devcli.autocomplete.woAutoCompletion;
import bytetroll.woa2016.devcli.internal.woConsoleHistoryCache;
import bytetroll.woa2016.idoms.woProperty;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;

public class woDevConsole {
    public final woProperty<Window>  ConsoleWindow = new woProperty<>(null);

    public woDevConsole() {
    }

    private woConsoleHistoryCache historyCache = new woConsoleHistoryCache();
    private woAutoCompletion  autoCompleter = new woAutoCompletion();
}
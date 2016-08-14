package bytetroll.woa2016.ohshit;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Background extends Image {
    public Texture texture;

    public Background(Texture texture) {
        super(texture);
        this.texture = texture;
    }
}

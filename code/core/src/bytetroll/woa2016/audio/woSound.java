package bytetroll.woa2016.audio;

import bytetroll.woa2016.idoms.woProperty;
import bytetroll.woa2016.io.woAsset;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class woSound {
    public final woProperty<Sound> Clip = new woProperty<>(null);
    public final woProperty<Long> ID = new woProperty<>(0L);
    public final woProperty<Float> Volume = new woProperty<>(100.0f);
    public final woProperty<Boolean> Looped = new woProperty<>(false);

    public woSound(woAsset sound) {
        Clip.Set(Gdx.audio.newSound(sound.AsLibGdxHandle()));
    }


    public woSound Volume(float volume) {
        Volume.Set(volume);
        return this;
    }

    public woSound Looped(boolean looped) {
        Looped.Set(looped);
        return this;
    }

    public woSound Play() {
        ID.Set(Clip.Get().play());

        if(Looped.Get()) {
            Clip.Get().setLooping(ID.Get(), Looped.Get());
        }

        Clip.Get().setVolume(ID.Get(), Volume.Get());
        return this;
    }

    public woSound Stop() {
        Clip.Get().stop();
        return this;
    }
}

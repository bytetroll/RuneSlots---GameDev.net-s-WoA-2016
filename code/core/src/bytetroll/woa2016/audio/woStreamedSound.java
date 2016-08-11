package bytetroll.woa2016.audio;

import bytetroll.woa2016.idoms.woProperty;
import bytetroll.woa2016.io.woAsset;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
public class woStreamedSound {
    public final woProperty<Music> Clip = new woProperty<>(null);
    public final woProperty<Long> ID = new woProperty<>(0L);
    public final woProperty<Float> Volume = new woProperty<>(100.0f);
    public final woProperty<Boolean> Looped = new woProperty<>(false);

    public woStreamedSound(woAsset sound) {
        Clip.Set(Gdx.audio.newMusic(sound.AsLibGdxHandle()));
    }


    public woStreamedSound Volume(float volume) {
        Volume.Set(volume);
        return this;
    }

    public woStreamedSound Looped(boolean looped) {
        Looped.Set(looped);
        return this;
    }

    public woStreamedSound Play() {
        Clip.Get().play();

        if(Looped.Get()) {
            Clip.Get().setLooping(Looped.Get());
        }

        Clip.Get().setVolume(Volume.Get());

        return this;
    }

    public woStreamedSound Stop() {
        Clip.Get().stop();
        return this;
    }
}

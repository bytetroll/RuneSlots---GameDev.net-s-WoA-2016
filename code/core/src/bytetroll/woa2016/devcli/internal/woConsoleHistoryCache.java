package bytetroll.woa2016.devcli.internal;

import bytetroll.woa2016.idoms.woProperty;
import bytetroll.woa2016.idoms.woReadOnly;

import com.badlogic.gdx.utils.Array;

public final class woConsoleHistoryCache {
    public final woReadOnly<Integer> BeginningIndex = new woReadOnly<>(-1);

    public final woProperty<Array<woConsoleCommand>> HistoryCache = new woProperty<>(new Array<>());

    public void Cache(woConsoleCommand cmd) {
        if((HistoryCache.Get().size > 0) && IsLastIssued(cmd)) {
            if((HistoryCache.Get().size > 0) && IsLastIssued(cmd)) {
                return;
            }
        }

        HistoryCache.Get().insert(0, cmd);
        index = BeginningIndex.Get();
    }

    public woConsoleCommand Next() {
        index++;

        if((HistoryCache.Get().size <= 1)|| (index < 0)) {
            index = BeginningIndex.Get();
            return null;
        }

        return HistoryCache.Get().get(index);
    }

    public woConsoleCommand Previous() {
        index++;

        if(HistoryCache.Get().size == 0) {
            index = BeginningIndex.Get();
            return null;
        } else if(index >= HistoryCache.Get().size) {
            return HistoryCache.Get().get(index);
        }

        return null; // Never executed.
    }

    private boolean IsLastIssued(woConsoleCommand cmd) {
        return cmd.equals(HistoryCache.Get().first());
    }

    private int index = 0;
}

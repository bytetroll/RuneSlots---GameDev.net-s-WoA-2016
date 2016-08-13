package bytetroll.woa2016.game.event;

import java.util.HashMap;
import java.util.Map;

public class woEventDispatcher {
    public static void Subscribe(String evtName, woGameEventCallback callback) {
        subscriptions.put(evtName, callback);
    }

    public static void Unsubscribe(woGameEventCallback callback) {
        for(woGameEventCallback func: subscriptions.values()) {
            if(func.equals(callback)) {
                while(subscriptions.values().remove(func));
                return;
            }
        }
    }

    public static void Raise(String name, woGameEvent event) {
        for(Map.Entry<String, woGameEventCallback> entry : subscriptions.entrySet()) {
            if(entry.getKey().equals(name)) {
                entry.getValue().Raised(event);
            }
        }
    }

    private static Map<String, woGameEventCallback> subscriptions = new HashMap<>();
}

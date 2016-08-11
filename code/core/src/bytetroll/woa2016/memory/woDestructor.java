package bytetroll.woa2016.memory;

import java.util.ArrayList;
import java.util.List;

public class woDestructor {
    public static void AddDestructible(woDestructible obj) {
        objects.add(obj);
    }

    public static void DestoryAll() {
        for(woDestructible obj : objects) {
            obj.Destruct();
        }
    }


    private static List<woDestructible> objects = new ArrayList<>();
}

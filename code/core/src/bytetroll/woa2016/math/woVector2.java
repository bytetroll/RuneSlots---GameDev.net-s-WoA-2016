package bytetroll.woa2016.math;

public class woVector2 {
    public woVector2() {
    }

    public woVector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public woVector2(woVector2 v2) {
        x = v2.x;
        y = v2.y;
    }

    public void Zero() {
        x = 0.0f;
        y = 0.0f;
    }

    public float x = 0;
    public float y = 0;
}

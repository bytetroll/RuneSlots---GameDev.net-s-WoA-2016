package bytetroll.woa2016.math;

public class woVector2 {
    public static final woVector2 VEC2_ZERO = new woVector2(0.0f, 0.0f);

    public woVector2() {
    }

    public woVector2(float x, float y) {
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

    public float x = 0.0f;
    public float y = 0.0f;
}

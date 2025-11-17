package sys;

public class Square {
    public static float triangle(int a, int b, int c) {
        if (a > b + c || b > a + c || c > a + b ||
                a <= 0 || b <= 0 || c <= 0) {
            return 0;
        }
        float s = (a + b + c) / 2.0f;
        return (float) Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}

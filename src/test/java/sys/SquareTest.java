package sys;

import jdk.jfr.Description;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SquareTest {

    @Description("Площадь треугольника Positive")
    @Test(dataProvider = "triangleTestPositiveData", dataProviderClass = SquareTestDataProvider.class)
    public void triangleTestPositive(int a, int b, int c, float expected) {
        assertEquals(expected, Square.triangle(a, b, c), 0.0001);
    }

    @Description("Площадь треугольника Negative")
    @Test(dataProvider = "triangleTestNegativeData", dataProviderClass = SquareTestDataProvider.class)
    public void triangleTestNegative(int a, int b, int c) {
        float expected = 0;
        assertEquals(expected, Square.triangle(a, b, c));
    }
}

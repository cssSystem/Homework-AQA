package sys;

import org.testng.Assert;
import org.testng.annotations.DataProvider;

public class SquareTestDataProvider extends Assert {
    @DataProvider(name = "triangleTestPositiveData")
    public static Object[][] triangleTestPositiveData() {
        return new Object[][]{
                {1, 1, 1, 0.4330f}, //равносторонний
                {3, 4, 5, 6f}, //прямоугольный
                {2, 2, 3, 1.9843f} //остроугольный
        };
    }

    @DataProvider(name = "triangleTestNegativeData")
    public static Object[][] triangleTestNegativeData() {
        return new Object[][]{
                //отрицательная сторона
                {-1, 1, 10},
                {1, -2, 10},
                {10, 1, -3},
                //нулевая сторона
                {0, 1, 10},
                {10, 0, 1},
                {1, 10, 0},
                //равная сторона a+b=c
                {1, 1, 2},
                {1, 2, 1},
                {2, 1, 1},
                //невозможные стороны
                {1, 1, 3},
                {4, 1, 1},
                {1, 5, 1}
        };
    }
}

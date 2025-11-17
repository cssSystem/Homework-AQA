package sys;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SquareTest {

    @DisplayName("Площадь треугольника Positive")
    @ParameterizedTest
    @CsvSource({
            "1, 1, 1, 0.4330", //равносторонний
            "3, 4, 5, 6", //прямоугольный
            "2,2,3, 1.9843" //остроугольный
    })
    public void triangleTestPositive(int a, int b, int c, float expected) {
        assertEquals(expected, Square.triangle(a, b, c), 0.0001);
    }

    @DisplayName("Площадь треугольника Negative")
    @ParameterizedTest
    @CsvSource({
            //отрицательная сторона
            "-1, 1, 10",
            "1, -2, 10",
            "10, 1, -3",
            //нулевая сторона
            "0, 1, 10",
            "10, 0, 1",
            "1, 10, 0",
            //равная сторона a+b=c
            "1, 1, 2",
            "1, 2, 1",
            "2, 1, 1",
            //невозможные стороны
            "1, 1, 3",
            "4, 1, 1",
            "1, 5, 1"

    })
    public void triangleTestNegative(int a, int b, int c) {
        float expected = 0;
        assertEquals(expected, Square.triangle(a, b, c));
    }
}

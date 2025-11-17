package sys;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArithmeticOperationsTest {
    @DisplayName("Plus Positive")
    @ParameterizedTest
    @CsvSource({
            "0,1,1",
            "1,0,1",
            "1,1,2",
            "-1,1,0",
            "1,-1,0",
            "-1,-1,-2",
            "0,0,0"
    })
    public void plusTest(int a, int b, int expected) {
        assertEquals(expected, ArithmeticOperations.plus(a, b));
    }

    @DisplayName("Sub Positive")
    @ParameterizedTest
    @CsvSource({
            "0,1,-1",
            "1,0,1",
            "1,1,0",
            "-1,1,-2",
            "1,-1,2",
            "-1,-1,0",
            "0,0,0"
    })
    public void subTest(int a, int b, int expected) {
        assertEquals(expected, ArithmeticOperations.sub(a, b));
    }

    @DisplayName("Multi Positive")
    @ParameterizedTest
    @CsvSource({
            "0,1,0",
            "1,0,0",
            "1,1,1",
            "-1,1,-1",
            "1,-1,-1",
            "-1,-1,1",
            "0,0,0"
    })
    public void multiTest(int a, int b, int expected) {
        assertEquals(expected, ArithmeticOperations.multi(a, b));
    }

    @DisplayName("Div Positive")
    @ParameterizedTest
    @CsvSource({
            "0,1,0",
            "1,1,1",
            "2,2,1",
            "-2,2,-1",
            "-2,-2,1",
            "3,2,1",
            "-3,2,-1",
            "-3,-2,1"
    })
    public void divTest(int a, int b, int expected) {
        assertEquals(expected, ArithmeticOperations.div(a, b));
    }

    @DisplayName("Div Negative")
    @Test
    public void divTestNegative() {
        int a = 3;
        int b = 0;
        assertThrows(IllegalArgumentException.class, () -> ArithmeticOperations.div(a, b));
    }
}

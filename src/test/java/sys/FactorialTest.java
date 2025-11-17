package sys;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialTest {
    @DisplayName("Факториал Positive")
    @ParameterizedTest
    @CsvSource({//Граничные n/expected
            "0, 1",
            "1, 1",
            "20, 2432902008176640000"
    })
    public void factTestPositive(int n, long expected) {
        assertEquals(expected, Factorial.fact(n));
    }

    @DisplayName("Факториал отрицательного числа и предела 21 Negative")
    @ParameterizedTest
    @ValueSource(ints = {-1, 21})
    public void factNegative(int n) {
        assertThrows(IllegalArgumentException.class, () -> Factorial.fact(n));
    }
}

package sys;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparisonTest {
    @DisplayName("Сравнение a>b Positive")
    @ParameterizedTest
    @CsvSource({
            "1, 0",
            "0,-1",
            "2, 1",
            "-1, -2"
    })
    public void moreTestPositive(int a, int b) {
        assertTrue(Comparison.more(a, b));
    }
    @DisplayName("Сравнение a>b Negative")
    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "0, 0",
            "-1, 0",
            "-2, -1",
            "1, 2"
    })
    public void moreTestNegative(int a, int b) {
        assertFalse(Comparison.more(a, b));
    }
}

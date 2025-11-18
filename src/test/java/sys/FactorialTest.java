package sys;

import jdk.jfr.Description;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class FactorialTest {
    @Description("Факториал Positive")
    @Test(dataProvider = "factTestPositiveData", dataProviderClass = FactorialTestDataProvider.class, groups = "Positive")
    public void factTestPositive(int n, long expected) {
        assertEquals(expected, Factorial.fact(n));
    }

    @Description("Факториал отрицательного числа и предела 21 Negative")
    @Test(dataProvider = "factTestNegativeData", dataProviderClass = FactorialTestDataProvider.class, groups = "Negative")
    public void factNegative(int n) {
        assertThrows(IllegalArgumentException.class, () -> Factorial.fact(n));
    }
}

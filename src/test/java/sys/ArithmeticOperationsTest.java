package sys;


import jdk.jfr.Description;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class ArithmeticOperationsTest {
    @Description("Plus Positive")
    @Test(dataProvider = "plusTestData", dataProviderClass = ArithmeticOperationsTestDataProvider.class)
    public void plusTest(int a, int b, int expected) {
        assertEquals(expected, ArithmeticOperations.plus(a, b));
    }

    @Description("Sub Positive")
    @Test(dataProvider = "subTestData", dataProviderClass = ArithmeticOperationsTestDataProvider.class)
    public void subTest(int a, int b, int expected) {
        assertEquals(expected, ArithmeticOperations.sub(a, b));
    }

    @Description("Multi Positive")
    @Test(dataProvider = "multiTestData", dataProviderClass = ArithmeticOperationsTestDataProvider.class)
    public void multiTest(int a, int b, int expected) {
        assertEquals(expected, ArithmeticOperations.multi(a, b));
    }

    @Description("Div Positive")
    @Test(dataProvider = "divTestData", dataProviderClass = ArithmeticOperationsTestDataProvider.class)
    public void divTest(int a, int b, int expected) {
        assertEquals(expected, ArithmeticOperations.div(a, b));
    }

    @Description("Div Negative")
    @Test
    public void divTestNegative() {
        int a = 3;
        int b = 0;
        assertThrows(IllegalArgumentException.class, () -> ArithmeticOperations.div(a, b));
    }
}

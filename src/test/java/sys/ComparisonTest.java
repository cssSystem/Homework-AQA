package sys;

import jdk.jfr.Description;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ComparisonTest {
    @Description("Сравнение a>b Positive")
    @Test(dataProvider = "moreTestPositiveData", dataProviderClass = ComparisonTestDataProvider.class)
    public void moreTestPositive(int a, int b) {
        assertTrue(Comparison.more(a, b));
    }

    @Description("Сравнение a>b Negative")
    @Test(dataProvider = "moreTestNegativeData", dataProviderClass = ComparisonTestDataProvider.class)
    public void moreTestNegative(int a, int b) {
        assertFalse(Comparison.more(a, b));
    }
}

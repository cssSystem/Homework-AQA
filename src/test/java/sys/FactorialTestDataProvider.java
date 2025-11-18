package sys;

import org.testng.Assert;
import org.testng.annotations.DataProvider;

public class FactorialTestDataProvider extends Assert {
    @DataProvider(name = "factTestPositiveData")
    public Object[][] factTestPositiveData() {
        return new Object[][]{
                //Граничные n/expected
                {0, 1},
                {1, 1},
                {20, 2432902008176640000L}
        };
    }

    @DataProvider(name = "factTestNegativeData")
    public Integer[] factTestNegativeData() {
        return new Integer[]{
                -1,
                21
        };
    }
}

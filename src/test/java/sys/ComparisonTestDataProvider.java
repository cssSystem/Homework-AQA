package sys;

import org.testng.Assert;
import org.testng.annotations.DataProvider;

public class ComparisonTestDataProvider extends Assert {
    @DataProvider(name = "moreTestPositiveData")
    public Object[][] moreTestPositiveData() {
        return new Object[][]{
                {1, 0},
                {0, -1},
                {2, 1},
                {-1, -2}
        };
    }

    @DataProvider(name = "moreTestNegativeData")
    public Object[][] moreTestNegativeData() {
        return new Object[][]{
                {0, 1},
                {0, 0},
                {-1, 0},
                {-2, -1},
                {1, 2}
        };
    }
}

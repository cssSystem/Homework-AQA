package sys;

import org.testng.Assert;
import org.testng.annotations.DataProvider;

public class ArithmeticOperationsTestDataProvider extends Assert {
    @DataProvider(name = "plusTestData")
    public static Object[][] plusTestData() {
        return new Object[][]{
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 2},
                {-1, 1, 0},
                {1, -1, 0},
                {-1, -1, -2},
                {0, 0, 0}
        };
    }
    @DataProvider(name = "subTestData")
    public static Object[][] subTestData() {
        return new Object[][]{
                {0,1,-1},
                {1,0,1},
                {1,1,0},
                {-1,1,-2},
                {1,-1,2},
                {-1,-1,0},
                {0,0,0}
        };
    }

    @DataProvider(name = "multiTestData")
    public static Object[][] mulTestData() {
        return new Object[][]{
                {0,1,0},
                {1,0,0},
                {1,1,1},
                {-1,1,-1},
                {1,-1,-1},
                {-1,-1,1},
                {0,0,0}
        };
    }

    @DataProvider(name = "divTestData")
    public static Object[][] divTestData() {
        return new Object[][]{
                {0,1,0},
                {1,1,1},
                {2,2,1},
                {-2,2,-1},
                {-2,-2,1},
                {3,2,1},
                {-3,2,-1},
                {-3,-2,1}
        };
    }
}

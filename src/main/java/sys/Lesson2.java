package sys;

public class Lesson2 {
    public static void printThreeWords() {
        System.out.println("Orange\nBanana\nApple");
    }

    public static void checkSumSign() {
        int a = 0, b = 2;
        int c = a + b;
        if (c >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor() {
        int value = 1;
        String res;
        if (value <= 0) {
            res = "Крассный";
        } else if (value <= 100) {
            res = "Желтый";
        } else {
            res = "Зеленый";
        }
        System.out.println(res);
    }

    public static void compareNumbers() {
        int a = 1, b = 2;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static boolean amountLimit(int val1, int val2) {
        var val = val1 + val2;
        return val >= 10 && val <= 20;
    }

    public static void anInt(int val) {
        System.out.println(val < 0 ? "отрицательное" : "положительное");
    }

    public static boolean anBool(int val) {
        return val < 0;
    }

    public static void repitLine(String text, int quan) {
        if (quan < 0) {
            quan = 0;
        }
        for (int i = 0; i < quan; i++) {
            System.out.println(text);
        }
    }

    public static boolean altitudeYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        }
        return year % 4 == 0;
    }

    public static int[] arrayPerRevolution(int[] arr) {
        for (int i = 0; arr.length > i; i++) {
            arr[i] = arr[i] < 1 ? 1 : 0;
        }
        return arr;
    }

    public static int[] arrayToOneHundred() {
        int[] arr = new int[100];
        for (int i = 0; arr.length > i; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    public static int[] task12() {
        int[] arr = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 6};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
        return arr;
    }

    public static int[][] task13(int posNum) {
        int[][] arr = new int[posNum][posNum];
        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = 1;
            arr[i][arr.length - 1 - i] = 1;
        }
        return arr;
    }

    public static int[] task14(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }
}

package sys;

public class ArithmeticOperations {
    public static int plus(int a, int b) {
        return a + b;
    }

    public static int sub(int a, int b) {
        return a - b;
    }

    public static int multi(int a, int b) {
        return a * b;
    }

    public static int div(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Попытка деления на ноль");
        }
        return a / b;
    }
}

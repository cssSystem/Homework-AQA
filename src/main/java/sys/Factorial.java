package sys;

public class Factorial {
    public static long fact(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Попытка вычислить факториал от отрицательного числа");
        }
        if (n > 20) {
            throw new IllegalArgumentException("Ограничение вычисления факториала от 20");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}

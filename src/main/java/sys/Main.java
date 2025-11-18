package sys;

import java.util.Arrays;

import static sys.lesson_2.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("1__________________");
        printThreeWords();
        System.out.println("2__________________");
        checkSumSign();
        System.out.println("3__________________");
        printColor();
        System.out.println("4__________________");
        compareNumbers();
        System.out.println("5__________________");
        System.out.println(amountLimit(10, 11));
        System.out.println("6__________________");
        anInt(0);
        System.out.println("7__________________");
        System.out.println(anBool(-1));
        System.out.println("8__________________");
        repitLine("ajax", -2);
        System.out.println("9__________________");
        System.out.println(altitudeYear(800));
        System.out.println("10_________________");
        System.out.println(Arrays.toString(arrayPerRevolution(new int[]{1, 2, 0, -1})));
        System.out.println("11_________________");
        System.out.println(Arrays.toString(arrayToOneHundred()));
        System.out.println("12_________________");
        System.out.println(Arrays.toString(task12()));
        System.out.println("13_________________");
        System.out.println(Arrays.deepToString(task13(5)));
        System.out.println("14_________________");
        System.out.println(Arrays.toString(task14(5, 6)));
    }


}
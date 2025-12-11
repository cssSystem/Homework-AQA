package sys;

import sys.exc.MyArrayDataException;
import sys.exc.MyArraySizeException;

public class Main {
    public static void main(String[] args) {
        String[][] arr = {//массив 4*5
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"}
        };
//            String[][] arr = {//массив с буквой
//                    {"1", "2", "3", "f"},
//                    {"1", "2", "3", "4"},
//                    {"1", "2", "3", "4"},
//                    {"1", "2", "3", "4"}
//            };
//            String[][] arr = {//массив нормальный
//                    {"1", "2", "3", "4"},
//                    {"1", "2", "3", "4"},
//                    {"1", "2", "3", "4"},
//                    {"1", "2", "3", "4"}
//            };
        try {
            ArrayTruble.ArrayTruble(arr);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println();
            System.err.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            for (StackTraceElement elem : e.getStackTrace()) {
                System.err.println(" в " + elem);
            }
        }

        System.out.println(ArrayTruble.ArrayIndexTruble(arr, 1, 7));
    }
}

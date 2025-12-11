package sys;

import sys.exc.MyArrayDataException;
import sys.exc.MyArraySizeException;

public class ArrayTruble {

    public static void ArrayTruble(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length > 4) {
            throw new MyArraySizeException();
        }
        int summ = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i].length > 4) {
                    throw new MyArraySizeException();
                }
                try {
                    summ += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }

            }
        }
        System.out.println(summ);
    }

    public static String ArrayIndexTruble(String[][] arr, int i, int j) {
        try {
            return arr[i][j];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println();
            System.err.println(e.getClass().getSimpleName() + ": " +
                    "Выход за пределы массива: " +
                    "в массиве отсутствует ячейка с индексом | " + i + " x " + j + " |");
            for (StackTraceElement elem : e.getStackTrace()) {
                System.err.println(" в " + elem);
            }
            return null;
        }
    }
}

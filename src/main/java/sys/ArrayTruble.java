package sys;

import sys.exc.MyArrayDataException;
import sys.exc.MyArraySizeException;

public class ArrayTruble {
    public static void ArrayTruble(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length > 4) {
            throw new MyArraySizeException();
        } else if (arr[0].length > 4) {
            throw new MyArraySizeException();
        }
        int summ = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    summ += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }

            }
        }
        System.out.println(summ);
    }

    public static void ArrayIndexTruble(String[][] arr) {
        try {
            var a = arr[arr.length];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}

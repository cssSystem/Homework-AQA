package sys;

import sys.exc.MyArrayDataException;
import sys.exc.MyArraySizeException;

public class Main {
    public static void main(String[] args) {
        try {
//            String[][] arr = {//массив 4*5
//                    {"1", "2", "3", "4"},
//                    {"1", "2", "3", "4"},
//                    {"1", "2", "3", "4"},
//                    {"1", "2", "3", "4"},
//                    {"1", "2", "3", "4"}
//            };
//            String[][] arr = {//массив с буквой
//                    {"1", "2", "3", "f"},
//                    {"1", "2", "3", "4"},
//                    {"1", "2", "3", "4"},
//                    {"1", "2", "3", "4"}
//            };
            String[][] arr = {//массив нормальный
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"}
            };
            ArrayTruble.ArrayTruble(arr);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }

        ArrayTruble.ArrayIndexTruble(new String[5][5]);
        System.out.println("Программа завершена");
    }
}

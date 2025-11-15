package sys.exc;

public class MyArrayDataException extends Exception {
    public MyArrayDataException(int i, int j) {
        super("Неверный формат данных в ячейке [" + i + "][" + j + "]");
    }
}

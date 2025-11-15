package sys.exc;

public class MyArraySizeException extends Exception {
    public MyArraySizeException() {
        super("Массив находится вне допустимого размеру 4х4");
    }
}

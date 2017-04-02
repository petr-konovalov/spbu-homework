package c1_s2.konovalov.task3_2;

public interface Printer {
    void print(int[][] array) throws PrintException;

    class PrintException extends Exception {

    }
}

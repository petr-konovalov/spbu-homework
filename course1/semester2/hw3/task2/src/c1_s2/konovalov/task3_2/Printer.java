package c1_s2.konovalov.task3_2;

/** Печатает элементы массива, обходя по спирали из центра */
public interface Printer {
    /**
     * Печатает массив
     *
     * @param array выводимый массив
     * @throws PrintException ошибка печати
     */
    void print(int[][] array) throws PrintException;

    /** Ошибка печати */
    class PrintException extends Exception {

    }
}

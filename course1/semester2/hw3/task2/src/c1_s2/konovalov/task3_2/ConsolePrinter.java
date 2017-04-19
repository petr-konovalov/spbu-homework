package c1_s2.konovalov.task3_2;

/** Печатает элементы массива в консоль, обходя по спирали из центра */
public class ConsolePrinter extends PrintLogic {
    @Override
    public void print(int[][] array) throws PrintException {
        try {
            printArray(array);
            System.out.println();
        } catch(Exception e) {
            throw new PrintException();
        }
    }

    @Override
    void printNumber(int number) {
        System.out.print(number + " ");
    }
}

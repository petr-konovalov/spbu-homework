package c1_s2.konovalov.task3_2;

import java.io.File;
import java.io.PrintWriter;

/** Печатает элементы массива в файл, обходя по спирали из центра */
public class FilePrinter extends PrintLogic {
    private final String fileName = "output.txt";
    private File file = new File(fileName);
    private PrintWriter output;

    @Override
    public void print(int[][] array) throws PrintException {
        try {
            file.createNewFile();
            output = new PrintWriter(file.getAbsoluteFile());

            printArray(array);
        } catch(Exception e) {
            throw new PrintException();
        } finally {
            output.close();
        }
    }

    @Override
    void printNumber(int number) {
        output.print(number + " ");
    }
}

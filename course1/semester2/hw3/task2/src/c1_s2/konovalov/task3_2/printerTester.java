package c1_s2.konovalov.task3_2;

import org.junit.Test;
import java.util.Random;
import static junit.framework.TestCase.fail;

public class PrinterTester {
    @Test
    public void testConsolePrinter() {
        Printer printer = new ConsolePrinter();

        testPrinter(printer, 3, 15);
    }

    @Test
    public void testFilePrinter() {
        Printer printer = new FilePrinter();

        testPrinter(printer, 5, 127);
    }

    private  void testPrinter(Printer printer, int minSize, int maxSize) {
        try {
            for (int size = minSize; size <= maxSize; size += 2)
                printer.print(generateArray(size, size));
        } catch(Printer.PrintException e) {
            fail();
        }
    }

    private int[][] generateArray(int width, int height) {
        final int maxNumber = 100;
        Random generator = new Random();
        int[][] array = new int[height][width];

        for (int i = 0; i < height; ++i)
            for (int j = 0; j < width; ++j)
                array[i][j] = (generator.nextInt() % maxNumber + maxNumber) % maxNumber;

        return array;
    }
}

package c1_s2.konovalov.task3_2;

import java.io.File;
import java.io.PrintWriter;

public class FilePrinter implements Printer {
    private final String fileName = "output.txt";
    private File file = new File(fileName);
    private PrintWriter output;
    private int printPositionX;
    private int printPositionY;

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

    private void printArray(int[][] array) {
        int step = 1;
        printPositionX = array.length / 2;
        printPositionY = array.length / 2;

        while (step < array.length) {
            printLeft(array, step);
            printDown(array, step);
            ++step;
            printRight(array, step);
            printUp(array, step);
            ++step;
        }
        printLeft(array, step);
        output.println();
    }

    private void printLeft(int[][] array, int step) {
        for (int i = printPositionX - step; printPositionX > i; --printPositionX)
            output.print(array[printPositionY][printPositionX] + " ");
    }

    private void printDown(int[][] array, int step) {
        for (int i = printPositionY + step; printPositionY < i; ++printPositionY)
            output.print(array[printPositionY][printPositionX] + " ");
    }

    private void printRight(int[][] array, int step) {
        for (int i = printPositionX + step; printPositionX < i; ++printPositionX)
            output.print(array[printPositionY][printPositionX] + " ");
    }

    private void printUp(int[][] array, int step) {
        for (int i = printPositionY - step; printPositionY > i; --printPositionY)
            output.print(array[printPositionY][printPositionX] + " ");
    }
} 

package c1_s2.konovalov.task3_2;

public class ConsolePrinter implements Printer {
    private int printPositionX;
    private int printPositionY;

    @Override
    public void print(int[][] array) throws PrintException {
        try {
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
            System.out.println();
        } catch(Exception e) {
            throw new PrintException();
        }
    }

    private void printLeft(int[][] array, int step) {
        for (int i = printPositionX - step; printPositionX > i; --printPositionX)
            System.out.print(array[printPositionY][printPositionX] + " ");
    }

    private void printDown(int[][] array, int step) {
        for (int i = printPositionY + step; printPositionY < i; ++printPositionY)
            System.out.print(array[printPositionY][printPositionX] + " ");
    }

    private void printRight(int[][] array, int step) {
        for (int i = printPositionX + step; printPositionX < i; ++printPositionX)
            System.out.print(array[printPositionY][printPositionX] + " ");
    }

    private void printUp(int[][] array, int step) {
        for (int i = printPositionY - step; printPositionY > i; --printPositionY)
            System.out.print(array[printPositionY][printPositionX] + " ");
    }
}

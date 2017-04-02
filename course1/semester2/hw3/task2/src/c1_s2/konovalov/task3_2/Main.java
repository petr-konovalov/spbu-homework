package c1_s2.konovalov.task3_2;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] argc) {
        Scanner input = new Scanner(System.in);
        Printer printer = getPrinter(input);


        if (printer != null) {
            System.out.println("printer was created");
            printCommand();
            executeCommands(printer, input);
        }
    }

    private static void executeCommands(Printer printer, Scanner input) {
        int[][] array = null;
        int command = -1;

        while (command != 0) {
            System.out.print("please, enter command: ");
            command = input.nextInt();

            if (command == 1 || command == 2)
                array = createArray(command, input);
            else if (command == 3)
                executeCommandPrintArray(printer, array);
        }
    }

    private static Printer getPrinter(Scanner input) {
        int command = -1;
        Printer printer = null;

        printCommandCreatingPrinter();
        while (command != 0 && printer == null) {
            System.out.print("please, enter command: ");
            command = input.nextInt();
            printer = createPrinter(command);
        }

        return printer;
    }

    private static Printer createPrinter(int printerType) {
        switch (printerType) {
            case 1:
                return new ConsolePrinter();
            case 2:
                return new FilePrinter();
            default:
                return null;
        }
    }

    private static void printCommandCreatingPrinter() {
        System.out.println(
                "commands:\n" +
                "0 - exit\n" +
                "1 - create console printer\n" +
                "2 - create file printer"
        );
    }

    private static void printCommand() {
        System.out.println(
                "commands:\n" +
                "0 - exit\n" +
                "1 - generate array\n" +
                "2 - enter array\n" +
                "3 - print array"
        );
    }

    private static int getArraySize(Scanner input) {
        int size = 0;

        System.out.print("please, enter array size: ");
        size = input.nextInt();
        while (size > 0 && size % 2 == 0) {
            System.out.print("An odd and positive number is needed, please enter another number: ");
            size = input.nextInt();
        }

        return size;
    }

    private static int[][] createArray(int creatingWay, Scanner input) {
        int size = getArraySize(input);

        switch (creatingWay) {
            case 1:
                return generateArray(size);
            case 2:
                return enterArray(size, input);
            default:
                return null;
        }
    }

    private static int[][] generateArray(int size) {
        final int maxNumber = 100;
        int[][] array = new int[size][size];
        Random generator = new Random();
        int number = 0;

        System.out.println("array: ");
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                number = ((generator.nextInt()) % maxNumber + maxNumber) % maxNumber;
                array[i][j] = number;
                System.out.format("%3d", number);
            }
            System.out.println();
        }

        return array;
    }

    private static int[][] enterArray(int size, Scanner input) {
        int[][] array = new int[size][size];

        System.out.println("please, enter array: ");
        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                array[i][j] = input.nextInt();

        return array;
    }

    private static void executeCommandPrintArray(Printer printer, int[][] array) {
        if (array == null)
            System.out.println("array was not generated");
        else {
            try {
                printer.print(array);
                System.out.println("array was printed");
            } catch(Printer.PrintException e) {
                System.out.println("Print error, array was not printed");
            }
        }
    }
}

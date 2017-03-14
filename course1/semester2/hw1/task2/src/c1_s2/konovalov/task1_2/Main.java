package c1_s2.konovalov.task1_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] argc)
    {
        List numbers = new List();
        Scanner input = new Scanner(System.in);
        int command = -1;

        printCommands();

        while (command != 0) {
            System.out.print("enter command: ");
            command = input.nextInt();
            executeCommand(numbers, command, input);
        }
    }

    private static void executeCommand(List numbers, int command, Scanner input) {
        switch (command) {
            case 1:
                executeCommandInsertElement(numbers, input);
                break;
            case 2:
                executeCommandDeleteElement(numbers, input);
                break;
            case 3:
                executeCommandLocateElement(numbers, input);
                break;
            case 4:
                executeCommandRetrieveElement(numbers, input);
                break;
            case 5:
                executeCommandPrintList(numbers);
                break;
        }
    }

    private static void executeCommandInsertElement(List numbers, Scanner input) {
        System.out.print("enter position: ");
        int position = input.nextInt();

        System.out.print("enter value: ");
        int value = input.nextInt();

        if (numbers.insertElement(value, position))
            System.out.println("element added successfully");
        else
            System.out.println("error: element was not added");
    }

    private static void executeCommandDeleteElement(List numbers, Scanner input) {
        System.out.print("enter position: ");
        int position = input.nextInt();

        if (numbers.deleteElement(position))
            System.out.println("element deleted successfully");
        else
            System.out.println("error: element was not deleted");
    }

    private static void executeCommandLocateElement(List numbers, Scanner input) {
        System.out.print("enter value: ");
        int value = input.nextInt();
        int position = numbers.locateElement(value);

        if (position == -1)
            System.out.println("element not found");
        else
            System.out.println("element is in position " + position);
    }

    private static void executeCommandRetrieveElement(List numbers, Scanner input) {
        System.out.print("enter position: ");
        int position = input.nextInt();

        int value = numbers.retrieveElement(position);
        System.out.println("value: " + value);
    }

    private static void executeCommandPrintList(List numbers) {
        if (numbers.isEmpty())
            System.out.println("list is empty");
        else {
            int value = numbers.getFirstElement();

            while (!numbers.hasReachedLastElement()) {
                System.out.print(value + " ");
                value = numbers.getNextElement();
            }
            System.out.println(value);
        }
    }

    private static void printCommands() {
        System.out.print(
                "commands:\n" +
                "exit             - 0\n" +
                "insert element   - 1\n" +
                "delete element   - 2\n" +
                "locate element   - 3\n" +
                "retrieve element - 4\n" +
                "print list       - 5\n"
        );
    }
}

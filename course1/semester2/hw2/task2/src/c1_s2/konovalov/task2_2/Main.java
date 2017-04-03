package c1_s2.konovalov.task2_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] argc) {
        List<Integer> numbers = null;
        Scanner input = new Scanner(System.in);
        int command = -1;

        printCreateListCommands();

        while (command != 0 && command != 1 && command != 2) {
            System.out.print("enter command: ");
            command = input.nextInt();
            numbers = initializeList(command);
        }

        printCommands();
        while (command != 0) {
            System.out.print("enter command: ");
            command = input.nextInt();
            executeCommand(numbers, input, command);
        }
    }

    private static List<Integer> initializeList(int type) {
        List<Integer> numbers = null;
        if (type == 1) {
            numbers = new LinkedList<>();
            System.out.println("linked list created");
        }
        else if (type == 2) {
            numbers = new DoublyLinkedList<>();
            System.out.println("doubly linked list created");
        }

        return numbers;
    }

    private static void printCreateListCommands() {
        System.out.println(
                "commands:\n" +
                "0 - exit\n" +
                "1 - create linked list\n" +
                "2 - create doubly linked list"
        );
    }

    private static void printCommands() {
        System.out.println(
                "commands: \n" +
                "0 - exit\n" +
                "1 - print\n" +
                "2 - size\n" +
                "3 - retrieve\n" +
                "4 - insert\n" +
                "5 - remove"
        );
    }

    private static void executeCommand(List<Integer> numbers, Scanner input, int command) {
        switch (command) {
            case 1:
                executeCommandPrint(numbers);
                break;
            case 2:
                executeCommandSize(numbers);
                break;
            case 3:
                executeCommandRetrieve(numbers, input);
                break;
            case 4:
                executeCommandInsert(numbers, input);
                break;
            case 5:
                executeCommandRemove(numbers, input);
                break;
        }
    }

    private static void executeCommandRetrieve(List<Integer> numbers, Scanner input) {
        try {
            System.out.print("enter position: ");
            int position = input.nextInt();

            Integer number = numbers.retrieve(position);
            System.out.println("retrieved number: " + number);
        } catch(List.ListOutOfBoundException e) {
            System.out.println("out of the list, number was not be retrieved");
        }
    }

    private static void executeCommandSize(List<Integer> numbers) {
        System.out.println("list size: " + numbers.size());
    }

    private static void executeCommandPrint(List<Integer> numbers) {
        try {
            System.out.print("list: ");
            for (Integer number = numbers.getNext(); number != null; number = numbers.getNext())
                System.out.print(number + " ");
            System.out.println();
        } catch (List.ListIsEmptyException e) {
            System.out.println("is empty");
        }
    }

    private static void executeCommandInsert(List<Integer> numbers, Scanner input) {
        try {
            System.out.print("enter position: ");
            int position = input.nextInt();
            System.out.print("enter value: ");
            Integer element = input.nextInt();

            numbers.insert(position, element);
            System.out.println("number added");
        } catch(List.ListOutOfBoundException e) {
            System.out.println("out of the list, number was not be added");
        }
    }

    private static void executeCommandRemove(List<Integer> numbers, Scanner input) {
        try {
            System.out.print("enter position: ");
            int position = input.nextInt();

            numbers.remove(position);
            System.out.println("number removed");
        } catch(List.ListOutOfBoundException e) {
            System.out.println("out of the list, number was not be removed");
        }
    }
}

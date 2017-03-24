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
                "1 - get next\n" +
                "2 - get previous\n" +
                "3 - get first\n" +
                "4 - insert in current position\n" +
                "5 - remove from current position\n" +
                "6 - insert\n" +
                "7 - remove"
        );
    }

    private static void executeCommand(List<Integer> numbers, Scanner input, int command) {
        switch (command) {
            case 1:
                executeCommandGetNext(numbers);
                break;
            case 2:
                executeCommandGetPrevious(numbers);
                break;
            case 3:
                executeCommandGetFirst(numbers);
                break;
            case 4:
                executeCommandInsertCurrentPosition(numbers, input);
                break;
            case 5:
                executeCommandRemoveCurrentPosition(numbers);
                break;
            case 6:
                executeCommandInsert(numbers, input);
                break;
            case 7:
                executeCommandRemove(numbers, input);
                break;
            case 8:
                executeCommandPrintList(numbers);
                break;
        }
    }

    private static void executeCommandGetNext(List<Integer> numbers) {
        try {
            Integer number = numbers.getNext();
            if (number == null)
                number = numbers.getNext();

            System.out.println("next number: " + number);
        } catch(List.ListIsEmptyException e) {
            System.out.println("list is empty");
        }
    }

    private static void executeCommandGetPrevious(List<Integer> numbers) {
        try {
            Integer number = numbers.getPrevious();
            if (number == null)
                number = numbers.getPrevious();

            System.out.println("previous number: " + number);
        } catch(List.ListIsEmptyException e) {
            System.out.println("list is empty");
        }
    }

    private static void executeCommandGetFirst(List<Integer> numbers) {
        try {
            System.out.println("first element: " + numbers.getFirst());
        } catch(List.ListIsEmptyException e) {
            System.out.println("list is empty");
        }
    }

    private static void executeCommandInsertCurrentPosition(List<Integer> numbers, Scanner input) {
        System.out.print("enter number: ");
        Integer element = input.nextInt();

        numbers.insertCurrentPosition(element);
        System.out.println("element added");
    }

    private static void executeCommandRemoveCurrentPosition(List<Integer> numbers) {
        try {
            numbers.removeCurrentPosition();
            System.out.println("element removed");
        } catch(List.ListIsEmptyException e) {
            System.out.println("list is empty");
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
            System.out.println("out of the list");
        }
    }

    private static void executeCommandRemove(List<Integer> numbers, Scanner input) {
        try {
            System.out.print("enter position: ");
            int position = input.nextInt();

            numbers.remove(position);
            System.out.println("number removed");
        } catch(List.ListOutOfBoundException e) {
            System.out.println("out of the list");
        }
    }

    private static void executeCommandPrintList(List<Integer> numbers) {
        if (numbers.isEmpty())
            System.out.println("list is empty");
        else {
            System.out.print("list: ");
            try {
                for (Integer number = numbers.getFirst(); numbers.hasNext(); number = numbers.getNext())
                    System.out.print(number + " ");
            } catch (List.ListIsEmptyException e) {

            }
            System.out.println();
        }
    }
}

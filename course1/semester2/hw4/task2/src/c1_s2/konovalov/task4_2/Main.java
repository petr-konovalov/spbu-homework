package c1_s2.konovalov.task4_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] argv) {
        HashTable table = new HashTable();
        Scanner input = new Scanner(System.in);
        int command = -1;

        printCommand();
        while (command != 0) {
            System.out.print("please enter command: ");
            command = input.nextInt();
            executeCommand(table, command, input);
        }
    }

    private static void printCommand() {
        System.out.println(
                "commands:\n" +
                "0 - exit\n" +
                "1 - add element\n" +
                "2 - delete element\n" +
                "3 - find element\n" +
                "4 - print statistic\n" +
                "5 - change hash function"
        );
    }

    private static void executeCommand(HashTable table, int command, Scanner input) {
        switch (command) {
            case 1:
                executeCommandAddElement(table, input);
                break;
            case 2:
                executeCommandRemoveElement(table, input);
                break;
            case 3:
                executeCommandFindElement(table, input);
                break;
            case 4:
                executeCommandPrintStatistic(table);
                break;
            case 5:
                executeCommandChangeHashFunction(table, input);
                break;
        }
    }

    private static void executeCommandAddElement(HashTable table, Scanner input) {
        System.out.print("Please enter key: ");
        String key = input.next();
        System.out.print("Please enter value: ");
        String value = input.next();
        try {
            table.add(key, value);
            System.out.println("Element added");
        } catch (HashTable.ReAddKeyException e) {
            System.out.println("Sorry key already added");
        }
    }

    private static void executeCommandRemoveElement(HashTable table, Scanner input) {
        System.out.print("Please enter key: ");
        String key = input.next();
        try {
            table.delete(key);
            System.out.println("Element deleted");
        } catch (HashTable.KeyNotFoundException e) {
            System.out.println("Sorry key not found");
        }
    }

    private static void executeCommandFindElement(HashTable table, Scanner input) {
        System.out.print("Please enter key: ");
        String key = input.next();
        try {
            String value = table.get(key);
            System.out.println("Value: " + value);
        } catch (HashTable.KeyNotFoundException e) {
            System.out.println("Sorry key not found");
        }
    }

    private static void executeCommandPrintStatistic(HashTable table) {
        HashTable.Statistic statistic = table.getTableStatistic();
        System.out.println(
                "Chain count: " + statistic.chainCount + "\n" +
                "Table size: " + statistic.tableSize + "\n" +
                "Load factor: " + statistic.loadFactor + "\n" +
                "Conflicting element count: " + statistic.conflictCount + "\n" +
                "Max length of conflict chain: " + statistic.maxConflictChainLength
        );
    }

    private static void executeCommandChangeHashFunction(HashTable table, Scanner input) {
        System.out.print("Please enter the number of the hash function (1, 2 or 3): ");
        int numberHashFunction = input.nextInt();

        switch (numberHashFunction) {
            case 1:
                table.setHashFunction(new FirstHash());
                break;
            case 2:
                table.setHashFunction(new SecondHash());
                break;
            case 3:
                table.setHashFunction(new ThirdHash());
                break;
            default:
                System.out.println("Sorry function number "  + numberHashFunction + " does not exist");
        }
    }
}

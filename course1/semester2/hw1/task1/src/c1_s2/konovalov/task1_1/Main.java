package c1_s2.konovalov.task1_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] argc) {
        Scanner input = new Scanner(System.in);
        int command = -1;
        Stack numbers = new Stack();
        printCommands();
        while (command != 0) {
            System.out.print("Enter command: ");
            command = input.nextInt();
            executeCommand(command, numbers, input);
        }
    }

    private static void printCommands() {
        System.out.print("Commands:\n" +
                         "0 - exit\n" +
                         "1 - push number\n" +
                         "2 - pop number\n");
    }

    private static void executeCommand(int command, Stack numbers, Scanner input) {
        switch (command) {
            case 1:
                System.out.print("Enter number: ");
                numbers.push(input.nextInt());
                break;
            case 2:
                if (numbers.isEmpty())
                    System.out.println("Stack is empty");
                else
                    System.out.println("Next value: " + numbers.pop());
                break;
        }
    }
}

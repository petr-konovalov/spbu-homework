package c1_s2.konovalov.task2_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] argc) {
        Scanner input = new Scanner(System.in);
        Calculator calculator = new Calculator();
        int command = -1;

        printCommands();

        while (command != 0) {
            System.out.print("please, enter a command: ");
            command = input.nextInt();
            if (command == 1)
                executeCommandEnterExpression(calculator, input);
        }
    }

    private static void printCommands() {
        System.out.println("commands:\n"+
                           "0 - exit\n" +
                           "1 - enter expression");
    }

    private static void executeCommandEnterExpression(Calculator calculator, Scanner input) {
        try {
            System.out.println("please enter expression:");

            input.nextLine();
            for (char inputSymbol : input.nextLine().toCharArray())
                calculator.addExpressionSymbol(inputSymbol);
            calculator.addExpressionSymbol('=');

            System.out.println("result: " + calculator.getResult());
            calculator.resetExpression();
        } catch (Calculator.SyntaxErrorException e) {
            System.out.println("syntax error, expression can't be calculate");
        }
    }
}

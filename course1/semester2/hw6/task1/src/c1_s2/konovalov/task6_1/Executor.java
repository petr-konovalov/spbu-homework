package c1_s2.konovalov.task6_1;

public class Executor {
    public static int executeOperation(char operation, int firstOperand, int secondOperand) {
        switch (operation) {
            case '+':
                return firstOperand + secondOperand;
            case '-':
                return firstOperand - secondOperand;
            case '*':
                return firstOperand * secondOperand;
            case '/':
                return firstOperand / secondOperand;
        }

        return 0;
    }
}

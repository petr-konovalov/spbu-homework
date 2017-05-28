package c1_s2.konovalov.task5_2.sample;

/** считате значение выражение без учёта приоритета операций */
class Calculator {
    private double result = 0;
    private double value = 0;
    private char operation = '#';

    /**
     * добавляет цифру в выражение
     * @param digit добавляемая цифра
     */
    void addDigit(int digit) {
        if (operation == '=')
            operation = '#';

        value = value * 10 + digit;
    }

    /**
     * добавляет операцию в выражение
     * @param newOperation добавляемая операция может принимать значения '+', '-', '*', '/'
     */
    void addOperation(char newOperation) {
        result = executeOperation(operation, result, value);
        value = 0;
        operation = newOperation;
    }

    /**
     * возвращает значение текущего операнда
     * @return возвращает значение текущего операнда
     */
    double getValue() {
        if (operation == '=')
            return result;

        return value;
    }

    /**
     * возвращает промежуточный результат
     * @return возвращает промежуточный результат
     */
    double getResult() {
        return result;
    }

    /**
     * считает и возвращает результат выражения
     * @return возвращает результат выражения
     */
    double calculateResult() {
        addOperation('=');
        return result;
    }

    private double executeOperation(char operation, double firstOperand, double secondOperand) {
        switch(operation) {
            case '+':
                return firstOperand + secondOperand;
            case '-':
                return firstOperand - secondOperand;
            case '*':
                return firstOperand * secondOperand;
            case '/':
                return firstOperand / secondOperand;
            case '=':
                return firstOperand;
        }

        return secondOperand;
    }
}

package c1_s2.konovalov.task6_1;

/** выполняет арифмитические операции */
class Executor {
    /**
     * выполняет арифмитическую операцию
     * @param operation арифмитическая операция '+', '-', '*' или '/'
     * @param firstOperand первый операнд
     * @param secondOperand второй операнд
     * @return возвращает результат арифмитической операции
     */
    static int executeOperation(char operation, int firstOperand, int secondOperand) {
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

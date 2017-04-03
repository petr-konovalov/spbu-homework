package c1_s2.konovalov.task2_1;

public class Calculator {
    private Stack<Float> numbers = new LinkedStack<>();
    private Stack<Character> operations = new ArrayStack<>();
    private int currentNumber = 0;
    private boolean currentNumberPushed = false;

    class SyntaxErrorException extends Exception {

    }

    public void addExpressionSymbol(char symbol) throws SyntaxErrorException {
        try {
            if (isDigit(symbol))
                addExpressionDigit(symbol - '0');
            else if (isSign(symbol))
                addExpressionOperation(symbol);
            else if (symbol == '(')
                addExpressionOpeningBracket();
            else if (symbol == ')')
                addExpressionClosingBracket();
            else if (symbol != ' ')
                throw new SyntaxErrorException();
        } catch(Stack.StackIsEmptyException e) {
            throw new SyntaxErrorException();
        }
    }

    public void resetExpression() {
        numbers.clear();
        operations.clear();

        currentNumber = 0;
        currentNumberPushed = false;
    }

    private void addExpressionDigit(int digit) {
        if (currentNumber == 0)
            currentNumber = digit;
        else
            currentNumber = currentNumber * 10 + digit;

        currentNumberPushed = false;
    }

    private void pushCurrentNumber() {
        if (!currentNumberPushed) {
            numbers.push((float)currentNumber);
            currentNumber = 0;
            currentNumberPushed = true;
        }
    }

    private void addExpressionOperation(char operation) throws Stack.StackIsEmptyException {
        pushCurrentNumber();

        while (!operations.isEmpty() && getPriority(operation) <= getPriority(operations.top()))
            executeExpressionTopOperation();

        operations.push(operation);
    }

    private void addExpressionOpeningBracket() {
        currentNumberPushed = false;
        operations.push('(');
    }

    private void addExpressionClosingBracket() throws Stack.StackIsEmptyException {
        pushCurrentNumber();

        while (operations.top() != '(')
            executeExpressionTopOperation();

        operations.pop();
    }

    public float getResult() throws SyntaxErrorException {
        try {
            return numbers.top();
        } catch(Stack.StackIsEmptyException e) {
            throw new SyntaxErrorException();
        }
    }

    private void executeExpressionTopOperation() throws Stack.StackIsEmptyException {
        float secondOperand = numbers.pop();
        float firstOperand = numbers.pop();

        numbers.push(executeOperation(operations.pop(), firstOperand, secondOperand));
    }

    private float executeOperation(char operation, float firstOperand, float secondOperand) {
        switch (operation) {
            case '+':
                return firstOperand + secondOperand;
            case '-':
                return firstOperand - secondOperand;
            case '*':
                return firstOperand * secondOperand;
            case '/':
                return firstOperand / secondOperand;
            default:
                return 0.0f;
        }
    }

    private boolean isDigit(char symbol) {
        return '0' <= symbol && symbol <= '9';
    }

    private boolean isSign(char symbol) {
        return symbol == '+' || symbol == '-' || symbol == '*'
                || symbol == '/' || symbol == '=';
    }

    private int getPriority(char operation) {
        switch (operation) {
            case '=':
                return 0;
            case '(':
                return 1;
            case '+':
            case '-':
                return 2;
            case '*':
            case '/':
                return 3;
            default:
                return -1;
        }
    }
}

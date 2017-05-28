package c1_s2.konovalov.task5_2.sample;

import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.fail;

public class CalculatorTester {
    private Calculator calculator = new Calculator();
    private Random generator = new Random();
    private char operation = '#';
    private double result = 0;
    private double value = 0;
    final private char[] operations = {'+', '-', '*', '/'};

    @Test
    public void testCalculator() {
        final int maxNumberLength = 5;
        final int testCount = 16;
        int operandCount = 2;

        for (int i = 0; i < testCount; ++i) {
            for (int j = 1; j < operandCount; ++j) {
                calculator.addOperation(operation);
                if (result != calculator.getResult())
                    fail();
                addNextValue(operation == '/', maxNumberLength);
                operation = operations[generator.nextInt(4)];
            }
            if (result != calculator.calculateResult())
                fail();
            operandCount *= 2;
        }
    }

    private void addNextValue(boolean noZero, int maxNumberLength) {
        value = 0;

        if (noZero) {
            addDigit(generator.nextInt(9) + 1);
            --maxNumberLength;
        }

        int nextDigit = generator.nextInt(10);
        while (maxNumberLength > 0 && nextDigit != 0) {
            addDigit(nextDigit);
            nextDigit = generator.nextInt(10);
            --maxNumberLength;
        }

        if (calculator.getValue() != value)
            fail();

        result = executeOperation(operation, result, value);
    }

    private void addDigit(int digit) {
        calculator.addDigit(digit);
        value = value * 10 + digit;
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
        }

        return secondOperand;
    }
}

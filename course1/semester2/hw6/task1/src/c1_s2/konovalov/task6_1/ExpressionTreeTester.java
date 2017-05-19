package c1_s2.konovalov.task6_1;

import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.fail;

public class ExpressionTreeTester {
    private String debugRecord;
    private String leftChild;
    private String rightChild;
    private int leftResult = 0;
    private int rightResult = 0;

    @Test
    public void ExpressionTreeTest() {
        final int testCount = 1024;

        for (int i = 0; i < testCount; ++i) {
            generateDebugRecord();
            ExpressionTree tree = new CreatorTree(debugRecord);
            if (getResult() != tree.getResult())
                fail();
        }
    }

    private int getResult() {
        return Executor.executeOperation(debugRecord.charAt(1), leftResult, rightResult);
    }

    private void generateDebugRecord() {
        final int MaxOperationCount = 128;
        Random generator = new Random();

        leftResult = getNextNumber(generator);
        rightResult = getNextNumber(generator);

        leftChild = String.valueOf(leftResult);
        rightChild = String.valueOf(rightResult);

        for (int i = 0; i < MaxOperationCount - 1; ++i) {
            int actionCode = generator.nextInt(6);
            executeAction(actionCode, generator);
        }

        debugRecord = merge(leftChild, rightChild, getNextOperation(rightResult, generator));
    }

    private void executeAction(int actionCode, Random generator) {
        switch (actionCode) {
            case 0:
                addInLeftTreeToLeft(generator);
                break;
            case 1:
                addInLeftTreeToRight(generator);
                break;
            case 2:
                addInRightTreeToRight(generator);
                break;
            case 3:
                addInRightTreeToLeft(generator);
                break;
            case 4:
                mergeToLeft(generator);
                break;
            case 5:
                mergeToRight(generator);
                break;
        }
    }

    private void addInLeftTreeToLeft(Random generator) {
        int Number = getNextNumber(generator);
        char operation = getNextOperation(leftResult, generator);

        leftResult = Executor.executeOperation(operation, Number, leftResult);
        leftChild = merge(String.valueOf(Number), leftChild, operation);
    }

    private void addInLeftTreeToRight(Random generator) {
        int Number = getNextNumber(generator);
        char operation = getNextOperation(Number, generator);

        leftResult = Executor.executeOperation(operation, leftResult, Number);
        leftChild = merge(leftChild, String.valueOf(Number), operation);
    }

    private void addInRightTreeToRight(Random generator) {
        int Number = getNextNumber(generator);
        char operation = getNextOperation(Number, generator);

        rightResult = Executor.executeOperation(operation, rightResult, Number);
        rightChild = merge(rightChild, String.valueOf(Number), operation);
    }

    private void addInRightTreeToLeft(Random generator) {
        int Number = getNextNumber(generator);
        char operation = getNextOperation(rightResult, generator);

        rightResult = Executor.executeOperation(operation, Number, rightResult);
        rightChild = merge(String.valueOf(Number), rightChild, operation);
    }

    private void mergeToLeft(Random generator) {
        int Number = getNextNumber(generator);
        char operation = getNextOperation(rightResult, generator);

        leftResult = Executor.executeOperation(operation, leftResult, rightResult);
        leftChild = merge(leftChild, rightChild, operation);
        rightChild = String.valueOf(Number);
        rightResult = Number;
    }

    private void mergeToRight(Random generator) {
        int Number = getNextNumber(generator);
        char operation = getNextOperation(rightResult, generator);

        rightResult = Executor.executeOperation(operation, leftResult, rightResult);
        rightChild = merge(leftChild, rightChild, operation);
        leftChild = String.valueOf(Number);
        leftResult = Number;
    }

    private String merge(String leftChild, String rightChild, char operation) {
        return "(" + String.valueOf(operation) +
                " " + leftChild + " " + rightChild + ")";
    }

    private char getNextOperation(int secondOperand, Random generator) {
        final char operations[] = {'+', '-', '*', '/'};

        if (secondOperand == 0)
            return operations[generator.nextInt(3)];
        else
            return operations[generator.nextInt(4)];
    }

    private int getNextNumber(Random generator) {
        final int NumberRange = 32;
        return generator.nextInt(NumberRange);
    }
}

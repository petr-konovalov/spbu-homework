package c1_s2.konovalov.task5_2.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    private TextArea resultDisplay;

    @FXML
    private TextArea expressionDisplay;

    private double result = 0;
    private long value = 0;
    private int operation = 0;

    public void buttonOneAction(ActionEvent event) {
        addValueDigit(1);
        printValue(value);
    }

    public void buttonTwoAction(ActionEvent event) {
        addValueDigit(2);
        printValue(value);
    }

    public void buttonThreeAction(ActionEvent event) {
        addValueDigit(3);
        printValue(value);
    }

    public void buttonFourAction(ActionEvent event) {
        addValueDigit(4);
        printValue(value);
    }

    public void buttonFiveAction(ActionEvent event) {
        addValueDigit(5);
        printValue(value);
    }

    public void buttonSixAction(ActionEvent event) {
        addValueDigit(6);
        printValue(value);
    }

    public void buttonSevenAction(ActionEvent event) {
        addValueDigit(7);
        printValue(value);
    }

    public void buttonEightAction(ActionEvent event) {
        addValueDigit(8);
        printValue(value);
    }

    public void buttonNineAction(ActionEvent event) {
        addValueDigit(9);
        printValue(value);
    }

    public void buttonZeroAction(ActionEvent event) {
        addValueDigit(0);
        printValue(value);
    }

    public void buttonPlusAction(ActionEvent event) {
        expressionDisplay.appendText(String.valueOf(value) + "+");
        printResult();
        operation = 1;
    }

    public void buttonMinusAction(ActionEvent event) {
        expressionDisplay.appendText(String.valueOf(value) + "-");
        printResult();
        operation = 2;
    }

    public void buttonMultiplyAction(ActionEvent event) {
        expressionDisplay.appendText(String.valueOf(value) + "*");
        printResult();
        operation = 3;
    }

    public void buttonDivideAction(ActionEvent event) {
        expressionDisplay.appendText(String.valueOf(value) + "/");
        printResult();
        operation = 4;
    }

    public void buttonEqualsAction(ActionEvent event) {
        expressionDisplay.setText("");
        printResult();
        operation = 0;
    }

    private void printResult() {
        result = executeOperation(operation, result, value);
        value = 0;
        printValue(result);
    }

    private double executeOperation(int operation, double firstOperand, double secondOperand) {
        switch(operation) {
            case 1:
                return firstOperand + secondOperand;
            case 2:
                return firstOperand - secondOperand;
            case 3:
                return firstOperand * secondOperand;
            case 4:
                return firstOperand / secondOperand;
        }

        return secondOperand;
    }

    private void addValueDigit(int digit) {
        value = value * 10 + digit;
    }

    private void printValue(long value) {
        resultDisplay.setText(String.valueOf(value));
    }

    private void printValue(double value) {
        resultDisplay.setText(String.valueOf(value));
    }
}

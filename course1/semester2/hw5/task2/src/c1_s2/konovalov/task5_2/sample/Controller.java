package c1_s2.konovalov.task5_2.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    private TextArea resultDisplay;

    @FXML
    private TextArea expressionDisplay;

    private Calculator calculator = new Calculator();

    public void buttonDigitAction(ActionEvent event) {
       calculator.addDigit(((Button)event.getSource()).getText().charAt(0) - '0');
       printValue(calculator.getValue());
    }

    public void buttonOperationAction(ActionEvent event) {
        expressionDisplay.appendText(String.valueOf(calculator.getValue()) +
                ((Button)event.getSource()).getText().charAt(0));
        calculator.addOperation(((Button)event.getSource()).getText().charAt(0));
        printValue(calculator.getResult());
    }

    public void buttonEqualsAction(ActionEvent event) {
        expressionDisplay.setText("");
        printValue(calculator.calculateResult());
    }

    private void printValue(Object value) {
        resultDisplay.setText(String.valueOf(value));
    }
}

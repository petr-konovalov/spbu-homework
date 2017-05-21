package c1_s2.konovalov.task5_1.sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class Controller {
    private int operation;
    private int firstNumber;
    private int secondNumber;

    @FXML
    private TextArea firstNumberDisplay;

    @FXML
    private TextArea secondNumberDisplay;

    @FXML
    private TextArea resultDisplay;

    @FXML
    private ScrollBar firstNumberScroller;

    @FXML
    private ScrollBar secondNumberScroller;

    @FXML
    private ChoiceBox<String> operationBox;

    public void initialize() {
        operationBox.setItems(FXCollections.observableArrayList("+", "-", "*", "/"));
        operationBox.setValue("+");
        operationBox.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> changeValue(newValue)
        );
        firstNumberScroller.setUnitIncrement(-1);
        secondNumberScroller.setUnitIncrement(-1);
    }

    public void firstNumberScroll(MouseEvent event) {
        firstNumber = (int) firstNumberScroller.getValue();
        firstNumberDisplay.setText(String.valueOf(firstNumber));
        printResult();
    }

    public void secondNumberScroll(MouseEvent event) {
        secondNumber = (int) secondNumberScroller.getValue();
        secondNumberDisplay.setText(String.valueOf(secondNumber));
        printResult();
    }

    private double calculate() {
        switch (operation) {
            case 0:
                return firstNumber + secondNumber;
            case 1:
                return firstNumber - secondNumber;
            case 2:
                return firstNumber * secondNumber;
            case 3:
                return (double) firstNumber / secondNumber;
            default:
                return 0;
        }
    }

    private void printResult() {
        final int maxLength = 14;
        String result = String.valueOf(calculate());
        resultDisplay.setText(result.substring(0, min(result.length(), maxLength)));
    }

    private int min(int firstNumber, int secondNumber) {
        if (firstNumber < secondNumber)
            return firstNumber;
        else
            return secondNumber;
    }

    private void changeValue(Number newValue) {
        operation = newValue.intValue();
        printResult();
    }
}

package c1_s2.konovalov.task7_2.sample;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Controller {
    private CrossZero crossZero = new CrossZero();
    private boolean gameOver = false;

    @FXML
    private Label victoryTitle;

    private Button filledButtons[] = new Button[9];
    private int filledButtonsCount = 0;

    public void cellAction(ActionEvent event) {
        Button currentButton = (Button)(event.getSource());

        if (currentButton.getText().equals("") && !gameOver) {
            currentButton.setText(crossZero.makeMovement(currentButton.getId().charAt(6) - '0',
                    currentButton.getId().charAt(7) - '0') ? "X" : "O");
            filledButtons[filledButtonsCount++] = currentButton;

            int gameStatus = crossZero.getGameStatus();
            if (gameStatus != 0) {
                gameOver = true;
                printVictoryTitle(gameStatus);
                victoryTitle.setVisible(true);
                restartGame();
            }
        }
    }

    private void printVictoryTitle(int victoryType) {
        switch (victoryType) {
            case 1:
                victoryTitle.setText("Победил X");
                break;
            case 2:
                victoryTitle.setText("Победил O");
                break;
            case 3:
                victoryTitle.setText("Ничья");
                break;
        }
    }

    private void clearFilledButtons() {
        for (int i = 0; i < filledButtonsCount; ++i)
            filledButtons[i].setText("");
        filledButtonsCount = 0;
    }

    private void restartGame() {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            crossZero = new CrossZero();
            gameOver = false;
            victoryTitle.setVisible(false);
            clearFilledButtons();
        });
        pause.play();
    }
}

package c1_s2.konovalov.task7_2.sample;

import org.junit.Test;

import static c1_s2.konovalov.task7_2.sample.CrossZero.GameStatus.*;
import static junit.framework.TestCase.fail;

public class CrossZeroTester {
    private CrossZero crossZero;
    private int[][] field = new int[3][3];

    @Test
    public void  crossZeroTest() {
        checkCombinations(3, 2);
        checkCombinations(3, 3);
        checkCombinations(4, 3);
        checkCombinations(4, 4);
        checkCombinations(5, 4);
    }

    private void checkCombinations(final int crossesCount, final int zeroesCount) {
        boolean[] crossesCombination = generateFirstCombination(9, crossesCount);

        do {
            boolean[] zeroesCombination = generateFirstCombination(9 - crossesCount,
                    zeroesCount);

            do {
                crossZero = new CrossZero();
                checkCombination(crossesCombination, zeroesCombination);
            } while (getNextCombination(zeroesCombination));
        } while (getNextCombination(crossesCombination));
    }

    private void checkCombination (final boolean[] crossesCombination, final boolean[] zeroesCombination) {
        int j = 0;
        int crossPosition = -1;

        for (int i = 0; i < crossesCombination.length; ++i) {
            if (crossesCombination[i])
                field[i / 3][i % 3] = 1;
            else if (j < zeroesCombination.length && zeroesCombination[j++]) {
                crossPosition = makeMovementNextCross(crossPosition, crossesCombination);
                makeMovement(i, false);
                field[i / 3][i % 3] = -1;
            }
            else
                field[i / 3][i % 3] = 0;
        }
        makeMovementNextCross(crossPosition, crossesCombination);
        checkGameStatus();
        resetField();
    }

    private int makeMovementNextCross(final int currentCrossPosition, boolean[] crossesCombination) {
        int nextCrossPosition = getNextCross(currentCrossPosition, crossesCombination);

        if (nextCrossPosition < crossesCombination.length)
            makeMovement(nextCrossPosition, true);

        return nextCrossPosition;
    }

    private void makeMovement(final int position, boolean isCross) {
        if (crossZero.makeMovement(position / 3, position % 3) != isCross)
            fail();
    }

    private int getNextCross(final int currentCross, final boolean[] crossesCombination) {
        int nextCrosses = currentCross + 1;

        while (nextCrosses < crossesCombination.length && !crossesCombination[nextCrosses])
            ++nextCrosses;

        return nextCrosses;
    }

    private boolean[] generateFirstCombination(final int length, final int oneCount) {
        boolean[] combination = new boolean[length];

        for (int i = length - 1; i > length - oneCount - 1; --i)
            combination[i] = true;

        return combination;
    }

    private boolean getNextCombination(boolean[] combination) {
        int oneCount = 0;
        while (oneCount < combination.length && combination[oneCount]) {
            combination[oneCount] = false;
            ++oneCount;
        }

        int movableOne = oneCount + 1;
        while (movableOne < combination.length && !combination[movableOne])
            ++movableOne;

        if (movableOne >= combination.length)
            return false;

        combination[movableOne] = false;
        for (int i = movableOne - oneCount - 1; i < movableOne; ++i)
            combination[i] = true;
        return true;
    }

    private void resetField() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j)
                field[i][j] = 0;
        }
    }

    private void checkGameStatus() {
        switch (getGameStatus()) {
            case 0:
                if (crossZero.getGameStatus() != GameIsNotOver && crossZero.getGameStatus() != NobodyWon)
                    fail();
                break;
            case 1:
                if (crossZero.getGameStatus() != WonCrosses)
                    fail();
                break;
            case 2:
                if (crossZero.getGameStatus() != WonZeros)
                    fail();
                break;
        }
    }

    private int getGameStatus() {
        for (int i = 0; i < 3; ++i) {
            if (field[0][i] == 1 && field [1][i] == 1 && field[2][i] == 1 ||
                    field[i][0] == 1 && field[i][1] == 1 && field[i][2] == 1)
                return 1;
            if (field[0][i] == -1 && field [1][i] == -1 && field[2][i] == -1 ||
                    field[i][0] == -1 && field[i][1] == -1 && field[i][2] == -1)
                return 2;
        }

        if (field[0][0] == 1 && field[1][1] == 1 && field[2][2] == 1 ||
                field[0][2] == 1 && field[1][1] == 1 && field[2][0] == 1)
            return 1;
        if (field[0][0] == -1 && field[1][1] == -1 && field[2][2] == -1 ||
                field[0][2] == -1 && field[1][1] == -1 && field[2][0] == -1)
            return 2;

        return 0;
    }
}

package c1_s2.konovalov.task7_2.sample;

import static java.lang.StrictMath.abs;

/** игра крестики нолики */
class CrossZero {
    private int[][] field = new int[3][3];
    private boolean isCross = true;

    /**
     * сделать ход
     * @param coordinateX координата X
     * @param coordinateY координата Y
     * @return возвращает true если ходил крест
     */
    boolean makeMovement(int coordinateX, int coordinateY) {
        if (coordinateX < 0 || coordinateX > 2 || coordinateY < 0 || coordinateY > 2)
            throw new ImpossibleMakeMovement("incorrect coordinate");

        if (field[coordinateX][coordinateY] == 0) {
            field[coordinateX][coordinateY] = isCross ? 1 : -1;
            isCross = !isCross;
            return !isCross;
        }
        else
            throw new ImpossibleMakeMovement("cell is filled");
    }

    /**
     * возвращает статус игры
     * @return GameIsNotOver - игра не закончена, WonCrosses - победили кресты,
     * WosZeros - победили нолики, NobodyWon - ничья
     */
    GameStatus getGameStatus() {
        int[] result = new int[8];
        int filledCellCount = 0;

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                filledCellCount += abs(field[i][j]);
                result[j] += field[j][i];
                result[j + 3] += field[i][j];
            }
            result[6] += field[i][i];
            result[7] += field[i][2 - i];
        }

        for (int i = 0; i < 8; ++i)
            if (abs(result[i]) == 3)
                return result[i] > 0 ? GameStatus.WonCrosses : GameStatus.WonZeros;

        return filledCellCount < 9 ? GameStatus.GameIsNotOver : GameStatus.NobodyWon;
    }

    enum GameStatus {
        GameIsNotOver, WonCrosses, WonZeros, NobodyWon
    }

    class ImpossibleMakeMovement extends RuntimeException {
        ImpossibleMakeMovement(String message) {
            super(message);
        }
    }
}

package Lesson_8;

import java.util.Arrays;
import java.util.Random;

public class Controller {

    private final int SIZE;
    private final int CHECK_SIZE;

    private final Parameters.GameMode gameMode;
    private CellState lastPlayer = CellState.Empty;

    enum CellState {
        Player,
        SecondPlayer,
        Empty,
        Error
    }

    enum GameState {
        PlayerWin,
        SecondPlayerWin,
        Draw,
        Playing
    }

    private final CellState[][] field;

    private final Random random = new Random();

    Controller(Parameters p) {
        CHECK_SIZE = p.getWinLen();
        SIZE = p.getFieldSize();
        field = new CellState[SIZE][SIZE];
        gameMode = p.getGameMode();
        initField();
    }

    private void initField() {
        for (CellState[] arr : field) {
            Arrays.fill(arr, CellState.Empty);
        }
    }

    public CellState getCellState(int y, int x) {
        if (!isCellValid(y,x)) {
            return CellState.Error;
        }
        return field[y][x];
    }

    public int getSize() {
        return SIZE;
    }

    public GameState makeMove(int x, int y) {
        if (!isCellValid(y, x) || !isCellEmpty(y, x)) {
            return GameState.Playing;
        }

        if (gameMode == Parameters.GameMode.humanVSAi) {
            setState(y, x, CellState.Player);
            GameState state = checkGameState();
            if (state == GameState.Playing) {
                makeAIStep();
            }
        } else {
            lastPlayer = (lastPlayer == CellState.Player) ? CellState.SecondPlayer : CellState.Player;
            setState(y, x, lastPlayer);
        }

        return checkGameState();
    }

    private GameState checkGameState() {
        if (checkWin(CellState.Player)) {
            return GameState.PlayerWin;
        }

        if (checkWin(CellState.SecondPlayer)) {
            return GameState.SecondPlayerWin;
        }

        if (isFieldFull()) {
            return GameState.Draw;
        }

        return GameState.Playing;
    }

    private boolean isCellValid(int y, int x) {
        return !(x < 0 || y < 0 || x > SIZE - 1 || y > SIZE - 1);
    }

    private boolean isCellEmpty(int y, int x) {
        return (field[y][x] == CellState.Empty) ;
    }

    private boolean checkAiStep(int y, int x) {
        return isCellValid(y, x) && isCellEmpty(y, x);
    }

    private boolean isFieldFull() {
        for (CellState[] arr : field) {
            for (CellState s : arr) {
                if (s == CellState.Empty) {
                    return false;
                }
            }
        }
        return true;
    }

    private void setState(int y, int x, CellState s) {
        field[y][x] = s;
    }

    private void makeAIStep() {
        if (!setAiDotToWin() && !setAiDotIfDanger()) {
            int x,y;
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!checkAiStep(y, x));
            setState(y, x, CellState.SecondPlayer);
        }
    }

    private boolean setAiDotToWin() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellEmpty(i, j)) {
                    setState(i, j, CellState.SecondPlayer);
                    if (checkWin(CellState.SecondPlayer)) {
                        setState(i, j, CellState.SecondPlayer);
                        return true;
                    } else {
                        setState(i, j, CellState.Empty);
                    }
                }
            }
        }
        return false;
    }

    private boolean setAiDotIfDanger() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellEmpty(i, j)) {
                    setState(i, j, CellState.Player);
                    if (checkWin(CellState.Player)) {
                        setState(i, j, CellState.SecondPlayer);
                        return true;
                    } else {
                        setState(i, j, CellState.Empty);
                    }
                }
            }
        }
        return false;
    }

    private boolean checkWin(CellState s) {
        // Проверяем вертикальные и горизонтальные строки
        int sumH;
        int[] sumV = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            sumH = 0;
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == s) {
                    sumH++;
                    sumV[j]++;
                    if (sumH == CHECK_SIZE || sumV[j] == CHECK_SIZE) {
                        return true;
                    }
                } else {
                    sumH = 0; sumV[j] = 0;
                }
            }
        }

        // Проверяем диагональные линии
        int delta = SIZE - CHECK_SIZE;
        if (delta < 0) return false;
        int diagonalRL, diagonalLR, x;
        for (int i = -delta; i <= delta; i++) {
            diagonalRL = 0;
            diagonalLR = 0;
            for (int j = 0; j < SIZE; j++) {
                x = i+j;
                if (x >= 0 && x < SIZE) {
                    if (field[j][x] == s) {
                        diagonalRL++;
                        if (diagonalRL == CHECK_SIZE) return true;
                    } else {
                        diagonalRL = 0;
                    }
                }

                x = i+SIZE-j-1;
                if (x < SIZE && x >= 0) {
                    if (field[j][x] == s) {
                        diagonalLR++;
                        if (diagonalLR == CHECK_SIZE) return true;
                    } else {
                        diagonalLR = 0;
                    }
                }

            }
        }

        return false;
    }
}
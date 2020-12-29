package Lesson_8;

public class Parameters {
    enum GameMode {
        humanVSAi,
        humanVSHuman
    }

    private int fieldSize;
    private int winLen;
    private GameMode gameMode;
    private final boolean initialized;

    public Parameters(int fieldSize, int winLen, GameMode gameMode) {
        this.fieldSize = fieldSize;
        this.winLen = winLen;
        this.gameMode = gameMode;
        initialized = true;
    }

    public Parameters() {
        initialized = false;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public int getWinLen() {
        return winLen;
    }

    public boolean isInitialized() {
        return initialized;
    }
}

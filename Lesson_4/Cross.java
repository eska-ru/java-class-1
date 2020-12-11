package Lesson_4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Cross {

    static class Coordinate {
        private final int x, y;
        Coordinate(int y, int x) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private final int SIZE;
    private final int CHECK_SIZE;
    private final static char PLAYER_DOT = 'X';
    private final static char AI_DOT = 'O';
    private final static char EMPTY_DOT = '.';

    private final char[][] field;

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    Cross(int size, int checkSize) {
        SIZE = size;
        field = new char[SIZE][SIZE];
        CHECK_SIZE = checkSize;
        initField();
    }

    private void initField() {
        for (char[] arr : field) {
            Arrays.fill(arr, EMPTY_DOT);
        }
    }

    public void play() {
        printField();

        while (true) {
            makePlayerStep();
            printField();

            if (checkWin(PLAYER_DOT)) {
                System.out.println("Player WIN!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("DRAW");
                break;
            }

            makeAIStep();
            printField();

            if (checkWin(AI_DOT)) {
                System.out.println("AI WIN");
                break;
            }
            if (isFieldFull()) {
                System.out.println("DRAW");
                break;
            }
        }
    }

    public void printField() {
        System.out.println("-------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("|");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("-------");
    }

    private boolean isCellValid(int y, int x) {
        return !(x < 0 || y < 0 || x > SIZE - 1 || y > SIZE - 1);
    }

    private boolean isCellEmpty(int y, int x) {
        return (field[y][x] == EMPTY_DOT) ;
    }

    private boolean checkPlayerStep(int y, int x) {
        if (!isCellValid(y, x)) {
            System.out.println("Координаты не верные, повторите ввод:");
            return false;
        }
        if (!isCellEmpty(y, x)) {
            System.out.println("Ячейка занята, повторите ввод: ");
            return false;
        }

        return true;
    }

    private boolean checkAiStep(int y, int x) {
        return isCellValid(y, x) && isCellEmpty(y, x);
    }

    public boolean isFieldFull() {
        for (char[] arr : field) {
            for (char c : arr) {
                if (c == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    private void setSym(int y, int x, char sym) {
        field[y][x] = sym;
    }

    private void makePlayerStep() {
        System.out.println("Введите координаты х и у:");
        int x,y;
        do {
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!checkPlayerStep(y, x));
        setSym(y, x, PLAYER_DOT);
    }

    private void makeAIStep() {
        System.out.println("Ходит AI.");

        Coordinate danger = getPossibleWarnings();
        if (isCellValid(danger.getY(), danger.getX())) {
            setSym(danger.getY(), danger.getX(), AI_DOT);
        } else {
            int x,y;
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!checkAiStep(y, x));
            setSym(y, x, AI_DOT);
        }
    }

    private Coordinate getPossibleWarnings() {
        // Проверяем вертикальные и горизонтальные
        int sumH;
        int[] sumV = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            sumH = 0;
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == PLAYER_DOT) {
                    sumH++;
                    if (sumH == CHECK_SIZE-1) {
                        if (checkAiStep(i, j+1)) {
                            return new Coordinate(i, j+1);
                        }
                        if (checkAiStep(i, j-CHECK_SIZE+1)) {
                            return new Coordinate(i, j-CHECK_SIZE+1);
                        }
                        sumH = 0;
                    }
                    sumV[j]++;
                    if (sumV[j] == CHECK_SIZE-1) {
                        if (checkAiStep(i+1, j)) {
                            return new Coordinate(i+1, j);
                        }
                        if (checkAiStep(i-CHECK_SIZE+1, j)) {
                            return new Coordinate(i-CHECK_SIZE+1, j);
                        }
                        sumV[j] = 0;
                    }
                } else {
                    sumH = 0; sumV[j] = 0;
                }
            }
        }

        // Проверяем диагональные
        int delta = SIZE - CHECK_SIZE;
        if (delta < 0) return new Coordinate(-1, -1);
        int diagonalRL, diagonalLR, x;
        for (int i = -delta; i <= delta; i++) {
            diagonalRL = 0;
            diagonalLR = 0;
            for (int j = 0; j < SIZE; j++) {
                x = i + j;
                if (x >= 0 && x < SIZE) {
                    if (field[j][x] == PLAYER_DOT) {
                        diagonalRL++;
                        if (diagonalRL == CHECK_SIZE - 1) {
                            if (checkAiStep(j + 1, x + 1)) {
                                return new Coordinate(j + 1, x + 1);
                            }
                            if (checkAiStep(j - CHECK_SIZE+1, x - CHECK_SIZE+1)) {
                                return new Coordinate(j - CHECK_SIZE+1, x - CHECK_SIZE+1);
                            }
                            diagonalRL = 0;
                        }
                    } else {
                        diagonalRL = 0;
                    }
                }

                x = i + SIZE - j - 1;
                if (x < SIZE && x >= 0) {
                    if (field[j][x] == PLAYER_DOT) {
                        diagonalLR++;
                        if (diagonalLR == CHECK_SIZE - 1) {
                            if (checkAiStep(j + 1, x - 1)) {
                                return new Coordinate(j + 1, x - 1);
                            }
                            if (checkAiStep(j - CHECK_SIZE + 1, x + CHECK_SIZE - 1)) {
                                return new Coordinate(j - CHECK_SIZE + 1, x + CHECK_SIZE - 1);
                            }
                            diagonalLR = 0;
                        }
                    } else {
                        diagonalLR = 0;
                    }
                }

            }
        }

            return new Coordinate(-1, -1);
    }

    private boolean checkWin(char sym) {
        // Проверяем вертикальные и горизонтальные строки
        int sumH;
        int[] sumV = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            sumH = 0;
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == sym) {
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
                    if (field[j][x] == sym) {
                        diagonalRL++;
                        if (diagonalRL == CHECK_SIZE) return true;
                    } else {
                        diagonalRL = 0;
                    }
                }

                x = i+SIZE-j-1;
                if (x < SIZE && x >= 0) {
                    if (field[j][x] == sym) {
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

class Main {
    public static void main(String[] args) {
        Cross game = new Cross(3, 3);
        game.play();
    }
}

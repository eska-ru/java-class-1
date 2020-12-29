package Lesson_8;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    private static final int WIN_HEIGHT = 555;
    private static final int WIN_WIDTH = 508;
    private static final int WIN_POS_X = 800;
    private static final int WIN_POS_Y = 300;

    private static View field;

    public Game() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(WIN_POS_X, WIN_POS_Y, WIN_WIDTH, WIN_HEIGHT);
        setTitle("Tic Tac Toe");

        setResizable(false);

        JPanel bottomPanel = new JPanel(new GridLayout(1,2));

        JButton btnNewGame = new JButton("Start new game");
        JButton btnExit = new JButton("Exit");

        bottomPanel.add(btnNewGame);
        bottomPanel.add(btnExit);

        add(bottomPanel, BorderLayout.SOUTH);

        field = new View();
        add(field, BorderLayout.CENTER);

        btnNewGame.addActionListener(e -> startNewGame());

        btnExit.addActionListener(e -> System.exit(0));
    }

    void startNewGame() {
        Parameters params = NewGameDialog.showDialog(this);
        if (params.isInitialized()) {
            field.startNewGame(new Controller(params));
        }
    }
}

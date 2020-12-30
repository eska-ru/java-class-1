package Lesson_8;

import javax.swing.*;
import java.awt.*;

public class NewGameDialog extends JDialog{

    private static final int WIN_HEIGHT = 230;
    private static final int WIN_WIDTH = 350;
    private static final int MIN_WIN_LEN = 3;
    private static final int MAX_WIN_LEN = 10;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final String STR_WIN_LEN = "Winning Length ";
    private static final String STR_FIELD_SIZE = "Field Size ";

    private final JRadioButton jrbHumVsAi = new JRadioButton("Human vs Ai", true);
    private final JRadioButton jrbHumVsHum = new JRadioButton("Human vs Human");
    private final ButtonGroup btnGroupGameMode = new ButtonGroup();

    private boolean btnClicked = false;

    private JSlider slFieldSize;
    private JSlider slWinLength;

    static Parameters showDialog(Game g) {
        NewGameDialog dialog = new NewGameDialog(g);
        dialog.setVisible(true);

        if (!dialog.btnClicked) {
            return new Parameters();
        }

        Parameters.GameMode gameMode;
        if (dialog.jrbHumVsAi.isSelected()) {
            gameMode = Parameters.GameMode.humanVSAi;
        } else {
            gameMode = Parameters.GameMode.humanVSHuman;
        }
        int fieldSize = dialog.slFieldSize.getValue();
        int winLen = dialog.slWinLength.getValue();

        return new Parameters(fieldSize, winLen, gameMode);
    }

    private NewGameDialog(Game g)  {
        super(g);
        setTitle("New game parameters");
        setLayout(new GridLayout(10,1));
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setLocationRelativeTo(g);
        setModal(true);

        addGameControlsMode();
        addGameControlsFieldWinLength();

        JButton btnStartGame = new JButton("Start a game!");
        add(btnStartGame);
        btnStartGame.addActionListener(e -> {
            btnClicked = true;
            dispose();
        });
    }

    private void addGameControlsMode() {
        add(new JLabel("Choose gaming mode:"));
        btnGroupGameMode.add(jrbHumVsAi);
        btnGroupGameMode.add(jrbHumVsHum);
        add(jrbHumVsAi);
        add(jrbHumVsHum);
    }

    private void addGameControlsFieldWinLength() {
        add(new JLabel("Choose field size:"));
        final JLabel lblFieldSize = new JLabel(STR_FIELD_SIZE + MIN_FIELD_SIZE);
        add(lblFieldSize);

        slFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slFieldSize.addChangeListener(e -> {
            int currentFieldSize = slFieldSize.getValue();
            lblFieldSize.setText(STR_FIELD_SIZE + currentFieldSize);
            slWinLength.setMaximum(currentFieldSize);
        });
        add(slFieldSize);

        add(new JLabel("Choose winning length size:"));
        final JLabel lblWinLen = new JLabel(STR_WIN_LEN + MIN_WIN_LEN);
        add(lblWinLen);

        slWinLength = new JSlider(MIN_WIN_LEN, MAX_WIN_LEN, MIN_WIN_LEN);
        slWinLength.addChangeListener(e -> lblWinLen.setText(STR_WIN_LEN + slWinLength.getValue()));

        add(slWinLength);
    }
}

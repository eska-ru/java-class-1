package Lesson_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View extends JPanel {
    private int fieldSize;
    private int cellHeight;
    private int cellWidth;

    private Controller controller;

    View() {
        setBackground(Color.ORANGE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                mouseClickEvent(e);
            }
        });
    }

    private void mouseClickEvent(MouseEvent e) {
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        Controller.GameState state = controller.makeMove(cellX, cellY);

        repaint();

        switch (state) {
            case Draw:
                JOptionPane.showMessageDialog(this,"Draw!");
                break;
            case PlayerWin:
                JOptionPane.showMessageDialog(this,"Х wins!");
                break;
            case SecondPlayerWin:
                JOptionPane.showMessageDialog(this,"O wins!");
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    void startNewGame(Controller c) {
        controller = c;
        this.fieldSize = c.getSize();

        repaint();
    }

    private void render(Graphics g) {
        if (controller == null) {
            return;
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        cellWidth = panelWidth / fieldSize;
        cellHeight = panelHeight / fieldSize;

        // рисуем полоски
        for (int i = 0; i < fieldSize; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for (int i = 0; i < controller.getSize(); i++) {
            for (int j = 0; j < controller.getSize(); j++) {
                Controller.CellState s = controller.getCellState(i, j);
                if (s == Controller.CellState.Player) {
                    drawCell(g, Controller.CellState.Player, cellWidth, cellHeight, j*cellWidth, i*cellHeight);
                } else if (s == Controller.CellState.SecondPlayer) {
                    drawCell(g, Controller.CellState.SecondPlayer, cellWidth, cellHeight, j*cellWidth, i*cellHeight);
                }
            }
        }
    }

    private void drawCell(Graphics g, Controller.CellState s, int width, int height, int x, int y) {
        int rX = (int)(x + width*0.2);
        int rY = (int)(y + height*0.2);
        if (s == Controller.CellState.Player) {
            int rW = (int)(x + width*0.8);
            int rH = (int)(y + height*0.8);
            g.drawLine(rX, rY, rW, rH);
            g.drawLine(rW, rY, rX, rH);
        } else if (s == Controller.CellState.SecondPlayer) {
            int rW = (int)(width*0.6);
            int rH = (int)(height*0.6);
            g.drawOval(rX, rY, rW, rH);
        }
    }
}

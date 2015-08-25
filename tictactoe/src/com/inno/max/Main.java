package com.inno.max;

import com.inno.max.game.Field;
import com.inno.max.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import static javax.swing.JOptionPane.showMessageDialog;

public class Main {
    public static Queue<Point> points = new LinkedBlockingDeque<Point>();
    private static JButton buttons[][] = new JButton[3][3];
    private static JFrame jFrame;

    public static void main(String[] args) {
        jFrame = new JFrame("Tic-tac-toe");
        JPanel panel;
        GridLayout gridLayout = new GridLayout(3, 3, 5, 5);


        jFrame.setSize(new Dimension(300, 300));
        jFrame.add(panel = new JPanel());

        panel.setLayout(gridLayout);

        for (int i = 0; i < Field.HEIGHT; i++) {
            for (int j = 0; j < Field.WIDTH; j++) {
                JButton button;
                buttons[j][i] = button = new JButton("");

                final int x = i, y = j;

                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);

                        points.clear();
                        points.add(new Point(x, y));
                        jFrame.repaint();
                    }
                });

                panel.add(button);
            }
        }

        new Thread(new Game()).start();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static synchronized void repaintCell(final int x, final int y, final Field.Cell.OccupationType occupationType) {
        switch (occupationType) {
            case X:
                buttons[y][x].setText("X");
                break;
            case O:
                buttons[y][x].setText("O");
                break;
            case Empty:
                buttons[y][x].setText("");
                break;
        }
    }

    public static void gracWinner(final Field.Cell.OccupationType winner) {
        showMessageDialog(jFrame, "Congrats! Player " + winner + " has won the match!");
    }

    public static void gracBoth() {
        showMessageDialog(jFrame, "Congrats to both players! Nobody has won the match!");
    }
}

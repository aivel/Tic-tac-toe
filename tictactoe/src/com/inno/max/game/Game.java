package com.inno.max.game;

import com.inno.max.Main;
import com.inno.max.player.AIPlayer;
import com.inno.max.player.HumanPlayer;
import com.inno.max.player.Player;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Queue;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by max on 25.08.15.
 */
public class Game implements Runnable {
    private enum GameState {
        PLAYING, GAME_OVER
    }

    private Field field = new Field();
    private Queue<Player> players = new ArrayDeque<Player>(2);
    private GameState gameState = GameState.PLAYING;
    private int step = 0;

    public Game(final int whoStartsTheGame) {
        if (whoStartsTheGame == 1) {
            players.add(new HumanPlayer(Field.Cell.OccupationType.X));
            players.add(new AIPlayer(Field.Cell.OccupationType.O));
        } else {
            players.add(new AIPlayer(Field.Cell.OccupationType.X));
            players.add(new HumanPlayer(Field.Cell.OccupationType.O));
        }
    }

    private void updateGameState() {
        GameState newGameState = gameState;

        switch (gameState) {
            case PLAYING:
                if (field.isFilled() || field.hasWinner()) {
                    newGameState = GameState.GAME_OVER;
                }
                break;
            case GAME_OVER:
                break;
        }

        gameState = newGameState;
    }

    @Override
    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            updateGameState();

            switch (gameState) {
                case PLAYING:
                    play();
                    break;
                case GAME_OVER:
                    isRunning = false;
                    onGameOver();
                    break;
            }
        }
    }

    private void play() {
        final Player currentPlayer = players.poll();
        final Field.Cell.OccupationType newFieldOccupationType = step % 2 == 0 ? Field.Cell.OccupationType.X : Field.Cell.OccupationType.O;
        Point shootPoint;

        do {
            shootPoint = currentPlayer.move();
        } while (!field.setCellStatus(shootPoint, newFieldOccupationType));

        Main.repaintCell(shootPoint.x, shootPoint.y, newFieldOccupationType);

        players.add(currentPlayer);

        for (final Player player: players) {
            player.onShoot(shootPoint, newFieldOccupationType);
        }

        step++;
    }

    private void onGameOver() {
        if (field.hasWinner()) {
            Main.gracWinner(field.getWinner());
        } else {
            Main.gracBoth();
        }
    }
}

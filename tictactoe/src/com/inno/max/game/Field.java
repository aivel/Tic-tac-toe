package com.inno.max.game;

import java.awt.*;

/**
 * Created by max on 25.08.15.
 */
public class Field {
    public static final class Cell {

        public enum OccupationType {
            X, O, Empty;


        }
        private OccupationType occupationType;

        public Cell() {
            occupationType = OccupationType.Empty;
        }

        public void setOccupationType(OccupationType occupationType) {
            this.occupationType = occupationType;
        }

        public OccupationType getOccupationType() {
            return occupationType;
        }

    }

    public static int HEIGHT = 3;
    public static int WIDTH = 3;
    private Cell[][] cells = new Cell[HEIGHT][WIDTH];

    public Field() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                cells[j][i] = new Cell();
            }
        }
    }

    public Cell.OccupationType getCellStatus(final int x, final int y) {
        return cells[y][x].getOccupationType();
    }

    public boolean setCellStatus(final int x, final int y,
                                 final Cell.OccupationType occupationType) {
        final Cell cell = cells[y][x];

        if (cell.getOccupationType() == Cell.OccupationType.Empty) {
            cell.setOccupationType(occupationType);
            return true;
        }

        return false;
    }

    public boolean setCellStatus(final Point shootPoint, final Cell.OccupationType newFieldOccupationType) {
        return setCellStatus(shootPoint.x, shootPoint.y, newFieldOccupationType);
    }

    public boolean isFilled() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (cells[j][i].getOccupationType() == Cell.OccupationType.Empty) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean hasWinner() {
        return  cells[0][0].getOccupationType() == cells[1][1].getOccupationType() && cells[1][1].getOccupationType() == cells[2][2].getOccupationType() &&  cells[2][2].getOccupationType() != Cell.OccupationType.Empty ||
                cells[0][2].getOccupationType() == cells[1][1].getOccupationType() && cells[1][1].getOccupationType() == cells[2][0].getOccupationType() &&  cells[2][0].getOccupationType() != Cell.OccupationType.Empty ||

                cells[0][0].getOccupationType() == cells[0][1].getOccupationType() && cells[0][1].getOccupationType() == cells[0][2].getOccupationType() &&  cells[0][2].getOccupationType() != Cell.OccupationType.Empty ||
                cells[1][0].getOccupationType() == cells[1][1].getOccupationType() && cells[1][1].getOccupationType() == cells[1][2].getOccupationType() &&  cells[1][2].getOccupationType() != Cell.OccupationType.Empty ||
                cells[2][0].getOccupationType() == cells[2][1].getOccupationType() && cells[2][1].getOccupationType() == cells[2][2].getOccupationType() &&  cells[2][2].getOccupationType() != Cell.OccupationType.Empty ||

                cells[0][0].getOccupationType() == cells[1][0].getOccupationType() && cells[1][0].getOccupationType() == cells[2][0].getOccupationType() &&  cells[2][0].getOccupationType() != Cell.OccupationType.Empty ||
                cells[0][1].getOccupationType() == cells[1][1].getOccupationType() && cells[1][1].getOccupationType() == cells[2][1].getOccupationType() &&  cells[2][1].getOccupationType() != Cell.OccupationType.Empty ||
                cells[0][2].getOccupationType() == cells[1][2].getOccupationType() && cells[1][2].getOccupationType() == cells[2][2].getOccupationType() &&  cells[2][2].getOccupationType() != Cell.OccupationType.Empty;
    }

    public Cell.OccupationType getWinner() {
        if (cells[0][0].getOccupationType() == cells[1][1].getOccupationType() && cells[1][1].getOccupationType() == cells[2][2].getOccupationType() &&  cells[2][2].getOccupationType() != Cell.OccupationType.Empty) {
            return cells[0][0].getOccupationType();
        }

        if (cells[0][2].getOccupationType() == cells[1][1].getOccupationType() && cells[1][1].getOccupationType() == cells[2][0].getOccupationType() &&  cells[2][0].getOccupationType() != Cell.OccupationType.Empty) {
            return cells[0][2].getOccupationType();
        }

        if (cells[0][0].getOccupationType() == cells[0][1].getOccupationType() && cells[0][1].getOccupationType() == cells[0][2].getOccupationType() &&  cells[0][2].getOccupationType() != Cell.OccupationType.Empty) {
            return cells[0][0].getOccupationType();
        }

        if (cells[1][0].getOccupationType() == cells[1][1].getOccupationType() && cells[1][1].getOccupationType() == cells[1][2].getOccupationType() &&  cells[1][2].getOccupationType() != Cell.OccupationType.Empty) {
            return cells[1][0].getOccupationType();
        }

        if (cells[2][0].getOccupationType() == cells[2][1].getOccupationType() && cells[2][1].getOccupationType() == cells[2][2].getOccupationType() &&  cells[2][2].getOccupationType() != Cell.OccupationType.Empty) {
            return cells[2][0].getOccupationType();
        }

        if (cells[0][0].getOccupationType() == cells[1][0].getOccupationType() && cells[1][0].getOccupationType() == cells[2][0].getOccupationType() &&  cells[2][0].getOccupationType() != Cell.OccupationType.Empty) {
            return cells[0][0].getOccupationType();
        }

        if (cells[0][1].getOccupationType() == cells[1][1].getOccupationType() && cells[1][1].getOccupationType() == cells[2][1].getOccupationType() &&  cells[2][1].getOccupationType() != Cell.OccupationType.Empty) {
            return cells[0][1].getOccupationType();
        }

        if (cells[0][2].getOccupationType() == cells[1][2].getOccupationType() && cells[1][2].getOccupationType() == cells[2][2].getOccupationType() &&  cells[2][2].getOccupationType() != Cell.OccupationType.Empty) {
            return cells[0][2].getOccupationType();
        }

        return null;
    }
}

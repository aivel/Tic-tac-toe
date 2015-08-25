package com.inno.max.player;

import com.inno.max.Main;
import com.inno.max.game.Field;
import javafx.util.Pair;

import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.util.*;

/**
 * Created by max on 25.08.15.
 */
public class AIPlayer extends Player {
    final Field.Cell[][] cells = new Field.Cell[Field.HEIGHT][Field.WIDTH];
    final Queue<Point> strategyPoints = new LinkedList<Point>();
    final Queue<Point> shittedPoints = new LinkedList<Point>();

    public AIPlayer(final Field.Cell.OccupationType occupationType) {
        super(occupationType);

        strategyPoints.add(new Point(1, 1));
        strategyPoints.add(new Point(0, 0));
        strategyPoints.add(new Point(0, 2));
        strategyPoints.add(new Point(2, 2));
        strategyPoints.add(new Point(2, 0));

        for (int i = 0; i < Field.HEIGHT; i++) {
            for (int j = 0; j < Field.WIDTH; j++) {
                cells[i][j] = new Field.Cell();
            }
        }
    }

    private boolean isOccupied(final Point point) {
        return cells[point.y][point.x].getOccupationType() == Field.Cell.OccupationType.Empty;
    }

    public Point getOpponentDesiredShootPoint() {
        if (cells[0][0].getOccupationType() == cells[1][1].getOccupationType() && cells[1][1].getOccupationType() != Field.Cell.OccupationType.Empty) {
            final Point point = new Point(2, 2);

            if (!shittedPoints.contains(point)) {
                shittedPoints.add(point);
                return point;
            }
        }

        if (cells[1][1].getOccupationType() == cells[2][2].getOccupationType() && cells[2][2].getOccupationType() != Field.Cell.OccupationType.Empty) {
            final Point point = new Point(0, 0);

            if (!shittedPoints.contains(point)) {
                shittedPoints.add(point);
                return point;
            }
        }

        if (cells[2][2].getOccupationType() == cells[0][0].getOccupationType() && cells[0][0].getOccupationType() != Field.Cell.OccupationType.Empty) {
            final Point point = new Point(1, 1);

            if (!shittedPoints.contains(point)) {
                shittedPoints.add(point);
                return point;
            }
        }


        if (cells[2][0].getOccupationType() == cells[1][1].getOccupationType() && cells[1][1].getOccupationType() != Field.Cell.OccupationType.Empty) {
            final Point point = new Point(0, 2);

            if (!shittedPoints.contains(point)) {
                shittedPoints.add(point);
                return point;
            }
        }

        if (cells[0][2].getOccupationType() == cells[1][1].getOccupationType() && cells[1][1].getOccupationType() != Field.Cell.OccupationType.Empty) {
            final Point point = new Point(2, 0);

            if (!shittedPoints.contains(point)) {
                shittedPoints.add(point);
                return point;
            }
        }

        if (cells[2][0].getOccupationType() == cells[0][2].getOccupationType() && cells[0][2].getOccupationType() != Field.Cell.OccupationType.Empty) {
            final Point point = new Point(1, 1);

            if (!shittedPoints.contains(point)) {
                shittedPoints.add(point);
                return point;
            }
        }

        return null;
    }

    @Override
    public Point move() {
        final Random random = new Random();

        Point nextShoot = getOpponentDesiredShootPoint();

        if (nextShoot == null) {
            nextShoot = strategyPoints.poll();
        }

        if (nextShoot == null) {
            nextShoot = new Point(Math.abs(random.nextInt() % Field.WIDTH), Math.abs(random.nextInt() % Field.WIDTH));
        }

        return nextShoot;
    }

    @Override
    public void onShoot(final Point point,
                        final Field.Cell.OccupationType occupationType) {
        cells[point.y][point.x].setOccupationType(occupationType);
    }
}

package com.inno.max.player;

import com.inno.max.Main;
import com.inno.max.game.Field;
import javafx.util.Pair;

import java.awt.*;
import java.util.*;

/**
 * Created by max on 25.08.15.
 */
public class HumanPlayer extends Player {
    final java.util.List<Pair<Point, Field.Cell.OccupationType>> shootPoints = new LinkedList<Pair<Point, Field.Cell.OccupationType>>();

    public HumanPlayer(final Field.Cell.OccupationType occupationType) {
        super(occupationType);
    }

    @Override
    public Point move() {
        while (Main.points.isEmpty()) {
        }

        final Point shootPoint = (Point) Main.points.poll().clone();

        Main.points.clear();

        return shootPoint;
    }

    @Override
    public void onShoot(final Point point,
                        final Field.Cell.OccupationType occupationType) {
        shootPoints.add(new Pair<Point, Field.Cell.OccupationType>(point, occupationType));
    }
}

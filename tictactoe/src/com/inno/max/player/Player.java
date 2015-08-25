package com.inno.max.player;

import com.inno.max.game.Field;

import java.awt.*;

public abstract class Player {
    protected final Field.Cell.OccupationType occupationType;

    protected Player(Field.Cell.OccupationType occupationType) {
        this.occupationType = occupationType;
    }

    public abstract Point move();
    public abstract void onShoot(final Point point, final Field.Cell.OccupationType occupationType);
}

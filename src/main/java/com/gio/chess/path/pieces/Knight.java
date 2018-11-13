package com.gio.chess.path.pieces;

import com.gio.chess.path.Position;

import java.util.HashSet;
import java.util.Set;

public class Knight {
    private Position position;

    public Knight(Position position) {
        this.position = position;
    }

    public Set<Position> getMovesAvailable() {
        Set<Position> expected = new HashSet<>();
        expected.add(new Position(2, 3));
        expected.add(new Position(3, 2));
        expected.add(new Position(5, 2));
        expected.add(new Position(2, 5));
        expected.add(new Position(6, 3));
        expected.add(new Position(3, 6));
        expected.add(new Position(5, 6));
        expected.add(new Position(6, 5));

        return expected;
    }
}

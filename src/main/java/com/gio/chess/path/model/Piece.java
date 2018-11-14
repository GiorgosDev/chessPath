package com.gio.chess.path.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Piece {

    private Position position;

    public Piece(Position position) {
        this.position = position;
    }

    public static void addPositionIfInRange(Position position, Set<Position> moves) {
        if (position.getX() >= 0
                && position.getX() <= 7
                && position.getY() >= 0
                && position.getY() <= 7)
            moves.add(position);
    }

    public Position getPosition() {
        return position;
    }

}

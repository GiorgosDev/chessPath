package com.gio.chess.path.model;

import com.gio.chess.path.exceptions.IncorrectPositionException;

import java.util.Objects;
import java.util.Set;

public class Position {

    public static final int BOARD_SIZE =8;

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(String chessPosition) throws IncorrectPositionException {

        if(chessPosition.length()>2
                || chessPosition.charAt(0) <'a'
                || chessPosition.charAt(0) >'h'
                || chessPosition.charAt(1) <'1'
                || chessPosition.charAt(1) >'8')
            throw new IncorrectPositionException();

        this.x = chessPosition.charAt(0) - 'a';
        this.y = chessPosition.charAt(1) - '1';
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + (char)('a' + x)  + (char)('1' + y) + ")";
    }

    public static void addPositionIfInRange(Position position, Set<Position> moves) {
        if (position.getX() >= 0
                && position.getX() <= BOARD_SIZE -1
                && position.getY() >= 0
                && position.getY() <= BOARD_SIZE -1)
            moves.add(position);
    }
}

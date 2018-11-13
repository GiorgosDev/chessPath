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
        Set<Position> moves = new HashSet<>();
        int leftSign = 1;
        int rightSign = 1;
        int leftShift = 1;
        int rightShift = 2;
        for(int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                addPositionIfInRange( new Position(position.getX() + leftShift*leftSign,
                        position.getY() + rightShift*rightSign), moves);
                addPositionIfInRange( new Position(position.getY() + rightShift*rightSign,
                        position.getX() + leftShift*leftSign), moves);
                leftSign *= -1;
            }
            rightSign *= -1;
        }
        return moves;
    }

    private void addPositionIfInRange(Position position, Set<Position> moves){
        if(position.getX() >= 0
                && position.getX() <= 7
                && position.getY() >=0
                && position.getY() <= 7)
            moves.add(position);
    }
}

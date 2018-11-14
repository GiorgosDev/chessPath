package com.gio.chess.path.moves;

import com.gio.chess.path.model.Piece;
import com.gio.chess.path.model.Position;

import java.util.HashSet;
import java.util.Set;

public class KnightMovesStrategy implements PieceMovesStrategy {

    public Set<Position> getMovesAvailable(Position position) {
        Set<Position> moves = new HashSet<>();
        int leftSign = 1;
        int rightSign = 1;
        int leftShift = 1;
        int rightShift = 2;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Piece.addPositionIfInRange(new Position(position.getX() + leftShift * leftSign,
                        position.getY() + rightShift * rightSign), moves);
                Piece.addPositionIfInRange(new Position(position.getX() + rightShift * rightSign,
                        position.getY() + leftShift * leftSign), moves);
                leftSign *= -1;
            }
            rightSign *= -1;
        }
        return moves;
    }

}

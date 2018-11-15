package com.gio.chess.path.moves;

import com.gio.chess.path.model.Position;

import java.util.HashSet;
import java.util.Set;

public class KingMovesStrategy implements PieceMovesStrategy {

    public Set<Position> getMovesAvailable(Position position) {
        Set<Position> moves = new HashSet<>();
        Position.addPositionIfInRange(new Position(position.getX() -1, position.getY() + 1), moves);
        Position.addPositionIfInRange(new Position(position.getX() , position.getY() + 1), moves);
        Position.addPositionIfInRange(new Position(position.getX() +1 , position.getY() + 1), moves);
        Position.addPositionIfInRange(new Position(position.getX() -1, position.getY() ), moves);
        Position.addPositionIfInRange(new Position(position.getX() +1 , position.getY() ), moves);
        Position.addPositionIfInRange(new Position(position.getX() -1, position.getY() - 1), moves);
        Position.addPositionIfInRange(new Position(position.getX() , position.getY() - 1), moves);
        Position.addPositionIfInRange(new Position(position.getX() +1 , position.getY() - 1), moves);


        return moves;
    }

}

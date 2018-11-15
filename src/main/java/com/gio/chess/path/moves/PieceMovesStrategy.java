package com.gio.chess.path.moves;

import com.gio.chess.path.model.Position;

import java.util.Set;

public interface PieceMovesStrategy {

    Set<Position> getMovesAvailable(Position position);

}

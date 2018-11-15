package com.gio.chess.path.moves;

import com.gio.chess.path.exceptions.IncorrectPositionException;
import com.gio.chess.path.model.Position;
import com.gio.chess.path.moves.KingMovesStrategy;
import com.gio.chess.path.moves.KnightMovesStrategy;
import com.gio.chess.path.moves.PieceMovesStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class KingMovesCalculationTest {

    @Test
    public void testMovePositionsCase() throws IncorrectPositionException {

        Set<Position> expected = new HashSet<>();
        expected.add(new Position("a5"));
        expected.add(new Position("b5"));
        expected.add(new Position("c5"));
        expected.add(new Position("a6"));
        expected.add(new Position("c6"));
        expected.add(new Position("a7"));
        expected.add(new Position("b7"));
        expected.add(new Position("c7"));

        PieceMovesStrategy pieceMovesStrategy = new KingMovesStrategy();
        Set<Position> calculated = pieceMovesStrategy.getMovesAvailable(new Position("b6"));

        Assert.assertEquals(expected.size(), calculated.size());
        for(Position calculatedPosition : calculated)
            Assert.assertTrue(expected.contains(calculatedPosition));
    }

    @Test
    public void testMovePositionsCornerCase() throws IncorrectPositionException {

        Set<Position> expected = new HashSet<>();
        expected.add(new Position("a7"));
        expected.add(new Position("b7"));
        expected.add(new Position("b8"));


        PieceMovesStrategy pieceMovesStrategy = new KingMovesStrategy();
        Set<Position> calculated = pieceMovesStrategy.getMovesAvailable(new Position("a8"));

        Assert.assertEquals(expected.size(), calculated.size());
        for(Position calculatedPosition : calculated)
            Assert.assertTrue(expected.contains(calculatedPosition));
    }
}

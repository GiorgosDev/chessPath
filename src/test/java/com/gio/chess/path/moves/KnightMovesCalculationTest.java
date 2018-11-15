package com.gio.chess.path.moves;

import com.gio.chess.path.exceptions.IncorrectPositionException;
import com.gio.chess.path.model.Position;
import com.gio.chess.path.moves.KnightMovesStrategy;
import com.gio.chess.path.moves.PieceMovesStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class KnightMovesCalculationTest {

    @Test
    public void testKnightMovePositions(){

        Set<Position> expected = new HashSet<>();
        expected.add(new Position(2, 3));
        expected.add(new Position(3, 2));
        expected.add(new Position(5, 2));
        expected.add(new Position(2, 5));
        expected.add(new Position(6, 3));
        expected.add(new Position(3, 6));
        expected.add(new Position(5, 6));
        expected.add(new Position(6, 5));

        PieceMovesStrategy pieceMovesStrategy = new KnightMovesStrategy();
        Set<Position> calculated = pieceMovesStrategy.getMovesAvailable(new Position(4,4));

        Assert.assertEquals(expected.size(), calculated.size());
        for(Position calculatedPosition : calculated)
            Assert.assertTrue(expected.contains(calculatedPosition));
    }

    @Test
    public void testKnightMovePositionsCase() throws IncorrectPositionException {

        Set<Position> expected = new HashSet<>();
        expected.add(new Position("c5"));
        expected.add(new Position("d4"));
        expected.add(new Position("f4"));
        expected.add(new Position("g5"));
        expected.add(new Position("c7"));
        expected.add(new Position("d8"));
        expected.add(new Position("f8"));
        expected.add(new Position("g7"));

        PieceMovesStrategy pieceMovesStrategy = new KnightMovesStrategy();
        Set<Position> calculated = pieceMovesStrategy.getMovesAvailable(new Position("e6"));

        Assert.assertEquals(expected.size(), calculated.size());
        for(Position calculatedPosition : calculated)
            Assert.assertTrue(expected.contains(calculatedPosition));
    }

    @Test
    public void testKnightAngleMovePositions(){

        Set<Position> expected = new HashSet<>();
        expected.add(new Position(1, 2));
        expected.add(new Position(2, 1));

        PieceMovesStrategy pieceMovesStrategy = new KnightMovesStrategy();
        Set<Position> calculated = pieceMovesStrategy.getMovesAvailable(new Position(0,0));


        Assert.assertEquals(expected.size(), calculated.size());
        for(Position calculatedPosition : calculated)
            Assert.assertTrue(expected.contains(calculatedPosition));
    }
}

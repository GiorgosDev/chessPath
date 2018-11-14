package com.gio.chess.path;

import com.gio.chess.path.model.Position;
import com.gio.chess.path.model.Knight;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class MovesCalculationTest {

    @Test
    public void testKnightMovePositions(){
        Knight knight = new Knight(new Position(4,4));
        Set<Position> expected = new HashSet<>();
        expected.add(new Position(2, 3));
        expected.add(new Position(3, 2));
        expected.add(new Position(5, 2));
        expected.add(new Position(2, 5));
        expected.add(new Position(6, 3));
        expected.add(new Position(3, 6));
        expected.add(new Position(5, 6));
        expected.add(new Position(6, 5));

        Set<Position> calculated = knight.getMovesAvailable();

        Assert.assertEquals(expected.size(), calculated.size());
        for(Position calculatedPosition : calculated)
            Assert.assertTrue(expected.contains(calculatedPosition));
    }

    @Test
    public void testKnightAngleMovePositions(){
        Knight knight = new Knight(new Position(0,0));
        Set<Position> expected = new HashSet<>();
        expected.add(new Position(1, 2));
        expected.add(new Position(2, 1));

        Set<Position> calculated = knight.getMovesAvailable();

        Assert.assertEquals(expected.size(), calculated.size());
        for(Position calculatedPosition : calculated)
            Assert.assertTrue(expected.contains(calculatedPosition));
    }
}

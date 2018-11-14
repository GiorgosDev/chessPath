package com.gio.chess.path.model;

import com.gio.chess.path.calculation.PathCalculationTemplate;
import com.gio.chess.path.calculation.SimplePathCalculationTemplate;
import com.gio.chess.path.exceptions.IncorrectPositionException;
import com.gio.chess.path.moves.KnightMovesStrategy;
import com.gio.chess.path.moves.PieceMovesStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class PiecePathCalculatorTest {

    @Test
    public void pathCalculationTest() throws IncorrectPositionException {
        PieceMovesStrategy pieceMovesStrategy = new KnightMovesStrategy();
        PathCalculationTemplate pathCalculationTemplate = new SimplePathCalculationTemplate(pieceMovesStrategy);
        Set<Path> paths = pathCalculationTemplate.getPaths(3, new Position("a1"), new Position("e6"));
        Assert.assertEquals(3, paths.size());
        Path expectedPath1 = (new Path(new Position("a1")))
                .copyAndAppend(new Position("b3"))
                .copyAndAppend(new Position("c5"))
                .copyAndAppend(new Position("e6"));
        paths.contains(expectedPath1);
        Assert.assertTrue(paths.contains(expectedPath1));
        Path expectedPath2 = (new Path(new Position("a1")))
                .copyAndAppend(new Position("b3"))
                .copyAndAppend(new Position("d4"))
                .copyAndAppend(new Position("e6"));
        Assert.assertTrue(paths.contains(expectedPath2));
        Path expectedPath3 = (new Path(new Position("a1")))
                .copyAndAppend(new Position("c2"))
                .copyAndAppend(new Position("d4"))
                .copyAndAppend(new Position("e6"));
        Assert.assertTrue(paths.contains(expectedPath3));
    }

    @Test
    public void pathCalculationNoPathTest() throws IncorrectPositionException {
        PieceMovesStrategy pieceMovesStrategy = new KnightMovesStrategy();
        PathCalculationTemplate pathCalculationTemplate = new SimplePathCalculationTemplate(pieceMovesStrategy);
        Set<Path> paths = pathCalculationTemplate.getPaths(3, new Position("a1"), new Position("c5"));
        Assert.assertEquals(0, paths.size());

    }

}

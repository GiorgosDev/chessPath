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
    public void pathCalculation3StepsTest() throws IncorrectPositionException {
        PieceMovesStrategy pieceMovesStrategy = new KnightMovesStrategy();
        PathCalculationTemplate pathCalculationTemplate = new SimplePathCalculationTemplate(pieceMovesStrategy);
        Set<Path> paths = pathCalculationTemplate.getPaths(3, new Position("a1"), new Position("e6"));
        Assert.assertEquals(3, paths.size());
        Path expectedPath1 = (new Path(new Position("a1")))
                .copyAndAppend(new Position("b3"))
                .copyAndAppend(new Position("c5"))
                .copyAndAppend(new Position("e6"));
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
    public void pathCalculation3StepsOtherPathTest() throws IncorrectPositionException {
        PieceMovesStrategy pieceMovesStrategy = new KnightMovesStrategy();
        PathCalculationTemplate pathCalculationTemplate = new SimplePathCalculationTemplate(pieceMovesStrategy);
        Set<Path> paths = pathCalculationTemplate.getPaths(3, new Position("b6"), new Position("f5"));
        Assert.assertEquals(6, paths.size());
        Path expectedPath1 = (new Path(new Position("b6")))
                .copyAndAppend(new Position("c8"))
                .copyAndAppend(new Position("e7"))
                .copyAndAppend(new Position("f5"));
        Assert.assertTrue(paths.contains(expectedPath1));
        Path expectedPath2 = (new Path(new Position("b6")))
                .copyAndAppend(new Position("c8"))
                .copyAndAppend(new Position("d6"))
                .copyAndAppend(new Position("f5"));
        Assert.assertTrue(paths.contains(expectedPath2));
        Path expectedPath3 = (new Path(new Position("b6")))
                .copyAndAppend(new Position("d5"))
                .copyAndAppend(new Position("e3"))
                .copyAndAppend(new Position("f5"));
        Assert.assertTrue(paths.contains(expectedPath3));
        Path expectedPath4 = (new Path(new Position("b6")))
                .copyAndAppend(new Position("c4"))
                .copyAndAppend(new Position("e3"))
                .copyAndAppend(new Position("f5"));
        Assert.assertTrue(paths.contains(expectedPath4));
        Path expectedPath5 = (new Path(new Position("b6")))
                .copyAndAppend(new Position("d5"))
                .copyAndAppend(new Position("e7"))
                .copyAndAppend(new Position("f5"));
        Assert.assertTrue(paths.contains(expectedPath5));
        Path expectedPath6 = (new Path(new Position("b6")))
                .copyAndAppend(new Position("c4"))
                .copyAndAppend(new Position("d6"))
                .copyAndAppend(new Position("f5"));
        Assert.assertTrue(paths.contains(expectedPath6));
    }

    @Test
    public void pathCalculation3StepsNoPathTest() throws IncorrectPositionException {
        PieceMovesStrategy pieceMovesStrategy = new KnightMovesStrategy();
        PathCalculationTemplate pathCalculationTemplate = new SimplePathCalculationTemplate(pieceMovesStrategy);
        Set<Path> paths = pathCalculationTemplate.getPaths(3, new Position("a1"), new Position("c5"));
        Assert.assertEquals(0, paths.size());

    }

    @Test
    public void pathCalculationOneStepPathTest() throws IncorrectPositionException {
        PieceMovesStrategy pieceMovesStrategy = new KnightMovesStrategy();
        PathCalculationTemplate pathCalculationTemplate = new SimplePathCalculationTemplate(pieceMovesStrategy);
        Set<Path> paths = pathCalculationTemplate.getPaths(1, new Position("b6"), new Position("c5"));
        Assert.assertEquals(0, paths.size());
    }

}

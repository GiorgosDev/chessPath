package com.gio.chess.path.calculation;

import com.gio.chess.path.calculation.PathCalculationTemplate;
import com.gio.chess.path.calculation.SimplePathCalculationTemplate;
import com.gio.chess.path.exceptions.IncorrectPositionException;
import com.gio.chess.path.model.Path;
import com.gio.chess.path.model.Position;
import com.gio.chess.path.moves.KingMovesStrategy;
import com.gio.chess.path.moves.KnightMovesStrategy;
import com.gio.chess.path.moves.PieceMovesStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class KingPathCalculatorTest {

    @Test
    public void pathCalculation3StepsTest() throws IncorrectPositionException {
        PieceMovesStrategy pieceMovesStrategy = new KingMovesStrategy();
        PathCalculationTemplate pathCalculationTemplate = new SimplePathCalculationTemplate(pieceMovesStrategy);
        Set<Path> paths = pathCalculationTemplate.getPaths(3, new Position("b5"), new Position("e5"));
        Assert.assertEquals(7, paths.size());
        Path expectedPath1 = (new Path(new Position("b5")))
                .copyAndAppend(new Position("c4"))
                .copyAndAppend(new Position("d4"))
                .copyAndAppend(new Position("e5"));
        Assert.assertTrue(paths.contains(expectedPath1));
        expectedPath1 = (new Path(new Position("b5")))
                .copyAndAppend(new Position("c4"))
                .copyAndAppend(new Position("d5"))
                .copyAndAppend(new Position("e5"));
        Assert.assertTrue(paths.contains(expectedPath1));
        expectedPath1 = (new Path(new Position("b5")))
                .copyAndAppend(new Position("c5"))
                .copyAndAppend(new Position("d5"))
                .copyAndAppend(new Position("e5"));
        Assert.assertTrue(paths.contains(expectedPath1));
        expectedPath1 = (new Path(new Position("b5")))
                .copyAndAppend(new Position("c5"))
                .copyAndAppend(new Position("d4"))
                .copyAndAppend(new Position("e5"));
        Assert.assertTrue(paths.contains(expectedPath1));
        expectedPath1 = (new Path(new Position("b5")))
                .copyAndAppend(new Position("c5"))
                .copyAndAppend(new Position("d6"))
                .copyAndAppend(new Position("e5"));
        Assert.assertTrue(paths.contains(expectedPath1));
        expectedPath1 = (new Path(new Position("b5")))
                .copyAndAppend(new Position("c6"))
                .copyAndAppend(new Position("d6"))
                .copyAndAppend(new Position("e5"));
        Assert.assertTrue(paths.contains(expectedPath1));
        expectedPath1 = (new Path(new Position("b5")))
                .copyAndAppend(new Position("c6"))
                .copyAndAppend(new Position("d5"))
                .copyAndAppend(new Position("e5"));
        Assert.assertTrue(paths.contains(expectedPath1));
    }


    @Test
    public void pathCalculationOneStepPathTest() throws IncorrectPositionException {
        PieceMovesStrategy pieceMovesStrategy = new KingMovesStrategy();
        PathCalculationTemplate pathCalculationTemplate = new SimplePathCalculationTemplate(pieceMovesStrategy);
        Set<Path> paths = pathCalculationTemplate.getPaths(1, new Position("b6"), new Position("h1"));
        Assert.assertEquals(0, paths.size());
    }

}

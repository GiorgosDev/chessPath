package com.gio.chess.path.model;

import com.gio.chess.path.exceptions.IncorrectPositionException;
import com.gio.chess.path.model.Knight;
import com.gio.chess.path.model.Path;
import com.gio.chess.path.model.Position;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class PiecePathCalculatorTest {

    @Test
    public void pathCalculationTest() throws IncorrectPositionException {
        Knight knight= new Knight(new Position("a1"));
        Position end = new Position("e6");
        Set<Path> paths = knight.getPaths(3, end);
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
        Knight knight= new Knight(new Position("a1"));
        Position end = new Position("c5");
        Set<Path> paths = knight.getPaths(3, end);
        Assert.assertEquals(0, paths.size());

    }

}

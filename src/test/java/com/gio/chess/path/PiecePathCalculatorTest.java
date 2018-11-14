package com.gio.chess.path;

import com.gio.chess.path.exceptions.IncorrectPositionException;
import com.gio.chess.path.model.Position;
import com.gio.chess.path.model.Knight;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class PiecePathCalculatorTest {

    @Test
    public void pathCalculationTest() throws IncorrectPositionException {
        Knight knight= new Knight(new Position("a1"));
        Position end = new Position("d4");
        Set<List<Position>> paths = knight.getPaths(3, end);
        Assert.assertEquals(3, paths.size());
    }
}

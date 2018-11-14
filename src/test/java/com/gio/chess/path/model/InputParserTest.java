package com.gio.chess.path.model;

import com.gio.chess.path.exceptions.IncorrectPositionException;
import com.gio.chess.path.model.Position;
import org.junit.Assert;
import org.junit.Test;

public class InputParserTest {

    @Test
    public void testParseCorrectPosition() throws IncorrectPositionException {
        Position position = new Position("a1");
        Assert.assertEquals( new Position(0, 0), position);

        position = new Position("b1");
        Assert.assertEquals( new Position(1, 0), position);

        position = new Position("h8");
        Assert.assertEquals( new Position(7, 7), position);

        position = new Position("e6");
        Assert.assertEquals( new Position(4, 5), position);
    }

    @Test(expected = IncorrectPositionException.class)
    public void testParseInCorrectPositionLetter() throws IncorrectPositionException {
        new Position("l1");
    }

    @Test(expected = IncorrectPositionException.class)
    public void testParseInCorrectPositionNumber() throws IncorrectPositionException {
        new Position("a9");
    }



}

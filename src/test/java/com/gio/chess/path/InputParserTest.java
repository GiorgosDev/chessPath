package com.gio.chess.path;

import org.junit.Assert;
import org.junit.Test;

public class InputParserTest {

    @Test
    public void testParseCorrectPosition(){
        Position position = new Position("a1");
        Assert.assertEquals( new Position(0, 0), position);
    }


}

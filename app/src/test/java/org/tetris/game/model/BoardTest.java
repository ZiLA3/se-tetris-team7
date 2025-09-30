package org.tetris.game.model;

import static org.junit.Assert.*;
import org.extension.Point;
import org.junit.Test;

public class BoardTest {

    @Test
    public void testBoardToString()
    {
        Board board = new Board(2, 2);
        String expected = "0 0 \n0 0 \n";
        assertEquals(expected, board.toString());
    }

    @Test
    public void testCanPlaceBlock()
    {
        Board board = new Board(4, 4);
        Block block = new Block(new int[][]{
            {1, 1},
            {1, 0}
        }, new Point(0, 0));

        assertTrue(board.canPlaceBlock(block, new Point(1, 1)));
        assertFalse(board.canPlaceBlock(block, new Point(3, 3)));
    }

    @Test
    public void testPlaceBlock()
    {
        Board board = new Board(4, 4);
        Block block = new Block(new int[][]{
            {1, 1},
            {1, 0}
        }, new Point(0, 0));

        board.placeBlock(block, new Point(1, 1));

        String expected = 
            "0 0 0 0 \n" + 
            "0 1 1 0 \n" + 
            "0 1 0 0 \n" + 
            "0 0 0 0 \n";

        assertEquals(expected, board.toString());
    }
}

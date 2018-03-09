package com.xm.interview.chess.chessboard;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Ivan Zemlyanskiy
 */
public class ChessboardTest {

    @Test
    public void testChessboardSquare() {
        Chessboard chessboard = new Chessboard();

        Square f2 = chessboard.findSquare("f2").get();
        assertEquals('f', f2.getFile());
        assertEquals(2, f2.getRank());
    }

    @Test
    public void testIncorrectSquare() {
        assertFalse(new Chessboard().findSquare("[2").isPresent());
        assertFalse(new Chessboard().findSquare("a123").isPresent());
        assertFalse(new Chessboard(1).findSquare("f2").isPresent());
        assertFalse(new Chessboard(1).findSquare("a2").isPresent());
    }


    @Test
    public void testShift() {
        Chessboard chessboard = new Chessboard(3);
        Square square = chessboard.findSquare("a1").get();
        assertTrue(chessboard.shiftSquare(square, 1, 1).isPresent());
        assertFalse(chessboard.shiftSquare(square, -1, 1).isPresent());
        assertFalse(chessboard.shiftSquare(square, 1, -1).isPresent());
        assertFalse(chessboard.shiftSquare(square, 3, 1).isPresent());
        assertFalse(chessboard.shiftSquare(square, 1, 3).isPresent());
    }
}
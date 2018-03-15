package com.xm.interview.chess.path;

import com.xm.interview.chess.chessboard.Chessboard;
import com.xm.interview.chess.chessboard.Square;
import com.xm.interview.chess.piece.King;
import com.xm.interview.chess.piece.Piece;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Ivan Zemlyanskiy
 */
public class KingDynamicProgrammingTest {


    private Chessboard chessboard = new Chessboard();
    private Piece piece = new King();

    @Test
    public void testDot() {
        Square a1 = chessboard.findSquare("a1").get();
        Game game = new DynamicProgramming(chessboard, a1, a1, piece, 3);
        List<Square> path = game.findPath();
        assertEquals(1, path.size());
        assertEquals(a1, path.get(0));
    }


    @Test
    public void testOneMove() {
        Game game = new DynamicProgramming(chessboard, "a1", "a2", piece);
        assertEquals("[a1, a2]", game.findPath().toString());
    }

    @Test
    public void testTwoMovies() {
        Game game = new DynamicProgramming(new Chessboard(5), "a1", "b3", piece);
        game.findPath();
    }

    @Test
    public void testDiagonal() {
        Game game = new DynamicProgramming(chessboard, "a1", "h8", piece);
        assertEquals("[a1, b2, c3, d4, e5, f6, g7, h8]", game.findPath().toString());
    }

    @Test
    public void testHorizontalLine() {
        Game game = new DynamicProgramming(chessboard, "a1", "h1", piece);
        assertEquals("[a1, b1, c1, d1, e1, f1, g1, h1]", game.findPath().toString());
    }

    @Test
    public void testVerticalLine() {
        Game game = new DynamicProgramming(chessboard, "a1", "a8", piece);
        assertEquals("[a1, a2, a3, a4, a5, a6, a7, a8]", game.findPath().toString());
    }

    @Test
    public void testNoPath() {
        Game game = new DynamicProgramming(chessboard, "a1", "a8", piece, 3);
        assertEquals("[]", game.findPath().toString());
    }
}
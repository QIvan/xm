package com.xm.interview.chess;

import com.xm.interview.Solution;
import com.xm.interview.chess.chessboard.Chessboard;
import com.xm.interview.chess.chessboard.Square;
import com.xm.interview.chess.piece.King;
import com.xm.interview.chess.piece.Knight;
import com.xm.interview.chess.piece.Piece;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * @author Ivan Zemlyanskiy
 */
public class KingSolutionTest {


    private Chessboard chessboard = new Chessboard();
    private Piece piece = new King();

    @Test
    public void testDot() {
        Square a1 = chessboard.findSquare("a1").get();
        Solution solution = new Solution(chessboard, a1, a1, piece);
        List<Square> path = solution.findPath(3);
        assertEquals(1, path.size());
        assertEquals(a1, path.get(0));
    }


    @Test
    public void testOneMove() {
        Solution solution = new Solution(chessboard, "a1", "a2", piece);
        assertEquals("[a1, a2]", solution.findPath().toString());
    }

    @Test
    public void testTwoMovies() {
        Solution solution = new Solution(new Chessboard(5), "a1", "b3", piece);
        solution.findPath();
    }

    @Test
    public void testDiagonal() {
        Solution solution = new Solution(chessboard, "a1", "h8", piece);
        assertEquals("[a1, b2, c3, d4, e5, f6, g7, h8]", solution.findPath().toString());
    }

    @Test
    public void testHorizontalLine() {
        Solution solution = new Solution(chessboard, "a1", "h1", piece);
        assertEquals("[a1, b1, c1, d1, e1, f1, g1, h1]", solution.findPath().toString());
    }

    @Test
    public void testVerticalLine() {
        Solution solution = new Solution(chessboard, "a1", "a8", piece);
        assertEquals("[a1, a2, a3, a4, a5, a6, a7, a8]", solution.findPath().toString());
    }

    @Test
    public void testNoPath() {
        Solution solution = new Solution(chessboard, "a1", "a8", piece);
        assertEquals("[]", solution.findPath(3).toString());
    }
}
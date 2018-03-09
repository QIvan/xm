package com.xm.interview.chess;

import com.xm.interview.Solution;
import com.xm.interview.chess.chessboard.Chessboard;
import com.xm.interview.chess.chessboard.Square;
import com.xm.interview.chess.piece.King;
import com.xm.interview.chess.piece.Knight;
import com.xm.interview.chess.piece.Piece;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Ivan Zemlyanskiy
 */
public class KnightSolutionTest {


    private Chessboard chessboard = new Chessboard();
    private Piece piece = new Knight();

    @Test
    public void testDot() {
        Square a1 = chessboard.findSquare("a1").get();
        Solution solution = new Solution(chessboard, a1, a1, new Knight());
        List<Square> path = solution.findPath(3);
        assertEquals(1, path.size());
        assertEquals(a1, path.get(0));
    }


    @Test
    public void testNextToStart() {
        Square start = chessboard.findSquare("a1").get();
        Square finish = chessboard.findSquare("a2").get();
        Solution solution = new Solution(chessboard, start, finish, piece);
        List<Square> path = solution.findPath();
        assertEquals(4, path.size());
        assertTrue(piece.findAllMoves(chessboard, start).anyMatch(square -> square.equals(path.get(1))));
        assertTrue(piece.findAllMoves(chessboard, finish).anyMatch(square -> square.equals(path.get(2))));

    }

}
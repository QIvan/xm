package com.xm.interview.chess.path;

import com.xm.interview.chess.chessboard.Chessboard;
import com.xm.interview.chess.chessboard.Square;
import com.xm.interview.chess.piece.Knight;
import com.xm.interview.chess.piece.Piece;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Ivan Zemlyanskiy
 */
public class KnightDynamicProgrammingTest {


    private Chessboard chessboard = new Chessboard();
    private Piece piece = new Knight();

    @Test
    public void testDot() {
        Square a1 = chessboard.findSquare("a1").get();
        Game game = new DynamicProgramming(chessboard, a1, a1, new Knight(),3);
        List<Square> path = game.findPath();
        assertEquals(1, path.size());
        assertEquals(a1, path.get(0));
    }


    @Test
    public void testNextToStart() {
        Square start = chessboard.findSquare("a1").get();
        Square finish = chessboard.findSquare("a2").get();
        Game game = new DynamicProgramming(chessboard, start, finish, piece);
        List<Square> path = game.findPath();
        assertEquals(4, path.size());
        assertTrue(piece.findAllNeighbors(chessboard, start).anyMatch(square -> square.equals(path.get(1))));
        assertTrue(piece.findAllNeighbors(chessboard, finish).anyMatch(square -> square.equals(path.get(2))));

    }

}
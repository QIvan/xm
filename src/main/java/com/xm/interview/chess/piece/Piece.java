package com.xm.interview.chess.piece;

import com.xm.interview.chess.chessboard.Chessboard;
import com.xm.interview.chess.chessboard.Square;

import java.util.stream.Stream;

/**
 * @author Ivan Zemlyanskiy
 */
public interface Piece {

    Stream<Square> findAllMoves(Chessboard chessboard, Square currentSquare);

}

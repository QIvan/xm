package com.xm.interview.chess.piece;

import com.xm.interview.chess.chessboard.Chessboard;
import com.xm.interview.chess.chessboard.Square;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Ivan Zemlyanskiy
 */
public class King implements Piece {


    @Override
    public Stream<Square> findAllMoves(Chessboard chessboard, Square currentSquare) {
        return Stream.of(
                chessboard.shiftSquare(currentSquare, 0, 1),
                chessboard.shiftSquare(currentSquare, 1, 0),
                chessboard.shiftSquare(currentSquare, 0, -1),
                chessboard.shiftSquare(currentSquare, -1, 0),

                chessboard.shiftSquare(currentSquare, 1, 1),
                chessboard.shiftSquare(currentSquare, -1, 1),
                chessboard.shiftSquare(currentSquare, -1, -1),
                chessboard.shiftSquare(currentSquare, 1, -1)
        )
                .filter(Optional::isPresent)
                .map(Optional::get);
    }
}

package com.xm.interview.chess.piece;

import com.xm.interview.chess.chessboard.Chessboard;
import com.xm.interview.chess.chessboard.Square;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Ivan Zemlyanskiy
 */
public class Knight implements Piece {


    @Override
    public Stream<Square> findAllNeighbors(Chessboard chessboard, Square currentSquare) {
        return Stream.of(
//              x 1 x 8 x
//              2 x x x 7
//              x x 0 x x
//              3 x x x 6
//              x 4 x 5 x
                chessboard.shiftSquare(currentSquare, -1, 2),       // 1
                chessboard.shiftSquare(currentSquare, -2, 1),       // 2
                chessboard.shiftSquare(currentSquare, -2, -1),      // 3
                chessboard.shiftSquare(currentSquare, -1, -2),      // 4
                chessboard.shiftSquare(currentSquare, 1, -2),       // 5
                chessboard.shiftSquare(currentSquare, 2, -1),       // 6
                chessboard.shiftSquare(currentSquare, 2, 1),        // 7
                chessboard.shiftSquare(currentSquare, 1, 2)         // 8
        )
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

}

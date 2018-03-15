package com.xm.interview.chess.path;

import com.xm.interview.chess.chessboard.Chessboard;
import com.xm.interview.chess.chessboard.Square;

import java.util.List;

/**
 * @author Ivan Zemlyanskiy
 */
public interface Game {
    int DEFAULT_MAX_MOVES = Chessboard.DEFAULT_CHESSBOARD_SIZE * 2;

    List<Square> findPath();

    Statistic getStatistic();
}

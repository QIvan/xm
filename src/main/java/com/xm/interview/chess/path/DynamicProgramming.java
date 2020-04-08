package com.xm.interview.chess.path;

import com.xm.interview.chess.chessboard.Chessboard;
import com.xm.interview.chess.chessboard.Square;
import com.xm.interview.chess.piece.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ivan Zemlyanskiy
 */
public class DynamicProgramming implements Game {

    private final Chessboard chessboard;
    private final Square start;
    private final Square finish;
    private final Piece piece;
    private final int maxMoves;

    private final int[][] solutionTable;

    private int iterations = 0;


    public DynamicProgramming(Chessboard chessboard,
                              Square start, Square finish,
                              Piece piece) {
        this(chessboard, start, finish, piece, DEFAULT_MAX_MOVES);
    }

    public DynamicProgramming(Chessboard chessboard,
                              Square start, Square finish,
                              Piece piece, int maxMoves) {
        this.chessboard = chessboard;
        this.start = start;
        this.finish = finish;
        this.piece = piece;
        this.maxMoves = maxMoves;
        solutionTable = new int[chessboard.getSize()][chessboard.getSize()];
    }

    public DynamicProgramming(Chessboard chessboard,
                              String start, String finish,
                              Piece piece) {
        this(chessboard, start, finish, piece, DEFAULT_MAX_MOVES);
    }

    public DynamicProgramming(Chessboard chessboard,
                              String start, String finish,
                              Piece piece, int maxMoves) {
        this(chessboard,
             chessboard.findSquare(start)
                     .orElseThrow(() -> new IllegalArgumentException("Square " + start + " is out of chessboard")),
             chessboard.findSquare(finish)
                     .orElseThrow(() -> new IllegalArgumentException("Square " + finish + " is out of chessboard")),
             piece,
             maxMoves
        );
    }

    public List<Square> findPath() {

        // 0 - there was no computation
        // 1 - finish square
        fillSolutionTable(maxMoves + 1, finish, 1);
        return restoreSolution(start);
    }

    private void fillSolutionTable(int maxMoves, Square current, int depth) {
        iterations++;
        if (depth >= maxMoves) {
            return;
        }
        setMoves(current, depth);
        int neighborDeepViaCurrent = depth + 1;
        piece.findAllNeighbors(chessboard, current).forEach(neighbor -> {
            int neighborMovies = getMoves(neighbor);
            if (neighborMovies == 0 || neighborMovies > neighborDeepViaCurrent) {
                setMoves(neighbor, neighborDeepViaCurrent);
                fillSolutionTable(maxMoves, neighbor, neighborDeepViaCurrent);
            }
        });
    }

    private List<Square> restoreSolution(Square start) {
        int currentValue = getMoves(start);
        if (currentValue == 0) {
            return Collections.emptyList();
        }

        Square current = start;
        List<Square> result = new ArrayList<>(currentValue);
        while (currentValue != 1) {
            result.add(current);

            int neighborValue = currentValue - 1;
            current = piece.findAllNeighbors(chessboard, current)
                    .filter(square -> getMoves(square) == neighborValue)
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("Incorrect solution table!"));

            currentValue = neighborValue;
        }

        assert current.equals(finish);
        result.add(finish);
        return result;
    }


    private int getMoves(Square square) {
        return solutionTable
                [chessboard.getSize() - 1 - chessboard.rankAsCoordinate(square)]
                [chessboard.fileAsCoordinate(square)];
    }

    private void setMoves(Square square, int value) {
        solutionTable
                [chessboard.getSize() - 1 - chessboard.rankAsCoordinate(square)]
                [chessboard.fileAsCoordinate(square)] = value;
    }


    public Statistic getStatistic() {
        return new Statistic(solutionTable, iterations);
    }

}

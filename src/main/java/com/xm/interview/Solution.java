package com.xm.interview;

import com.xm.interview.chess.chessboard.Chessboard;
import com.xm.interview.chess.chessboard.Square;
import com.xm.interview.chess.piece.Piece;
import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ivan Zemlyanskiy
 */
public class Solution {
    public static final int DEFAULT_MAX_MOVES = Chessboard.DEFAULT_CHESSBOARD_SIZE * 2;

    private final Chessboard chessboard;
    private final Square start;
    private final Square finish;
    private final Piece piece;

    private final int[][] solutionTable;

    private int iterations = 0;

    public Solution(Chessboard chessboard,
                    Square start, Square finish,
                    Piece piece) {
        this.chessboard = chessboard;
        this.start = start;
        this.finish = finish;
        this.piece = piece;
        solutionTable = new int[chessboard.getSize()][chessboard.getSize()];
    }

    public Solution(Chessboard chessboard,
                    String start, String finish,
                    Piece piece) {
        this(chessboard,
             chessboard.findSquare(start)
                     .orElseThrow(() -> new IllegalArgumentException("Square " + start + " is out of chessboard")),
             chessboard.findSquare(finish)
                     .orElseThrow(() -> new IllegalArgumentException("Square " + finish + " is out of chessboard")),
             piece
        );

    }

    public List<Square> findPath() {
        return findPath(DEFAULT_MAX_MOVES);
    }

    public List<Square> findPath(int maxMoves) {

        // 0 - there was no computation
        // 1 - finish square
        fillSolutionTable(maxMoves + 1, finish, 1);
        return restoreSolution(start);
    }

    private void fillSolutionTable(int maxMoves, Square current, int deep) {
        iterations++;
        if (deep >= maxMoves) {
            return;
        }
        setMoves(current, deep);
        int nextDeepViaCurrent = deep + 1;
        piece.findAllMoves(chessboard, current).forEach(next -> {
            int nextMovies = getMoves(next);
            if (nextMovies == 0 || nextMovies > nextDeepViaCurrent) {
                setMoves(next, nextDeepViaCurrent);
                fillSolutionTable(maxMoves, next, nextDeepViaCurrent);
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

            int nextValue = currentValue - 1;
            current = piece.findAllMoves(chessboard, current)
                    .filter(square -> getMoves(square) == nextValue)
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("Incorrect solution table!"));

            currentValue = nextValue;
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

    @Value
    public class Statistic {
        int[][] solutionTable;
        int iterations;

        public String printableSolutionTable() {
            StringBuilder result = new StringBuilder();
            result.append("   ");
            for (int i = 0; i < solutionTable.length; i++) {
                result.append(Chessboard.CHESS_LETTERS[i]);
                result.append("\t");
            }
            result.append("\n");
            
            result.append("   ");
            for (int i = 0; i < solutionTable.length; i++) {
                result.append("-");
                result.append("\t");
            }
            result.append("\n");

            int startColumn = chessboard.fileAsCoordinate(start);
            int startLine = solutionTable.length - start.getRank();
            int finishColumn = chessboard.fileAsCoordinate(finish);
            int finishLine = solutionTable.length - finish.getRank();
            
            for (int i = 0; i < solutionTable.length; i++) {
                result.append(solutionTable.length - i);
                result.append("| ");
                for (int j = 0; j < solutionTable.length; j++) {
                    if (j == startColumn && i == startLine) {
                        result.append("[");
                    }
                    if (j == finishColumn && i == finishLine) {
                        result.append("{");
                    }

                    result.append(solutionTable[i][j]);

                    if (j == startColumn && i == startLine) {
                        result.append("]");
                    }
                    if (j == finishColumn && i == finishLine) {
                        result.append("}");
                    }
                    result.append("\t");
                }
                result.append("\n");
            }
            result.append("\n");
            return result.toString();
        }
    }

}

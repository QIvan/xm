package com.xm.interview.chess.path;

import com.xm.interview.chess.chessboard.Chessboard;
import com.xm.interview.chess.chessboard.Square;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

/**
 * @author Ivan Zemlyanskiy
 */
@Value
@AllArgsConstructor
public class Statistic {
    int[][] solutionTable;
    int iterations;


    public String printableSolutionTable(Chessboard chessboard, Square start, Square finish) {
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

package com.xm.interview.chess.chessboard;

import lombok.Getter;

import java.util.Optional;

/**
 * @author Ivan Zemlyanskiy
 */
public class Chessboard {
    public static final char[] CHESS_LETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();;

    public static final int MAX_CHESSBOARD_SIZE = CHESS_LETTERS.length;
    public static final int DEFAULT_CHESSBOARD_SIZE = 8;

    @Getter
    private final int size;

    public Chessboard(int size) {
        this.size = size;
    }

    public Chessboard() {
        size = DEFAULT_CHESSBOARD_SIZE;
    }


    public Optional<Square> findSquare(String square) {
        Square result = new Square(square);
        if (incorrectSquare(result)) {
            return Optional.empty();
        }

        return Optional.of(result);
    }

    public Optional<Square> shiftSquare(Square square, int fileShift, int rankShift) {
        int fileAsNumberWithShift = fileAsCoordinate(square) + fileShift;
        if (fileAsNumberWithShift < 0 || fileAsNumberWithShift >= MAX_CHESSBOARD_SIZE) {
            return Optional.empty();
        }
        Square result = new Square(
                CHESS_LETTERS[fileAsNumberWithShift],
                square.getRank() + rankShift
        );
        if (incorrectSquare(result)) {
            return Optional.empty();
        }

        return Optional.of(result);
    }

    private boolean incorrectSquare(Square square) {
        int fileAsNumber = fileAsCoordinate(square);
        int rank = rankAsCoordinate(square);
        return fileAsNumber < 0 || rank < 0 ||
                fileAsNumber >= size || rank >= size;
    }

    public int fileAsCoordinate(Square square) {
        return square.getFile() - CHESS_LETTERS[0];
    }

    public int rankAsCoordinate(Square square) {
        return square.getRank() - 1;
    }

}

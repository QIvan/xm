package com.xm.interview.chess.chessboard;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

/**
 * Chess square in algebraic notation
 * https://en.wikipedia.org/wiki/Algebraic_notation_(chess)#Naming_the_squares
 *
 * @author Ivan Zemlyanskiy
 */
@Value
@AllArgsConstructor
public class Square {

    /**
     * https://en.wikipedia.org/wiki/Glossary_of_chess#file
     */
    char file;
    /**
     * https://en.wikipedia.org/wiki/Glossary_of_chess#rank
     */
    int rank;

    Square (@NonNull String square) {
        file = square.charAt(0);
        rank = Integer.valueOf(String.valueOf(square.substring(1)));
    }


    @Override
    public String toString() {
        return "" + file + rank;
    }

}

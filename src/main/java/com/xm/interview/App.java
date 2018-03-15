package com.xm.interview;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.xm.interview.chess.chessboard.Chessboard;
import com.xm.interview.chess.chessboard.Square;
import com.xm.interview.chess.path.DynamicProgramming;
import com.xm.interview.chess.path.Game;
import com.xm.interview.chess.path.Statistic;
import com.xm.interview.chess.piece.King;
import com.xm.interview.chess.piece.Knight;
import com.xm.interview.chess.piece.Piece;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@ToString
public class App {

    public enum PieceType {
        King(new King()),
        Knight(new Knight()),
        ;

        @Getter
        private final Piece piece;

        PieceType(Piece piece) {
            this.piece = piece;
        }
    }

    @Parameter(names = {"-c", "--chessboard-size"}, description = "Size of chessboard")
    private Integer chessboardSize = Chessboard.DEFAULT_CHESSBOARD_SIZE;

    @Parameter(names = {"-s", "--start" }, required = true, description = "Start square")
    private String start;

    @Parameter(names = {"-f", "-e", "--finish", "--end"}, required = true, description = "Finish square")
    private String finish;

    @Parameter(names = {"-p", "--piece"}, description = "Type of piece")
    private PieceType piece = PieceType.Knight;

    @Parameter(names = {"--debug", "--print-statistic"}, description = "Print statistic after solution")
    private boolean printStatistic = true;


    public static void main(String[] input) {
        App args = new App();
        try {
            JCommander.newBuilder()
                    .addObject(args)
                    .build()
                    .parse(input);

            Chessboard chessboard = new Chessboard(args.chessboardSize);
            Game game = new DynamicProgramming(
                    chessboard,
                    args.start,
                    args.finish,
                    args.piece.getPiece()
            );
            
            List<Square> path = game.findPath();
            System.out.println(
                    path.stream()
                            .map(Square::toString)
                            .collect(Collectors.joining(" -> ")));

            if (args.printStatistic) {
                Statistic statistic = game.getStatistic();
                System.out.println();
                System.out.println("Iterations " + statistic.getIterations());
                System.out.println();
                System.out.println("Result chessboard.");
                System.out.println("  [] - start ");
                System.out.println("  {} - finish");
                Square start = chessboard.findSquare(args.start).orElseThrow(RuntimeException::new);
                Square finish = chessboard.findSquare(args.finish).orElseThrow(RuntimeException::new);
                System.out.println(statistic.printableSolutionTable(chessboard, start, finish));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println();
            usage();
        }
    }

    private static void usage() {
        JCommander.newBuilder()
                .addObject(new App())
                .build()
                .usage();
    }
}

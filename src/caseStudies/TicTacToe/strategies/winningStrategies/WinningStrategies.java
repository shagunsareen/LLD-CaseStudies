package caseStudies.TicTacToe.strategies.winningStrategies;

import caseStudies.TicTacToe.models.Move;
import caseStudies.TicTacToe.models.Board;

public interface WinningStrategies {
    boolean checkWinner(Move move, Board board);

    void handleUndo(Board board, Move lastMove);
    //board is required because we need to check board size for n-1 diagonal
}

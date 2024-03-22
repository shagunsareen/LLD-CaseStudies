package caseStudies.TicTacToe.strategies.botPlayingStrategies;


import caseStudies.TicTacToe.models.Board;
import caseStudies.TicTacToe.models.Cell;

public interface BotPlayingStrategy {
    Cell makeMove(Board board); //board is required so that bot can decide where to make the next move
}

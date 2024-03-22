package caseStudies.TicTacToe.controllers;

import caseStudies.TicTacToe.exceptions.InvalidGameParamsException;
import caseStudies.TicTacToe.models.Board;
import caseStudies.TicTacToe.models.Game;
import caseStudies.TicTacToe.models.GameStatus;
import caseStudies.TicTacToe.models.Player;
import caseStudies.TicTacToe.strategies.winningStrategies.WinningStrategies;

import java.util.List;

public class GameController {

    public Game createGame(int dimension,
                           List<Player> players,
                           List<WinningStrategies> winningStrategyList) throws InvalidGameParamsException {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategyList)
                .build();
    }


    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public void printResult(Game game) {
        game.printResult();
    }

    //reverse for whatever happened on makingMove
    public void undo(Game game) {
        game.undo();
    }
}

package TicTacToe.controllers;

import TicTacToe.exceptions.InvalidGameParamsException;
import TicTacToe.models.Game;
import TicTacToe.models.GameStatus;
import TicTacToe.models.Player;
import TicTacToe.strategies.winningstrategies.WinningStrategy;

import java.util.List;

public class GameController {

    public Game createGame(int dimension,
                           List<Player> players,
                           List<WinningStrategy> winningStrategies) throws InvalidGameParamsException {

        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void displayBoard(Game game){
        game.printBoard();
    }

    public void printResult(Game game){
        game.printResult();
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public void undo(Game game){
        game.undo();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }


}

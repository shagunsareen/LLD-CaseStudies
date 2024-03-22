package caseStudies.TicTacToe;

import caseStudies.TicTacToe.controllers.GameController;
import caseStudies.TicTacToe.exceptions.InvalidGameParamsException;
import caseStudies.TicTacToe.models.*;
import caseStudies.TicTacToe.strategies.winningStrategies.ColumnWinningStrategy;
import caseStudies.TicTacToe.strategies.winningStrategies.DiagonalWinningStrategy;
import caseStudies.TicTacToe.strategies.winningStrategies.RowWinningStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Create a game
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        int dimension = 3;
        List<Player> players = List.of(
                new Player("Naman", new Symbol('X'), PlayerType.HUMAN),
                new Bot("Aman", new Symbol('O'), BotDifficultyLevel.EASY));
        Game game;

        try {
            game = gameController.createGame(
                    dimension,
                    players,
                    List.of(
                            new DiagonalWinningStrategy(players),
                            new RowWinningStrategy(dimension, players),
                            new ColumnWinningStrategy(dimension, players)
                    ));
        } catch (InvalidGameParamsException e) {
            System.out.println("Seems like you gave invalid params. Update those.");
            return;
        }

        System.out.println("------ Game is starting ------");

        //while game status is in progress
        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            System.out.println("This is how the board looks like :");
            gameController.displayBoard(game);

            System.out.println("Does anyone want to undo? (y/n)");
            //if yes -> call undo
            String input = scanner.next();

            if(input.equalsIgnoreCase("y")){
                gameController.undo(game);
            }else{
                //move next player
                gameController.makeMove(game);
            }

        }

        gameController.printResult(game);
        gameController.displayBoard(game);
    }
}

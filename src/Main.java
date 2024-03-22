import TicTacToe.controllers.GameController;
import TicTacToe.exceptions.InvalidGameParamsException;
import TicTacToe.models.*;
import TicTacToe.strategies.winningstrategies.OrderOneColumnWinningStrategy;
import TicTacToe.strategies.winningstrategies.OrderOneDiagonalWinningStrategy;
import TicTacToe.strategies.winningstrategies.OrderOneRowWinningStrategy;

import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //Create a game
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        Game game;
        List<Player> players = List.of(
                new Player(new Symbol('X'), "Naman", PlayerType.HUMAN),
                new Bot(new Symbol('O'), "Aman", BotDifficultyLevel.EASY));

        int dimension = 3;

        try{
            game = gameController.createGame(
                    dimension,
                    players,
                    List.of(
                            new OrderOneDiagonalWinningStrategy(players),
                            new OrderOneColumnWinningStrategy(dimension, players),
                            new OrderOneRowWinningStrategy(dimension, players)
                    ));
        }catch(InvalidGameParamsException e){
            System.out.println("Seems like you gave invalid params. Update those.");
            return;
        }

        System.out.println("------ Game is starting ------");

        //while game status is in progress
        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            System.out.println("This is how the board looks like :");

            //print board
            gameController.displayBoard(game);

            //print if undo
            System.out.println("Does anyone want to undo? (y/n)");
            //if yes -> call undo
            String input = scanner.next();

            if(input.equalsIgnoreCase("y")){
                gameController.undo(game);
            }else{
                //move ext player
                gameController.makeMove(game);
            }
        }

        //check status of game
        gameController.printResult(game);
        gameController.displayBoard(game);
        //else -> print draw
    }
}
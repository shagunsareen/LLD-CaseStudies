package TicTacToe.strategies.winningstrategies;

import TicTacToe.models.Board;
import TicTacToe.models.Move;
import TicTacToe.models.Player;
import TicTacToe.models.Symbol;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class OrderOneDiagonalWinningStrategy implements WinningStrategy{

    private Map<Symbol, Integer> leftDiagMap;

    private Map<Symbol, Integer> rightDiagMap;

    public OrderOneDiagonalWinningStrategy(List<Player> players){
        leftDiagMap = new HashMap<>();
        rightDiagMap = new HashMap<>();

        for(Player player : players){
            leftDiagMap.put(player.getSymbol(), 0);
            rightDiagMap.put(player.getSymbol(), 0);
        }
    }
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        //check if that cell is even on diagonal or not
        if(row == col){
            leftDiagMap.put(symbol, leftDiagMap.get(symbol) + 1);
        }

        if(row + col == board.getSize() - 1){
            rightDiagMap.put(symbol, rightDiagMap.get(symbol) + 1);
        }

        // we have ensured state of the board is maintained correctly
        if(row == col){
            //check if count is same as board size
            return leftDiagMap.get(symbol) == board.getSize();
        }

        if(row + col == board.getSize() - 1){
            //check if count is same as board size
            return rightDiagMap.get(symbol) == board.getSize();
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        //check if that cell is even on diagonal or not
        if(row == col){
            leftDiagMap.put(symbol, leftDiagMap.get(symbol) - 1);
        }

        if(row + col == board.getSize() - 1){
            rightDiagMap.put(symbol, rightDiagMap.get(symbol) - 1);
        }
    }
}

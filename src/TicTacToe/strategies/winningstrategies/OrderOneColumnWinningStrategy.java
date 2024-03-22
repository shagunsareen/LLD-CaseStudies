package TicTacToe.strategies.winningstrategies;

import TicTacToe.models.Board;
import TicTacToe.models.Move;
import TicTacToe.models.Player;
import TicTacToe.models.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class OrderOneColumnWinningStrategy implements WinningStrategy{

    List<Map<Symbol, Integer>> colMaps;

    public OrderOneColumnWinningStrategy(int size, List<Player> players){
        colMaps = new ArrayList<>();
        for(int i=0; i<size; ++i){
            colMaps.add(new HashMap<>());
            for(Player player : players){
                colMaps.get(i).put(player.getSymbol(), 0);
            }
        }
    }

    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        colMaps.get(col).put(symbol, 1 + colMaps.get(col).get(symbol));

        if(board.getSize() == colMaps.get(col).get(symbol)){
            return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        colMaps.get(col).put(
                symbol,
                colMaps.get(col).get(symbol) - 1
        );
    }
}

package TicTacToe.strategies.winningstrategies;

import TicTacToe.models.Board;
import TicTacToe.models.Move;
import TicTacToe.models.Player;
import TicTacToe.models.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

public class OrderOneRowWinningStrategy implements WinningStrategy{
    //row maps i.e. 1st item of list will be map of 1st row,
    // 2nd item of list will be map for 2nd row
    // 3rd item of list will be map for 3rd row
    private List<Map<Symbol, Integer>> rowMaps;

    //this will jst construct the initial map for every row
    //{x:0, y:0} |
    //{x:0, y:0} |
    //{x:0, y:0} |
    public OrderOneRowWinningStrategy(int size, List<Player> players){
        rowMaps = new ArrayList<>();
        for(int i=0; i<size; ++i){
            rowMaps.add(new HashMap<>());
            //add symbols for each row
            for(Player player : players){
                rowMaps.get(i).put(player.getSymbol(), 0);
            }
        }
    }
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        rowMaps.get(row).put(symbol, 1 + rowMaps.get(row).get(symbol));

        if(rowMaps.get(row).get(symbol) == board.getSize()){
            return true;
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        rowMaps.get(row).put(
                symbol,
                rowMaps.get(row).get(symbol) - 1
        );
    }
}

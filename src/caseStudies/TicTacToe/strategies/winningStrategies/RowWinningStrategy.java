package caseStudies.TicTacToe.strategies.winningStrategies;

import caseStudies.TicTacToe.models.Move;
import caseStudies.TicTacToe.models.Player;
import caseStudies.TicTacToe.models.Symbol;
import caseStudies.TicTacToe.models.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategies{
    //row maps i.e. 1st item of list will be map of 1st row,
    // 2nd item of list will be map for 2nd row
    // 3rd item of list will be map for 3rd row
    private List<Map<Symbol, Integer>> rowMaps;

    public RowWinningStrategy(int dimension, List<Player> players){
        rowMaps = new ArrayList<>();
        for(int i=0; i<dimension; ++i){
            rowMaps.add(new HashMap<>());
            //add symbols for each row
            for(Player player : players){
                rowMaps.get(i).put(player.getSymbol(), 0);
            }
        }
    }

    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        rowMaps.get(row).put(symbol, 1 + rowMaps.get(row).get(symbol));

        if(rowMaps.get(row).get(symbol) == board.getSize()){
            return true;
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move lastMove) {
        int row = lastMove.getCell().getRow();
        Symbol symbol = lastMove.getPlayer().getSymbol();

        rowMaps.get(row).put(
                symbol,
                rowMaps.get(row).get(symbol) - 1
        );

    }
}

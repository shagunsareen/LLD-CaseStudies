package caseStudies.TicTacToe.strategies.winningStrategies;

import caseStudies.TicTacToe.models.Move;
import caseStudies.TicTacToe.models.Board;
import caseStudies.TicTacToe.models.Player;
import caseStudies.TicTacToe.models.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategies{

    private List<Map<Symbol, Integer>> colMaps;

    public ColumnWinningStrategy(int dimension, List<Player> players){
        colMaps = new ArrayList<>();
        for(int i=0; i<dimension; ++i){
            colMaps.add(new HashMap<>());
            //add symbols for each col
            for(Player player : players){
                colMaps.get(i).put(player.getSymbol(), 0);
            }
        }
    }

    @Override
    public boolean checkWinner(Move move, Board board) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        colMaps.get(col).put(symbol, 1 + colMaps.get(col).get(symbol));

        if(colMaps.get(col).get(symbol) == board.getSize()){
            return true;
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move lastMove) {
        int col = lastMove.getCell().getCol();
        Symbol symbol = lastMove.getPlayer().getSymbol();

        colMaps.get(col).put(
                symbol,
                colMaps.get(col).get(symbol) - 1
        );
    }
}

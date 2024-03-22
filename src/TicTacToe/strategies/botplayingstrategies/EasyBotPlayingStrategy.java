package TicTacToe.strategies.botplayingstrategies;

import TicTacToe.models.Board;
import TicTacToe.models.Cell;
import TicTacToe.models.CellStatus;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Cell makeMove(Board board) {
        for(List<Cell> row : board.getBoard()){
            for(Cell cell : row){
                if(cell.getCellStatus().equals(CellStatus.EMPTY)){
                    return cell;
                }
            }
        }
        return null; //this we are mentioning before Game starts so we will never come to this line.
        //There will always be one empty cell.
    }
}

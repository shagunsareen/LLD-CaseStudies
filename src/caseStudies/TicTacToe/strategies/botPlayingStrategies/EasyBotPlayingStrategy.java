package caseStudies.TicTacToe.strategies.botPlayingStrategies;

import caseStudies.TicTacToe.models.CellStatus;
import caseStudies.TicTacToe.models.Board;
import caseStudies.TicTacToe.models.Cell;

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
        return null;
    }
}

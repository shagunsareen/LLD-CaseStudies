package caseStudies.TicTacToe.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> board;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public Board(int dimension) {
        this.size = dimension;
        this.board = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            board.add(new ArrayList<>()); //rows
            for (int j = 0; j < size; j++) {
                board.get(i).add(new Cell(i, j));
            }
        }
    }

    public void print() {
        for (List<Cell> row : board) {
            System.out.print("|");
            for (Cell cell : row) {
                cell.display();
            }
            System.out.println();
        }
    }
}

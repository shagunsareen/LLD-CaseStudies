package caseStudies.TicTacToe.models;

import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;

    private Scanner scanner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Player(String name, Symbol symbol, PlayerType playerType){
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    public Cell makeMove(Board board) {
        System.out.println("Please tell the row number starting from 0");
        int row = scanner.nextInt();
        System.out.println("Please tell the col number starting from 0");
        int col = scanner.nextInt();

        return new Cell(row, col);
    }
}

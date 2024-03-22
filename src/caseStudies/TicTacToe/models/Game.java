package caseStudies.TicTacToe.models;

import TicTacToe.strategies.winningstrategies.WinningStrategy;
import caseStudies.TicTacToe.exceptions.InvalidGameParamsException;
import caseStudies.TicTacToe.strategies.winningStrategies.WinningStrategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private List<WinningStrategies> winningStrategies;
    private GameStatus gameStatus;
    private int currentMovePlayerIndex;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<WinningStrategies> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategies> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getCurrentMovePlayerIndex() {
        return currentMovePlayerIndex;
    }

    public void setCurrentMovePlayerIndex(int currentMovePlayerIndex) {
        this.currentMovePlayerIndex = currentMovePlayerIndex;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    Game(Builder builder) {
        this.board = new Board(builder.getDimension());
        this.players = builder.getPlayers();
        this.winningStrategies = builder.getWinningStrategies();
        this.moves = new ArrayList<>();
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.currentMovePlayerIndex = 0;
    }

    public void displayBoard() {
        this.board.print();
    }

    public void makeMove() {
        Player currentPlayer = players.get(currentMovePlayerIndex);
        System.out.println("It is "+ currentPlayer.getName() + "'s turn");

        //we are passing board because Bot Player needs Board
        Cell proposedCell = currentPlayer.makeMove(board);
        System.out.println("Move made at row: "+ proposedCell.getRow()
                +" col: "+ proposedCell.getCol() + ".");

        if(!validateMove(proposedCell)){
            System.out.println("Invalid move. Retry");
            return;
        }

        //if move is valid then do the steps
        Cell cellInBoard = board.getBoard().get(proposedCell.getRow()).get(proposedCell.getCol());
        cellInBoard.setCellStatus(CellStatus.FILLED);
        cellInBoard.setPlayer(currentPlayer);

        Move move = new Move(cellInBoard, currentPlayer);
        moves.add(move); //add move to list of moves

        //we check if game is won or is draw on every move
        if(checkGameWon(currentPlayer, move)){
            return;
        }

        if(checkDraw()) return;

        //if no one has won the game yet and it is not draw then update currentplayer index
        currentMovePlayerIndex += 1;
        currentMovePlayerIndex %= players.size(); // to handle cases where index > size of players list
    }

    private boolean checkDraw() {
        if(moves.size() == board.getSize() * board.getSize()){
            gameStatus = GameStatus.DRAW;
            return true;
        }
        return false;
    }

    private boolean checkGameWon(Player currentPlayer, Move move) {
        for(WinningStrategies winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(move, board)){
                gameStatus = GameStatus.ENDED;
                winner = currentPlayer;
                return true;
            }
        }
        return false;
    }

    private boolean validateMove(Cell proposedCell) {
        int row = proposedCell.getRow();
        int col = proposedCell.getCol();

        if(row < 0 || col < 0 || row >= board.getSize() || col >= board.getSize()){
            return false;
        }
        return board.getBoard().get(row).get(col).getCellStatus().equals(CellStatus.EMPTY);
    }

    public void printResult() {
        if(gameStatus.equals(GameStatus.ENDED)){
            System.out.println("Game had ended");
            System.out.println("Winner is: "+ winner.getName());
        }else{
            System.out.println("Game is draw");
        }
    }

    public void undo() {
        //reverse for whatever happened on makingMove
        //decrement count from row map and col map , change the cell status, remove move from move list, decrement current player index
        if(moves.size() == 0){
            System.out.println("No move. Can't undo");
            return;
        }

        Move lastMove = moves.get(moves.size() - 1);

        for (WinningStrategies winningStrategy : winningStrategies){
            winningStrategy.handleUndo(board, lastMove);
        }

        Cell cellInBoard = lastMove.getCell();
        cellInBoard.setCellStatus(CellStatus.EMPTY);
        cellInBoard.setPlayer(null);

        moves.remove(lastMove);

        currentMovePlayerIndex -=1;
        currentMovePlayerIndex += players.size();
        currentMovePlayerIndex %= players.size();
    }

    public static class Builder {
        private List<Player> players;
        private List<WinningStrategies> winningStrategies;
        private int dimension;

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategies> getWinningStrategies() {
            return winningStrategies;
        }

        public Builder setWinningStrategies(List<WinningStrategies> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Game build() throws InvalidGameParamsException {
            if (!validate()) {
                throw new InvalidGameParamsException("Invalid params for game");
            }
            return new Game(this);
        }

        private boolean validate() {

            //players should be equal to board size - 1
            if (this.players.size() < 2) {
                return false;
            }

            if (this.players.size() != this.dimension - 1) {
                return false;
            }

            //bot should be 1
            int botCount = 0;

            for (Player player : this.players) {
                if (PlayerType.BOT.equals(player.getPlayerType())) {
                    botCount += 1;
                }
            }

            if (botCount >= 2) {
                return false;
            }

            //all characters should be unique
            Set<Character> symbols = new HashSet<>();

            for (Player player : players) {
                if (symbols.contains(player.getSymbol().getSymbol())) {
                    return false;
                }
                symbols.add(player.getSymbol().getSymbol());
            }
            return true;
        }
    }
}

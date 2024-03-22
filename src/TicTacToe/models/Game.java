package TicTacToe.models;

import TicTacToe.exceptions.InvalidGameParamsException;
import TicTacToe.strategies.winningstrategies.WinningStrategy;

import java.util.*;

public class Game {

    //all attr must be private
    //have getters and setters
    //all attr are initialised in constructor

    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private int currentMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;
    private GameStatus gameStatus;
    private Player winner;

    public static Builder getBuilder(){
        return new Builder();
    }

    public Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.currentMovePlayerIndex = 0;
        this.winningStrategies = winningStrategies;
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

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

    public int getCurrentMovePlayerIndex() {
        return currentMovePlayerIndex;
    }

    public void setCurrentMovePlayerIndex(int currentMovePlayerIndex) {
        this.currentMovePlayerIndex = currentMovePlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }


    //reverse for whatever happened on makingMove
    public void undo() {
        if(moves.size() == 0){
            System.out.println("No move. Can't undo");
            return;
        }

        Move lastMove = moves.get(moves.size() - 1);

        //this is done before because internally it will use player and in line 109 we are setting player to null
        for (WinningStrategy winningStrategy : winningStrategies){
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

    public void makeMove(){

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

        Move move = new Move(currentPlayer, cellInBoard);
        moves.add(move); //add move to list of moves

        //we check if game is won or is draw on every move
        if(checkGameWon(currentPlayer, move)){
            return;
        }

        if(checkDraw()) return;

        //if no one has won the game yet and it is not draw then update currentplayer index
        currentMovePlayerIndex +=1;
        currentMovePlayerIndex %= players.size(); // to handle cases where index > size of players list
    }

    private boolean validateMove(Cell cell) {
        int row = cell.getRow();
        int col = cell.getCol();

        if(row < 0 || col < 0 || row >= board.getSize() || col >= board.getSize()){
            return false;
        }
        return board.getBoard().get(row).get(col).getCellStatus().equals(CellStatus.EMPTY);
    }

    private boolean checkDraw(){
        if(moves.size() == board.getSize() * board.getSize()){
            gameStatus = GameStatus.DRAW;
            return true;
        }
        return false;
    }

    private boolean checkGameWon(Player currentPlayer, Move move){
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(board, move)){
                gameStatus = GameStatus.ENDED;
                winner = currentPlayer;
                return true;
            }
        }
        return false;
    }

    public void printBoard(){
        this.board.print();
    }

    public void printResult(){
        if(gameStatus.equals(GameStatus.ENDED)){
            System.out.println("Game had ended");
            System.out.println("Winner is: "+ winner.getName());
        }else{
            System.out.println("Game is draw");
        }
    }

    public static class Builder{
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimension;

        private Builder(){}

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Game build() throws InvalidGameParamsException {
            if(!valid()){
                throw new InvalidGameParamsException("Invalid params for game");
            }
            return new Game(dimension, players, winningStrategies);
        }

        private boolean valid() {
            //players should be equal to board size - 1
            if(this.players.size() < 2){
                return false;
            }

            if(this.players.size() != this.dimension - 1){
                return false;
            }

            //bot should be 1
            int botCount = 0;

            for(Player player  :  this.players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount += 1;
                }
            }

            if(botCount >= 2){
                return false;
            }

            //all characters should be unique
            Set<Character> symbols = new HashSet<>();

            for(Player player : players){
                if(symbols.contains(player.getSymbol().getaChar())){
                    return false;
                }
                symbols.add(player.getSymbol().getaChar());
            }

            return true;
        }
    }
}

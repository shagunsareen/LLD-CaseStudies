package caseStudies.TicTacToe.models;

import caseStudies.TicTacToe.strategies.botPlayingStrategies.BotPlayingStrategy;
import caseStudies.TicTacToe.strategies.botPlayingStrategies.BotPlayingStrategyFactory;

public class Bot extends Player {

    private BotDifficultyLevel botDifficultyLevel;

    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyByDifficultyLevel(this.botDifficultyLevel);
    }

    public Cell makeMove(Board board) {
        return botPlayingStrategy.makeMove(board);
    }
}

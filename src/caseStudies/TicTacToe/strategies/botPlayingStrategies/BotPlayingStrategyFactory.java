package caseStudies.TicTacToe.strategies.botPlayingStrategies;

import caseStudies.TicTacToe.models.BotDifficultyLevel;

import static caseStudies.TicTacToe.models.BotDifficultyLevel.EASY;
import static caseStudies.TicTacToe.models.BotDifficultyLevel.MEDIUM;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategyByDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        if (EASY.equals(botDifficultyLevel)) {
            return new EasyBotPlayingStrategy();
        } else if (MEDIUM.equals(botDifficultyLevel)) {
            return new MediumBotPlayingStrategy();
        } else {
            return new HardBotPlayingStrategy();
        }
    }
}

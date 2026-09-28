package ru.nsu.gorlova;

/**
 * Счёт игры: номер раунда и победы участников.
 */
public class Score {

    private int roundNumber;
    private int playerWins;
    private int dealerWins;

    /**
     * Начинает новый раунд.
     */
    public void nextRound() {
        roundNumber++;
    }

    /**
     * Засчитывает победу игроку.
     */
    public void playerWins() {
        playerWins++;
    }

    /**
     * Засчитывает победу дилеру.
     */
    public void dealerWins() {
        dealerWins++;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getPlayerWins() {
        return playerWins;
    }

    public int getDealerWins() {
        return dealerWins;
    }
}
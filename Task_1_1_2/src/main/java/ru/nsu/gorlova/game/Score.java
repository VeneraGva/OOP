package ru.nsu.gorlova.game;

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
     * Записывает результат раунда.
     *
     * @param result результат
     */
    public void record(RoundResult result) {
        switch (result) {
            case PLAYER_WIN -> playerWins++;
            case DEALER_WIN -> dealerWins++;
            case DRAW -> { }
        }
    }

    /**
     * Печатает текущий счёт.
     */
    public void print() {
        System.out.println("Счёт " + playerWins + ":" + dealerWins + ".");
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
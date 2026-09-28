package ru.nsu.gorlova;

/**
 * Счёт игры: номер раунда и победы участников.
 */
public class Score {

    /**
     * Номер текущего раунда.
     */
    private int roundNumber;

    /**
     * Количество побед игрока.
     */
    private int playerWins;

    /**
     * Количество побед дилера.
     */
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

    /**
     * Возвращает номер текущего раунда.
     *
     * @return номер раунда
     */
    public int getRoundNumber() {
        return roundNumber;
    }

    /**
     * Возвращает количество побед игрока.
     *
     * @return победы игрока
     */
    public int getPlayerWins() {
        return playerWins;
    }

    /**
     * Возвращает количество побед дилера.
     *
     * @return победы дилера
     */
    public int getDealerWins() {
        return dealerWins;
    }
}
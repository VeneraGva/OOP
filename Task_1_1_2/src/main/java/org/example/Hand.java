package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Рука игрока или дилера.
 */
public class Hand {

    /**
     * Карты в руке.
     */
    private final List<Card> cards;

    /**
     * Создаёт пустую руку.
     */
    public Hand() {
        this.cards = new ArrayList<>();
    }

    /**
     * Кладёт карту в руку.
     *
     * @param card карта для добавления
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Считает сумму очков в руке.
     *
     * @return сумма очков
     */
    public int getScore() {
        int score = 0;
        int aces = 0;
        for (Card card : cards) {
            score += card.value();
            if (card.isAce()) {
                aces++;
            }
        }
        while (score > 21 && aces > 0) {
            score -= 10;
            aces--;
        }
        return score;
    }

    /**
     * Проверяет, является ли рука блэкджеком.
     *
     * @return {@code true}, если блэкджек
     */
    public boolean isBlackjack() {
        return cards.size() == 2 && getScore() == 21;
    }

    /**
     * Проверяет, превысила ли сумма 21.
     *
     * @return {@code true}, если перебор
     */
    public boolean isBusted() {
        return getScore() > 21;
    }

    /**
     * Возвращает копию списка карт.
     *
     * @return список карт
     */
    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    /**
     * Возвращает строковое представление руки.
     *
     * @return строка с картами и суммой
     */
    @Override
    public String toString() {
        return cards + " > " + getScore();
    }

    /**
     * Очищает руку.
     */
    public void clear() {
        cards.clear();
    }
}
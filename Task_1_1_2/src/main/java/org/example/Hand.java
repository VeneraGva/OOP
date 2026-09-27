package org.example;

import java.util.ArrayList;
import java.util.List;
/**
 * Рука игрока или дилера.
 */
public class Hand {
    private final List<Card> cards;
    /**
     * Создает пустую руку.
     */
    public Hand() {
        this.cards = new ArrayList<>();
    }
    /**
     * Кладет карту в руку.
     */
    public void addCard(Card card) {
        cards.add(card);
    }
    /**
     * Считает сумму очков в руке.
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
     * Блэкджек.
     */
    public boolean isBlackjack() {
        return cards.size() == 2 && getScore() == 21;
    }
    /**
     * Проигрыш.
     */
    public boolean lose() {
        return getScore() > 21;
    }
    /**
     * Возвращает карты на руках.
     */
    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
    /**
     * Возвращает строку с картами на руках.
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
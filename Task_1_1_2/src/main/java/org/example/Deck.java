package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Колода игральных карт.
 */
public class Deck {
    private final List<Card> cards;
    /**
     * Создание, заполнение и перемешивание колоды.
     */
    public Deck() {
        this.cards = new ArrayList<>();
        refill();
        shuffle();
    }
    /**
     * Заполнение колоды.
     */
    private void refill() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }
    /**
     * Перемешивание колоды.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }
    /**
     * Достает последнюю карту из колоды, и если колода пуста, то заводит новую.
     */
    public Card drawCard() {
        if (cards.isEmpty()) {
            refill();
            shuffle();
        }
        return cards.remove(cards.size() - 1);
    }
    /**
     * Размер колоды.
     */
    public int size() {
        return cards.size();
    }
    /**
     * Проверяет пуста ли колода.
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
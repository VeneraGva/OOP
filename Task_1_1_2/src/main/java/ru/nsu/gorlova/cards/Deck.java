package ru.nsu.gorlova.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.nsu.gorlova.cards.card.Card;
import ru.nsu.gorlova.cards.card.Rank;
import ru.nsu.gorlova.cards.card.Suit;

/**
 * Колода игральных карт.
 */
public class Deck {

    /**
     * Карты в колоде.
     */
    private final List<Card> cards;

    /**
     * Создаёт, заполняет и перемешивает колоду.
     */
    public Deck() {
        this.cards = new ArrayList<>();
        refill();
        shuffle();
    }

    /**
     * Заполняет колоду всеми 52 картами.
     */
    private void refill() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Перемешивает колоду.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Достаёт верхнюю карту из колоды.
     *
     * <p>Если колода пуста — заполняет и перемешивает заново.
     *
     * @return верхняя карта
     */
    public Card drawCard() {
        if (cards.isEmpty()) {
            refill();
            shuffle();
        }
        return cards.remove(cards.size() - 1);
    }

    /**
     * Возвращает количество карт в колоде.
     *
     * @return размер колоды
     */
    public int size() {
        return cards.size();
    }

    /**
     * Проверяет, пуста ли колода.
     *
     * @return {@code true}, если колода пуста
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
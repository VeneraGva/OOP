package org.example;
/**
 * Игральная карта.
 */
public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    /**
     * Возвращает масть карты.
     */
    public Suit suit() {
        return suit;
    }
    /**
     * Возвращает ранг карты.
     */
    public Rank rank() {
        return rank;
    }
    /**
     * Возвращает значение ранга карты.
     */
    public int value() {
        return rank.getValue();
    }
    /**
     * Проверяет является ли карта тузом.
     */
    public boolean isAce() {
        return rank.isAce();
    }
    /**
     * Возвращает все название карты.
     */
    @Override
    public String toString() {
        if (rank.usesNounForm()) {
            return rank.getName() + " " + rank.suitForm(suit) + " (" + rank.getValue() + ")";
        }
        return rank.suitForm(suit) + " " + rank.getName() + " (" + rank.getValue() + ")";
    }
}

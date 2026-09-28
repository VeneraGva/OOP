package ru.nsu.gorlova.cards.card;

/**
 * Игральная карта.
 */
public class Card {

    private final Suit suit;

    private final Rank rank;

    /**
     * Создаёт карту с заданной мастью и рангом.
     *
     * @param suit масть карты
     * @param rank ранг карты
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Возвращает масть карты.
     *
     * @return масть
     */
    public Suit suit() {
        return suit;
    }

    /**
     * Возвращает ранг карты.
     *
     * @return ранг
     */
    public Rank rank() {
        return rank;
    }

    /**
     * Возвращает значение ранга карты.
     *
     * @return значение
     */
    public int value() {
        return rank.getValue();
    }

    /**
     * Проверяет, является ли карта тузом.
     *
     * @return {@code true}, если туз
     */
    public boolean isAce() {
        return rank.isAce();
    }

    /**
     * Возвращает название карты.
     *
     * @return название карты
     */
    @Override
    public String toString() {
        if (rank.usesNounForm()) {
            return rank.getName() + " " + rank.suitForm(suit) +
                    " (" + rank.getValue() + ")";
        }
        return rank.suitForm(suit) + " " + rank.getName() +
                " (" + rank.getValue() + ")";
    }
}
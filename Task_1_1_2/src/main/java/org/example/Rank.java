package org.example;

/**
 * Ранг карты.
 */
public enum Rank {

    TWO("Двойка", 2),

    THREE("Тройка", 3),

    FOUR("Четвёрка", 4),

    FIVE("Пятёрка", 5),

    SIX("Шестёрка", 6),

    SEVEN("Семёрка", 7),

    EIGHT("Восьмёрка", 8),

    NINE("Девятка", 9),

    TEN("Десятка", 10),

    JACK("Валет", 10),

    QUEEN("дама", 10),

    KING("король", 10),

    ACE("Туз", 11);

    /**
     * Русское название ранга.
     */
    private final String name;

    /**
     * Значение очков.
     */
    private final int value;

    /**
     * Создаёт ранг.
     *
     * @param name  русское название
     * @param value значение очков
     */
    Rank(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Возвращает название ранга.
     *
     * @return название
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает значение очков.
     *
     * @return значение
     */
    public int getValue() {
        return value;
    }

    /**
     * Проверяет, является ли ранг тузом.
     *
     * @return {@code true}, если туз
     */
    public boolean isAce() {
        return this == ACE;
    }

    /**
     * Проверяет, используется ли существительная форма масти.
     *
     * @return {@code true}, если используется существительная форма
     */
    public boolean usesNounForm() {
        return this != QUEEN && this != KING;
    }

    /**
     * Возвращает форму масти, подходящую по рангу.
     *
     * @param suit масть
     * @return форма масти
     */
    public String suitForm(Suit suit) {
        return switch (this) {
            case QUEEN -> suit.getFeminine();
            case KING -> suit.getMasculine();
            default -> suit.getNoun();
        };
    }
}
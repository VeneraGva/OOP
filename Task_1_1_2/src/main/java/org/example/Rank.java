package org.example;
/**
 * Ранг карты.
 */
public enum Rank {
    TWO    ("Двойка",    2),
    THREE  ("Тройка",    3),
    FOUR   ("Четвёрка",  4),
    FIVE   ("Пятёрка",   5),
    SIX    ("Шестёрка",  6),
    SEVEN  ("Семёрка",   7),
    EIGHT  ("Восьмёрка", 8),
    NINE   ("Девятка",   9),
    TEN    ("Десятка",  10),
    JACK   ("Валет",    10),
    QUEEN  ("дама",     10),
    KING   ("король",   10),
    ACE    ("Туз",      11);

    private final String name;
    private final int value;

    Rank(String name, int value) {
        this.name = name;
        this.value = value;
    }
    /**
     * Возвращает название ранга.
     */
    public String getName() {
        return name;
    }
    /**
     * Возвращает значение.
     */
    public int getValue() {
        return value;
    }
    /**
     * Проверяет туз ли это.
     */
    public boolean isAce() {
        return this == ACE;
    }
    /**
     * Проверяет последовательность слов в название карты.
     */
    public boolean usesNounForm() {
        return this != QUEEN && this != KING;
    }
    /**
     * Возвращает форму масти, подходящую по рангу.
     */
    public String suitForm(Suit suit) {
        return switch (this) {
            case QUEEN -> suit.getFeminine();
            case KING -> suit.getMasculine();
            default -> suit.getNoun();
        };
    }
}

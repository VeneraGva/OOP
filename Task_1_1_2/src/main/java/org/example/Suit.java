package org.example;
/**
 * Масть карты.
 */
public enum Suit {
    SPADES("Пики", "Пиковая", "Пиковый"),
    HEARTS("Червы", "Червовая", "Червовый"),
    DIAMONDS("Бубны", "Бубновая", "Бубновый"),
    CLUBS("Трефы", "Трефовая", "Трефовый");

    private final String noun;
    private final String feminine;
    private final String masculine;

    Suit(String noun, String feminine, String masculine) {
        this.noun = noun;
        this.feminine = feminine;
        this.masculine = masculine;
    }
    /**
     * Возвращает название масти в виде существительного.
     */
    public String getNoun() {
        return noun;
    }
    /**
     * Возвращает название масти в виде прилагательного в женском роде.
     */
    public String getFeminine() {
        return feminine;
    }
    /**
     * Возвращает название масти в виде прилагательного в мужском роде.
     */
    public String getMasculine() {
        return masculine;
    }
}

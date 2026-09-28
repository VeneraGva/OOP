package org.example;

/**
 * Масть карты.
 */
public enum Suit {

    /**
     * Пики.
     */
    SPADES("Пики", "Пиковая", "Пиковый"),

    /**
     * Червы.
     */
    HEARTS("Червы", "Червовая", "Червовый"),

    /**
     * Бубны.
     */
    DIAMONDS("Бубны", "Бубновая", "Бубновый"),

    /**
     * Трефы.
     */
    CLUBS("Трефы", "Трефовая", "Трефовый");

    /**
     * Название масти в виде существительного.
     */
    private final String noun;

    /**
     * Название масти в виде прилагательного в женском роде.
     */
    private final String feminine;

    /**
     * Название масти в виде прилагательного в мужском роде.
     */
    private final String masculine;

    /**
     * Создаёт масть.
     *
     * @param noun      существительное
     * @param feminine  женский род
     * @param masculine мужской род
     */
    Suit(String noun, String feminine, String masculine) {
        this.noun = noun;
        this.feminine = feminine;
        this.masculine = masculine;
    }

    /**
     * Возвращает название масти в виде существительного.
     *
     * @return существительное
     */
    public String getNoun() {
        return noun;
    }

    /**
     * Возвращает название масти в виде прилагательного в женском роде.
     *
     * @return женский род
     */
    public String getFeminine() {
        return feminine;
    }

    /**
     * Возвращает название масти в виде прилагательного в мужском роде.
     *
     * @return мужской род
     */
    public String getMasculine() {
        return masculine;
    }
}
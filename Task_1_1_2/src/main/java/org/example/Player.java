package org.example;

/**
 * Участник игры.
 */
public abstract class Player {

    /**
     * Рука участника.
     */
    private final Hand hand;

    /**
     * Создаёт участника с пустой рукой.
     */
    protected Player() {
        this.hand = new Hand();
    }

    /**
     * Возвращает руку участника.
     *
     * @return рука
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Хочет ли участник взять ещё одну карту.
     *
     * @return {@code true}, если хочет взять карту
     */
    public abstract boolean wantsToHit();
}
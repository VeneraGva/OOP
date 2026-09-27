package org.example;
/**
 * Участник игры.
 */
public abstract class Player {
    private final Hand hand;
    /**
     * Создание руки участника игры.
     */
    public Player() {
        this.hand = new Hand();
    }
    /**
     * Возвращает руку участника.
     */
    public Hand getHand() {
        return hand;
    }
    /**
     * Хочет ли участник взять ещё одну карту.
     */
    public abstract boolean wantsToHit();
}
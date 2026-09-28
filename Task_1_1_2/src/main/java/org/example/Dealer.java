package org.example;

/**
 * Дилер — участник игры, управляемый приложением.
 */
public class Dealer extends Player {

    /**
     * Скрыта ли закрытая карта.
     */
    private boolean hiddenCard;

    /**
     * Создаёт дилера со скрытой закрытой картой.
     */
    public Dealer() {
        this.hiddenCard = true;
    }

    /**
     * Хочет ли дилер взять ещё карту.
     *
     * @return {@code true}, если сумма руки меньше 17
     */
    @Override
    public boolean wantsToHit() {
        return getHand().getScore() < 17;
    }

    /**
     * Открывает закрытую карту.
     */
    public void revealCard() {
        this.hiddenCard = false;
    }

    /**
     * Скрывает закрытую карту.
     */
    public void hideCard() {
        this.hiddenCard = true;
    }

    /**
     * Возвращает закрытую карту (вторую в руке).
     *
     * @return закрытая карта
     */
    public Card getHiddenCard() {
        return getHand().getCards().get(1);
    }

    /**
     * Возвращает строковое представление руки дилера.
     *
     * @return рука дилера для вывода
     */
    public String handToString() {
        if (!hiddenCard) {
            return getHand().toString();
        }
        return "[" + getHand().getCards().get(0) + ", <закрытая карта> ]";
    }
}
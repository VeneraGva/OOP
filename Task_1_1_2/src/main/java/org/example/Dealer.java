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
     * Скрывается карта при создании Дилера.
     */
    public Dealer() {
        this.hiddenCard = true;
    }

    /**
     * Хочет ли дилер взять ещё карту.
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
     * Закрывает закрытую карту.
     */
    public void HiddenCard() {
        this.hiddenCard = true;
    }

    /**
     * Возвращает закрытую карту (вторую в руке).
     */
    public Card getHiddenCard() {
        return getHand().getCards().get(1);
    }

    /**
     * Возвращает строковое представление руки дилера.
     */
    public String handToString() {
        if (!hiddenCard) {
            return getHand().toString();
        }
        return "[" + getHand().getCards().get(0) + ", <закрытая карта> ]";
    }
}
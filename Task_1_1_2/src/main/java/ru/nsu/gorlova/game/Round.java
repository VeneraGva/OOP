package ru.nsu.gorlova.game;

import ru.nsu.gorlova.cards.Deck;
import ru.nsu.gorlova.cards.card.Card;
import ru.nsu.gorlova.player.Dealer;
import ru.nsu.gorlova.player.Player;

/**
 * Один раунд игры в Блэкджек.
 */
public class Round {

    private final Deck deck;
    private final Player player;
    private final Dealer dealer;

    /**
     * Создаёт раунд.
     *
     * @param deck   колода
     * @param player игрок
     * @param dealer дилер
     */
    public Round(Deck deck, Player player, Dealer dealer) {
        this.deck = deck;
        this.player = player;
        this.dealer = dealer;
    }

    /**
     * Играет раунд.
     *
     * @return результат раунда
     */
    public RoundResult play() {
        player.getHand().clear();
        dealer.getHand().clear();
        dealer.hideCard();

        player.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());
        player.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());

        System.out.println("Дилер раздал карты");
        System.out.println("Ваши карты: " + player.getHand());
        System.out.println("Карты дилера: " + dealer.handToString());

        if (player.getHand().isBlackjack()) {
            System.out.println("Блэкджек! Вы выиграли раунд!");
            return RoundResult.PLAYER_WIN;
        }

        System.out.println("Ваш ход");
        System.out.println("-------");

        while (player.wantsToHit()) {
            Card card = deck.drawCard();
            player.getHand().addCard(card);
            System.out.println("Вы открыли карту " + card);
            System.out.println("Ваши карты: " + player.getHand());

            if (player.getHand().isBusted()) {
                System.out.println("Перебор! Вы проиграли раунд.");
                return RoundResult.DEALER_WIN;
            }
        }

        System.out.println("Ход дилера");
        System.out.println("-------");

        dealer.revealCard();
        System.out.println("Дилер открывает закрытую карту "
                + dealer.getHiddenCard());
        System.out.println("Ваши карты: " + player.getHand());
        System.out.println("Карты дилера: " + dealer.getHand());

        while (dealer.wantsToHit()) {
            Card card = deck.drawCard();
            dealer.getHand().addCard(card);
            System.out.println("Дилер открывает карту " + card);
            System.out.println("Ваши карты: " + player.getHand());
            System.out.println("Карты дилера: " + dealer.getHand());
        }

        return determineWinner();
    }

    /**
     * Определяет победителя раунда.
     *
     * @return результат
     */
    private RoundResult determineWinner() {
        int playerScore = player.getHand().getScore();
        int dealerScore = dealer.getHand().getScore();

        if (dealer.getHand().isBusted()) {
            System.out.println("У дилера перебор! Вы выиграли раунд!");
            return RoundResult.PLAYER_WIN;
        } else if (playerScore > dealerScore) {
            System.out.println("Вы выиграли раунд!");
            return RoundResult.PLAYER_WIN;
        } else if (playerScore < dealerScore) {
            System.out.println("Дилер выиграл раунд.");
            return RoundResult.DEALER_WIN;
        } else {
            System.out.println("Ничья!");
            return RoundResult.DRAW;
        }
    }
}
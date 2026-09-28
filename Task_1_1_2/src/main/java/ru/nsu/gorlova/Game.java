package ru.nsu.gorlova;

import ru.nsu.gorlova.cards.Deck;
import ru.nsu.gorlova.cards.card.Card;
import ru.nsu.gorlova.player.Dealer;
import ru.nsu.gorlova.player.HumanPlayer;
import ru.nsu.gorlova.player.Player;

import java.util.Scanner;

/**
 * Игра «Консольный блэкджек».
 */
public class Game {

    private final Deck deck;
    private final Player player;
    private final Dealer dealer;
    private final Scanner scanner;
    private final Score score;

    /**
     * Создаёт новую игру.
     *
     * @param scanner сканер для чтения ввода
     */
    public Game(Scanner scanner) {
        this.deck = new Deck();
        this.player = new HumanPlayer();
        this.dealer = new Dealer();
        this.scanner = scanner;
        this.score = new Score();
    }

    /**
     * Запускает игру.
     */
    public void start() {
        System.out.println("Добро пожаловать в Блэкджек!");

        do {
            score.nextRound();
            playRound();

            System.out.print("Хотите сыграть ещё? Введите 1, если да, и не 1 в противном случае. ");
        } while (!scanner.nextLine().trim().equals("0"));

        System.out.println("Игра окончена. Счёт "
                + score.getPlayerWins() + ":" + score.getDealerWins());
    }

    private void playRound() {
        System.out.println("Раунд " + score.getRoundNumber());

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
            score.playerWins();
            printScore();
            return;
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
                score.dealerWins();
                printScore();
                return;
            }
        }

        System.out.println("Ход дилера");
        System.out.println("-------");

        dealer.revealCard();
        System.out.println("Дилер открывает закрытую карту " + dealer.getHiddenCard());
        System.out.println("Ваши карты: " + player.getHand());
        System.out.println("Карты дилера: " + dealer.getHand());

        while (dealer.wantsToHit()) {
            Card card = deck.drawCard();
            dealer.getHand().addCard(card);
            System.out.println("Дилер открывает карту " + card);
            System.out.println("Ваши карты: " + player.getHand());
            System.out.println("Карты дилера: " + dealer.getHand());
        }

        determineWinner();
    }

    private void determineWinner() {
        int playerScore = player.getHand().getScore();
        int dealerScore = dealer.getHand().getScore();

        if (dealer.getHand().isBusted()) {
            System.out.println("У дилера перебор! Вы выиграли раунд!");
            score.playerWins();
        } else if (playerScore > dealerScore) {
            System.out.println("Вы выиграли раунд!");
            score.playerWins();
        } else if (playerScore < dealerScore) {
            System.out.println("Дилер выиграл раунд.");
            score.dealerWins();
        } else {
            System.out.println("Ничья!");
        }

        printScore();
    }

    private void printScore() {
        System.out.println("Счёт " + score.getPlayerWins() + ":"
                + score.getDealerWins() + ".");
    }
}
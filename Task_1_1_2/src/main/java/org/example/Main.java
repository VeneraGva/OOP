package org.example;

import java.util.Scanner;

/**
 * Консольный Блэкджек.
 */
public class Main {
    private static Deck deck;
    private static Player player;
    private static Dealer dealer;
    private static Scanner scanner;
    /**
     * Количество побед игрока.
     */
    private static int playerWins;
    /**
     * Количество побед дилера.
     */
    private static int dealerWins;
    /**
     * Номер текущего раунда.
     */
    private static int roundNumber;

    /**
     * Запускает игру.
     */
    public static void main(String[] args) {
        deck = new Deck();
        player = new HumanPlayer();
        dealer = new Dealer();
        scanner = new Scanner(System.in);
        playerWins = 0;
        dealerWins = 0;
        roundNumber = 0;

        System.out.println("Добро пожаловать в Блэкджек!");

        while (true) {
            roundNumber++;
            playRound();
            System.out.print("Хотите сыграть ещё? Введите 1, если да, и 0, если нет. ");
            if (scanner.nextInt() == 0) {
                break;
            }
        }

        System.out.println("Игра окончена. Счёт " + playerWins + ":" + dealerWins);
    }

    /**
     * Играет один раунд.
     */
    private static void playRound() {
        System.out.println("Раунд " + roundNumber);

        player.getHand().clear();
        dealer.getHand().clear();
        dealer.HiddenCard();

        player.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());
        player.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());

        System.out.println("Дилер раздал карты");
        System.out.println("Ваши карты: " + player.getHand());
        System.out.println("Карты дилера: " + dealer.handToString());

        if (player.getHand().isBlackjack()) {
            System.out.println("Блэкджек! Вы выиграли раунд!");
            playerWins++;
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

            if (player.getHand().lose()) {
                System.out.println("Перебор! Вы проиграли раунд.");
                dealerWins++;
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

    private static void determineWinner() {
        int playerScore = player.getHand().getScore();
        int dealerScore = dealer.getHand().getScore();

        if (dealer.getHand().lose()) {
            System.out.println("У дилера перебор! Вы выиграли раунд!");
            playerWins++;
        } else if (playerScore > dealerScore) {
            System.out.println("Вы выиграли раунд!");
            playerWins++;
        } else if (playerScore < dealerScore) {
            System.out.println("Дилер выиграл раунд.");
            dealerWins++;
        } else {
            System.out.println("Ничья!");
        }

        printScore();
    }

    private static void printScore() {
        System.out.println("Счёт " + playerWins + ":" + dealerWins + " в вашу пользу.");
    }
}
package ru.nsu.gorlova.game;

import ru.nsu.gorlova.cards.Deck;
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
    private final Score score;
    private final Scanner scanner;

    /**
     * Создаёт игру.
     *
     * @param scanner сканер для ввода
     */
    public Game(Scanner scanner) {
        this.deck = new Deck();
        this.player = new HumanPlayer();
        this.dealer = new Dealer();
        this.score = new Score();
        this.scanner = scanner;
    }

    /**
     * Запускает игру.
     */
    public void start() {
        System.out.println("Добро пожаловать в Блэкджек!");

        do {
            score.nextRound();
            System.out.println("Раунд " + score.getRoundNumber());

            Round round = new Round(deck, player, dealer);
            RoundResult result = round.play();

            score.record(result);
            score.print();

            System.out.print("Хотите сыграть ещё? "
                    + "Введите 1, если да, и 0, если нет. ");
        } while (!scanner.nextLine().trim().equals("0"));

        System.out.println("Игра окончена. Счёт "
                + score.getPlayerWins() + ":" + score.getDealerWins());
    }
}
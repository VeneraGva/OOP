package ru.nsu.gorlova;

import ru.nsu.gorlova.cards.Deck;
import ru.nsu.gorlova.game.Round;
import ru.nsu.gorlova.game.RoundResult;
import ru.nsu.gorlova.player.Dealer;
import ru.nsu.gorlova.player.HumanPlayer;

import java.util.Scanner;

/**
 * Точка входа: играет раунды блэкджека, пока игрок хочет продолжать.
 */
public class Main {

    /**
     * Запускает игру.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        Deck deck = new Deck();
        HumanPlayer player = new HumanPlayer();
        Dealer dealer = new Dealer();

        boolean playAgain = true;
        while (playAgain) {
            Round round = new Round(deck, player, dealer);
            RoundResult result = round.play();
            System.out.println("Результат раунда: " + result);

            System.out.print("Сыграть ещё? (1 — да, иначе — нет): ");
            String answer = new Scanner(System.in).nextLine().trim();
            playAgain = "1".equals(answer);
        }

        System.out.println("Игра окончена.");
    }
}
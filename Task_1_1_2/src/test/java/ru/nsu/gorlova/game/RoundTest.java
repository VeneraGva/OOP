package ru.nsu.gorlova.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.nsu.gorlova.cards.Deck;
import ru.nsu.gorlova.cards.card.Card;
import ru.nsu.gorlova.cards.card.Rank;
import ru.nsu.gorlova.cards.card.Suit;
import ru.nsu.gorlova.player.Dealer;
import ru.nsu.gorlova.player.HumanPlayer;

import java.io.ByteArrayInputStream;


class RoundTest {
    @Test
    @DisplayName("Игрок сразу получает блэкджек — победа игрока")
    void playerBlackjack() {
        Deck deck = new Deck();
        HumanPlayer player = new HumanPlayer();
        Dealer dealer = new Dealer();

        deck.putOnTop(new Card(Suit.DIAMONDS, Rank.FIVE));
        deck.putOnTop(new Card(Suit.CLUBS, Rank.ACE));
        deck.putOnTop(new Card(Suit.HEARTS, Rank.FOUR));
        deck.putOnTop(new Card(Suit.SPADES, Rank.KING));

        Round round = new Round(deck, player, dealer);
        RoundResult result = round.play();

        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertEquals(RoundResult.PLAYER_WIN, result);
    }

    @Test
    @DisplayName("Игрок останавливается — победа дилера")
    void playerStops() {
        String input = "0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Deck deck = new Deck();
        HumanPlayer player = new HumanPlayer();
        Dealer dealer = new Dealer();

        deck.putOnTop(new Card(Suit.SPADES, Rank.NINE));
        deck.putOnTop(new Card(Suit.HEARTS, Rank.FIVE));
        deck.putOnTop(new Card(Suit.SPADES, Rank.TEN));
        deck.putOnTop(new Card(Suit.HEARTS, Rank.TEN));

        Round round = new Round(deck, player, dealer);
        RoundResult result = round.play();

        assertEquals(RoundResult.DEALER_WIN, result);
    }

    @Test
    @DisplayName("Игрок останавливается — ничья")
    void playerStopsDraw() {
        String input = "0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Deck deck = new Deck();
        HumanPlayer player = new HumanPlayer();
        Dealer dealer = new Dealer();

        deck.putOnTop(new Card(Suit.SPADES, Rank.NINE));
        deck.putOnTop(new Card(Suit.CLUBS, Rank.NINE));
        deck.putOnTop(new Card(Suit.SPADES, Rank.TEN));
        deck.putOnTop(new Card(Suit.HEARTS, Rank.TEN));

        Round round = new Round(deck, player, dealer);
        RoundResult result = round.play();

        assertEquals(RoundResult.DRAW, result);
    }

    @Test
    @DisplayName("Игрок берёт карту и перебирает — победа дилера")
    void playerBusted() {
        String input = "1\n0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Deck deck = new Deck();
        HumanPlayer player = new HumanPlayer();
        Dealer dealer = new Dealer();

        deck.putOnTop(new Card(Suit.HEARTS, Rank.SEVEN));
        deck.putOnTop(new Card(Suit.CLUBS, Rank.FIVE));
        deck.putOnTop(new Card(Suit.SPADES, Rank.TEN));
        deck.putOnTop(new Card(Suit.HEARTS, Rank.TEN));
        deck.putOnTop(new Card(Suit.DIAMONDS, Rank.TEN));

        Round round = new Round(deck, player, dealer);
        RoundResult result = round.play();

        assertEquals(RoundResult.DEALER_WIN, result);
    }

    @Test
    @DisplayName("Игрок берёт карту и выигрывает — победа игрока")
    void playerHitsAndWins() {
        String input = "1\n0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Deck deck = new Deck();
        HumanPlayer player = new HumanPlayer();
        Dealer dealer = new Dealer();

        deck.putOnTop(new Card(Suit.HEARTS, Rank.SEVEN));
        deck.putOnTop(new Card(Suit.CLUBS, Rank.NINE));
        deck.putOnTop(new Card(Suit.SPADES, Rank.TEN));
        deck.putOnTop(new Card(Suit.HEARTS, Rank.TWO));
        deck.putOnTop(new Card(Suit.DIAMONDS, Rank.TEN));

        Round round = new Round(deck, player, dealer);
        RoundResult result = round.play();

        assertEquals(RoundResult.DEALER_WIN, result);
    }

    @Test
    @DisplayName("У дилера перебор — победа игрока")
    void dealerBusted() {
        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Deck deck = new Deck();
        HumanPlayer player = new HumanPlayer();
        Dealer dealer = new Dealer();

        deck.putOnTop(new Card(Suit.CLUBS, Rank.TEN));
        deck.putOnTop(new Card(Suit.HEARTS, Rank.SIX));
        deck.putOnTop(new Card(Suit.HEARTS, Rank.FIVE));
        deck.putOnTop(new Card(Suit.SPADES, Rank.TEN));
        deck.putOnTop(new Card(Suit.DIAMONDS, Rank.TEN));

        Round round = new Round(deck, player, dealer);
        RoundResult result = round.play();

        assertEquals(RoundResult.PLAYER_WIN, result);
    }
}
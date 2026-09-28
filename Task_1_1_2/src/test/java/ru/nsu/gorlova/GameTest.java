package ru.nsu.gorlova;

import org.junit.jupiter.api.Test;

import ru.nsu.gorlova.cards.Hand;
import ru.nsu.gorlova.cards.card.Card;
import ru.nsu.gorlova.cards.card.Rank;
import ru.nsu.gorlova.cards.card.Suit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты игровых ситуаций: определение победителя по рукам.
 */
class GameTest {

    @Test
    void testPlayerWinsWithHigherScore() {
        Hand player = new Hand();
        player.addCard(new Card(Suit.SPADES, Rank.KING));
        player.addCard(new Card(Suit.HEARTS, Rank.NINE));

        Hand dealer = new Hand();
        dealer.addCard(new Card(Suit.SPADES, Rank.TEN));
        dealer.addCard(new Card(Suit.HEARTS, Rank.FIVE));

        assertTrue(player.getScore() > dealer.getScore());
        assertFalse(player.isBusted());
        assertFalse(dealer.isBusted());
    }

    @Test
    void testDealerWinsWithHigherScore() {
        Hand player = new Hand();
        player.addCard(new Card(Suit.SPADES, Rank.TEN));
        player.addCard(new Card(Suit.HEARTS, Rank.FIVE));

        Hand dealer = new Hand();
        dealer.addCard(new Card(Suit.SPADES, Rank.KING));
        dealer.addCard(new Card(Suit.HEARTS, Rank.NINE));

        assertTrue(dealer.getScore() > player.getScore());
    }

    @Test
    void testDraw() {
        Hand player = new Hand();
        player.addCard(new Card(Suit.SPADES, Rank.KING));
        player.addCard(new Card(Suit.HEARTS, Rank.NINE));

        Hand dealer = new Hand();
        dealer.addCard(new Card(Suit.CLUBS, Rank.KING));
        dealer.addCard(new Card(Suit.DIAMONDS, Rank.NINE));

        assertEquals(player.getScore(), dealer.getScore());
    }

    @Test
    void testPlayerBusted() {
        Hand player = new Hand();
        player.addCard(new Card(Suit.SPADES, Rank.KING));
        player.addCard(new Card(Suit.HEARTS, Rank.QUEEN));
        player.addCard(new Card(Suit.CLUBS, Rank.TWO));

        assertTrue(player.isBusted());
        assertTrue(player.getScore() > 21);
    }

    @Test
    void testDealerBusted() {
        Hand dealer = new Hand();
        dealer.addCard(new Card(Suit.SPADES, Rank.KING));
        dealer.addCard(new Card(Suit.HEARTS, Rank.QUEEN));
        dealer.addCard(new Card(Suit.CLUBS, Rank.TWO));

        assertTrue(dealer.isBusted());
        assertTrue(dealer.getScore() > 21);
    }

    @Test
    void testBothBusted() {
        Hand player = new Hand();
        player.addCard(new Card(Suit.SPADES, Rank.KING));
        player.addCard(new Card(Suit.HEARTS, Rank.QUEEN));
        player.addCard(new Card(Suit.CLUBS, Rank.TWO));

        Hand dealer = new Hand();
        dealer.addCard(new Card(Suit.SPADES, Rank.KING));
        dealer.addCard(new Card(Suit.HEARTS, Rank.QUEEN));
        dealer.addCard(new Card(Suit.CLUBS, Rank.TWO));

        assertTrue(player.isBusted());
        assertTrue(dealer.isBusted());
    }

    @Test
    void testPlayerBlackjack() {
        Hand player = new Hand();
        player.addCard(new Card(Suit.SPADES, Rank.ACE));
        player.addCard(new Card(Suit.HEARTS, Rank.KING));

        assertTrue(player.isBlackjack());
        assertEquals(21, player.getScore());
    }

    @Test
    void testDealerNotBlackjackWithThreeCards() {
        Hand dealer = new Hand();
        dealer.addCard(new Card(Suit.SPADES, Rank.SEVEN));
        dealer.addCard(new Card(Suit.HEARTS, Rank.SEVEN));
        dealer.addCard(new Card(Suit.CLUBS, Rank.SEVEN));

        assertFalse(dealer.isBlackjack());
        assertEquals(21, dealer.getScore());
    }

    @Test
    void testPlayerWinsDealerBusted() {
        Hand player = new Hand();
        player.addCard(new Card(Suit.SPADES, Rank.TEN));
        player.addCard(new Card(Suit.HEARTS, Rank.FIVE));

        Hand dealer = new Hand();
        dealer.addCard(new Card(Suit.SPADES, Rank.KING));
        dealer.addCard(new Card(Suit.HEARTS, Rank.QUEEN));
        dealer.addCard(new Card(Suit.CLUBS, Rank.TWO));

        assertFalse(player.isBusted());
        assertTrue(dealer.isBusted());
        assertTrue(player.getScore() <= 21);
    }

    @Test
    void testDealerWinsPlayerBusted() {
        Hand player = new Hand();
        player.addCard(new Card(Suit.SPADES, Rank.KING));
        player.addCard(new Card(Suit.HEARTS, Rank.QUEEN));
        player.addCard(new Card(Suit.CLUBS, Rank.TWO));

        Hand dealer = new Hand();
        dealer.addCard(new Card(Suit.SPADES, Rank.TEN));
        dealer.addCard(new Card(Suit.HEARTS, Rank.FIVE));

        assertTrue(player.isBusted());
        assertFalse(dealer.isBusted());
    }
}
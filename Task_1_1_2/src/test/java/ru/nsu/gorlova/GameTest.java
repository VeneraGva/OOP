package ru.nsu.gorlova;

import org.junit.jupiter.api.Test;
import ru.nsu.gorlova.cards.card.Card;
import ru.nsu.gorlova.cards.card.Rank;
import ru.nsu.gorlova.cards.card.Suit;
import ru.nsu.gorlova.cards.Hand;

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
        player.addCard(new Card(Suit.SPADES, Rank.KING));    // 10
        player.addCard(new Card(Suit.HEARTS, Rank.NINE));    // 9 → 19

        Hand dealer = new Hand();
        dealer.addCard(new Card(Suit.SPADES, Rank.TEN));     // 10
        dealer.addCard(new Card(Suit.HEARTS, Rank.FIVE));    // 5 → 15

        assertTrue(player.getScore() > dealer.getScore());
        assertFalse(player.isBusted());
        assertFalse(dealer.isBusted());
    }

    @Test
    void testDealerWinsWithHigherScore() {
        Hand player = new Hand();
        player.addCard(new Card(Suit.SPADES, Rank.TEN));     // 10
        player.addCard(new Card(Suit.HEARTS, Rank.FIVE));    // 5 → 15

        Hand dealer = new Hand();
        dealer.addCard(new Card(Suit.SPADES, Rank.KING));    // 10
        dealer.addCard(new Card(Suit.HEARTS, Rank.NINE));    // 9 → 19

        assertTrue(dealer.getScore() > player.getScore());
    }

    @Test
    void testDraw() {
        Hand player = new Hand();
        player.addCard(new Card(Suit.SPADES, Rank.KING));    // 10
        player.addCard(new Card(Suit.HEARTS, Rank.NINE));    // 9 → 19

        Hand dealer = new Hand();
        dealer.addCard(new Card(Suit.CLUBS, Rank.KING));     // 10
        dealer.addCard(new Card(Suit.DIAMONDS, Rank.NINE));  // 9 → 19

        assertEquals(player.getScore(), dealer.getScore());
    }

    @Test
    void testPlayerBusted() {
        Hand player = new Hand();
        player.addCard(new Card(Suit.SPADES, Rank.KING));    // 10
        player.addCard(new Card(Suit.HEARTS, Rank.QUEEN));   // 10
        player.addCard(new Card(Suit.CLUBS, Rank.TWO));      // 2 → 22

        assertTrue(player.isBusted());
        assertTrue(player.getScore() > 21);
    }

    @Test
    void testDealerBusted() {
        Hand dealer = new Hand();
        dealer.addCard(new Card(Suit.SPADES, Rank.KING));    // 10
        dealer.addCard(new Card(Suit.HEARTS, Rank.QUEEN));   // 10
        dealer.addCard(new Card(Suit.CLUBS, Rank.TWO));      // 2 → 22

        assertTrue(dealer.isBusted());
        assertTrue(dealer.getScore() > 21);
    }

    @Test
    void testBothBusted() {
        Hand player = new Hand();
        player.addCard(new Card(Suit.SPADES, Rank.KING));    // 10
        player.addCard(new Card(Suit.HEARTS, Rank.QUEEN));   // 10
        player.addCard(new Card(Suit.CLUBS, Rank.TWO));      // 2 → 22

        Hand dealer = new Hand();
        dealer.addCard(new Card(Suit.SPADES, Rank.KING));    // 10
        dealer.addCard(new Card(Suit.HEARTS, Rank.QUEEN));   // 10
        dealer.addCard(new Card(Suit.CLUBS, Rank.TWO));      // 2 → 22

        assertTrue(player.isBusted());
        assertTrue(dealer.isBusted());
    }

    @Test
    void testPlayerBlackjack() {
        Hand player = new Hand();
        player.addCard(new Card(Suit.SPADES, Rank.ACE));     // 11
        player.addCard(new Card(Suit.HEARTS, Rank.KING));    // 10 → 21

        assertTrue(player.isBlackjack());
        assertEquals(21, player.getScore());
    }

    @Test
    void testDealerNotBlackjackWithThreeCards() {
        Hand dealer = new Hand();
        dealer.addCard(new Card(Suit.SPADES, Rank.SEVEN));   // 7
        dealer.addCard(new Card(Suit.HEARTS, Rank.SEVEN));   // 7
        dealer.addCard(new Card(Suit.CLUBS, Rank.SEVEN));    // 7 → 21

        assertFalse(dealer.isBlackjack());
        assertEquals(21, dealer.getScore());
    }

    @Test
    void testPlayerWinsDealerBusted() {
        Hand player = new Hand();
        player.addCard(new Card(Suit.SPADES, Rank.TEN));     // 10
        player.addCard(new Card(Suit.HEARTS, Rank.FIVE));    // 5 → 15

        Hand dealer = new Hand();
        dealer.addCard(new Card(Suit.SPADES, Rank.KING));    // 10
        dealer.addCard(new Card(Suit.HEARTS, Rank.QUEEN));   // 10
        dealer.addCard(new Card(Suit.CLUBS, Rank.TWO));      // 2 → 22

        assertFalse(player.isBusted());
        assertTrue(dealer.isBusted());
        assertTrue(player.getScore() <= 21);
    }

    @Test
    void testDealerWinsPlayerBusted() {
        Hand player = new Hand();
        player.addCard(new Card(Suit.SPADES, Rank.KING));    // 10
        player.addCard(new Card(Suit.HEARTS, Rank.QUEEN));   // 10
        player.addCard(new Card(Suit.CLUBS, Rank.TWO));      // 2 → 22

        Hand dealer = new Hand();
        dealer.addCard(new Card(Suit.SPADES, Rank.TEN));     // 10
        dealer.addCard(new Card(Suit.HEARTS, Rank.FIVE));    // 5 → 15

        assertTrue(player.isBusted());
        assertFalse(dealer.isBusted());
    }
}
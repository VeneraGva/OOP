package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Тесты для класса Dealer.
 */
class DealerTest {

    @Test
    void testWantsToHitBelow17() {
        Dealer dealer = new Dealer();
        dealer.getHand().addCard(new Card(Suit.SPADES, Rank.TEN));
        dealer.getHand().addCard(new Card(Suit.HEARTS, Rank.FIVE));
        // 15 < 17
        assertTrue(dealer.wantsToHit());
    }

    @Test
    void testWantsToHitAt17() {
        Dealer dealer = new Dealer();
        dealer.getHand().addCard(new Card(Suit.SPADES, Rank.TEN));
        dealer.getHand().addCard(new Card(Suit.HEARTS, Rank.SEVEN));
        // 17 — не меньше 17
        assertFalse(dealer.wantsToHit());
    }

    @Test
    void testWantsToHitAbove17() {
        Dealer dealer = new Dealer();
        dealer.getHand().addCard(new Card(Suit.SPADES, Rank.TEN));
        dealer.getHand().addCard(new Card(Suit.HEARTS, Rank.NINE));
        // 19
        assertFalse(dealer.wantsToHit());
    }

    @Test
    void testHandToStringHidden() {
        Dealer dealer = new Dealer();
        dealer.getHand().addCard(new Card(Suit.CLUBS, Rank.ACE));
        dealer.getHand().addCard(new Card(Suit.CLUBS, Rank.THREE));
        String result = dealer.handToString();
        assertTrue(result.contains("Туз Трефы (11)"));
        assertTrue(result.contains("<закрытая карта> "));
    }

    @Test
    void testHandToStringRevealed() {
        Dealer dealer = new Dealer();
        dealer.getHand().addCard(new Card(Suit.CLUBS, Rank.ACE));
        dealer.getHand().addCard(new Card(Suit.CLUBS, Rank.THREE));
        dealer.revealCard();
        String result = dealer.handToString();
        assertTrue(result.contains("Туз Трефы (11)"));
        assertTrue(result.contains("Тройка Трефы (3)"));
        assertFalse(result.contains("<закрытая карта"));
    }

    @Test
    void testGetHoleCard() {
        Dealer dealer = new Dealer();
        Card first = new Card(Suit.CLUBS, Rank.ACE);
        Card second = new Card(Suit.CLUBS, Rank.THREE);
        dealer.getHand().addCard(first);
        dealer.getHand().addCard(second);
        assertEquals(second, dealer.getHiddenCard());
    }

    @Test
    void testRevealHoleCard() {
        Dealer dealer = new Dealer();
        dealer.getHand().addCard(new Card(Suit.CLUBS, Rank.ACE));
        dealer.getHand().addCard(new Card(Suit.CLUBS, Rank.THREE));
        dealer.revealCard();
        assertFalse(dealer.handToString().contains("<закрытая карта"));
    }
}
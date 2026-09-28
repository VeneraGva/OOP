package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Тесты для класса Hand.
 */
class HandTest {

    @Test
    void testEmptyHandScore() {
        Hand hand = new Hand();
        assertEquals(0, hand.getScore());
    }

    @Test
    void testSimpleScore() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.QUEEN));    // 10
        hand.addCard(new Card(Suit.HEARTS, Rank.THREE));    // 3
        assertEquals(13, hand.getScore());
    }

    @Test
    void testAceAsEleven() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));      // 11
        hand.addCard(new Card(Suit.HEARTS, Rank.NINE));     // 9
        assertEquals(20, hand.getScore());
    }

    @Test
    void testAceAsOne() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));      // 11 → 1
        hand.addCard(new Card(Suit.HEARTS, Rank.NINE));     // 9
        hand.addCard(new Card(Suit.CLUBS, Rank.FIVE));      // 5
        // 11 + 9 + 5 = 25 → понижаем туз → 1 + 9 + 5 = 15
        assertEquals(15, hand.getScore());
    }

    @Test
    void testTwoAces() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));      // 11
        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));      // 11
        // 11 + 11 = 22 → понижаем один → 11 + 1 = 12
        assertEquals(12, hand.getScore());
    }

    @Test
    void testTwoAcesWithNine() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));      // 11
        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));      // 11 → 1
        hand.addCard(new Card(Suit.CLUBS, Rank.NINE));      // 9
        // 11 + 11 + 9 = 31 → понижаем один → 11 + 1 + 9 = 21
        assertEquals(21, hand.getScore());
    }

    @Test
    void testBlackjack() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.HEARTS, Rank.KING));
        assertTrue(hand.isBlackjack());
    }

    @Test
    void testNotBlackjackThreeCards() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.SEVEN));
        hand.addCard(new Card(Suit.HEARTS, Rank.SEVEN));
        hand.addCard(new Card(Suit.CLUBS, Rank.SEVEN));
        // 21, но не на двух картах
        assertFalse(hand.isBlackjack());
    }

    @Test
    void testBusted() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.KING));
        hand.addCard(new Card(Suit.HEARTS, Rank.QUEEN));
        hand.addCard(new Card(Suit.CLUBS, Rank.TWO));
        // 22 > 21
        assertTrue(hand.lose());
    }

    @Test
    void testNotBusted() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.KING));
        hand.addCard(new Card(Suit.HEARTS, Rank.QUEEN));
        // 20
        assertFalse(hand.lose());
    }

    @Test
    void testToString() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.QUEEN));
        hand.addCard(new Card(Suit.HEARTS, Rank.THREE));
        String result = hand.toString();
        assertTrue(result.contains("Пиковая дама (10)"));
        assertTrue(result.contains("Тройка Червы (3)"));
        assertTrue(result.contains("> 13"));
    }
}
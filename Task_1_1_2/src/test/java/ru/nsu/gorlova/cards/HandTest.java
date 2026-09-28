package ru.nsu.gorlova.cards;

import org.junit.jupiter.api.Test;
import ru.nsu.gorlova.cards.card.Card;
import ru.nsu.gorlova.cards.card.Rank;
import ru.nsu.gorlova.cards.card.Suit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        hand.addCard(new Card(Suit.SPADES, Rank.QUEEN));
        hand.addCard(new Card(Suit.HEARTS, Rank.THREE));
        assertEquals(13, hand.getScore());
    }

    @Test
    void testAceAsEleven() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.HEARTS, Rank.NINE));
        assertEquals(20, hand.getScore());
    }

    @Test
    void testAceAsOne() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.HEARTS, Rank.NINE));
        hand.addCard(new Card(Suit.CLUBS, Rank.FIVE));
        assertEquals(15, hand.getScore());
    }

    @Test
    void testTwoAces() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        assertEquals(12, hand.getScore());
    }

    @Test
    void testTwoAcesWithNine() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        hand.addCard(new Card(Suit.CLUBS, Rank.NINE));
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
        assertFalse(hand.isBlackjack());
    }

    @Test
    void testBusted() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.KING));
        hand.addCard(new Card(Suit.HEARTS, Rank.QUEEN));
        hand.addCard(new Card(Suit.CLUBS, Rank.TWO));
        assertTrue(hand.isBusted());
    }

    @Test
    void testNotBusted() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Rank.KING));
        hand.addCard(new Card(Suit.HEARTS, Rank.QUEEN));
        assertFalse(hand.isBusted());
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
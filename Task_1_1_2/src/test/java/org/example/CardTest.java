package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса Card.
 */
class CardTest {

    @Test
    void testValueQueen() {
        Card card = new Card(Suit.SPADES, Rank.QUEEN);
        assertEquals(10, card.value());
    }

    @Test
    void testValueAce() {
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        assertEquals(11, card.value());
    }

    @Test
    void testValueNumber() {
        Card card = new Card(Suit.HEARTS, Rank.SEVEN);
        assertEquals(7, card.value());
    }

    @Test
    void testIsAceTrue() {
        Card card = new Card(Suit.SPADES, Rank.ACE);
        assertTrue(card.isAce());
    }

    @Test
    void testIsAceFalse() {
        Card card = new Card(Suit.SPADES, Rank.KING);
        assertFalse(card.isAce());
    }

    @Test
    void testToStringQueen() {
        Card card = new Card(Suit.SPADES, Rank.QUEEN);
        assertEquals("Пиковая дама (10)", card.toString());
    }

    @Test
    void testToStringAce() {
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        assertEquals("Туз Трефы (11)", card.toString());
    }

    @Test
    void testToStringNumber() {
        Card card = new Card(Suit.HEARTS, Rank.THREE);
        assertEquals("Тройка Червы (3)", card.toString());
    }

    @Test
    void testToStringKing() {
        Card card = new Card(Suit.DIAMONDS, Rank.KING);
        assertEquals("Бубновый король (10)", card.toString());
    }
}
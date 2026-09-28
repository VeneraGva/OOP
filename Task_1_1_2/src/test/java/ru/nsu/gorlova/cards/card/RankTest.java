package ru.nsu.gorlova.cards.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для класса Rank.
 */
class RankTest {

    @Test
    void testIsAceTrue() {
        assertTrue(Rank.ACE.isAce());
    }

    @Test
    void testIsAceFalse() {
        assertFalse(Rank.KING.isAce());
        assertFalse(Rank.TWO.isAce());
    }

    @Test
    void testValues() {
        assertEquals(2, Rank.TWO.getValue());
        assertEquals(10, Rank.TEN.getValue());
        assertEquals(10, Rank.JACK.getValue());
        assertEquals(10, Rank.QUEEN.getValue());
        assertEquals(10, Rank.KING.getValue());
        assertEquals(11, Rank.ACE.getValue());
    }

    @Test
    void testSuitFormQueen() {
        assertEquals("Пиковая", Rank.QUEEN.suitForm(Suit.SPADES));
    }

    @Test
    void testSuitFormKing() {
        assertEquals("Бубновый", Rank.KING.suitForm(Suit.DIAMONDS));
    }

    @Test
    void testSuitFormNumber() {
        assertEquals("Червы", Rank.THREE.suitForm(Suit.HEARTS));
    }

    @Test
    void testSuitFormAce() {
        assertEquals("Трефы", Rank.ACE.suitForm(Suit.CLUBS));
    }
}
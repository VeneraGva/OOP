package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Тесты для класса Suit.
 */
class SuitTest {

    @Test
    void testNoun() {
        assertEquals("Пики", Suit.SPADES.getNoun());
        assertEquals("Червы", Suit.HEARTS.getNoun());
        assertEquals("Бубны", Suit.DIAMONDS.getNoun());
        assertEquals("Трефы", Suit.CLUBS.getNoun());
    }

    @Test
    void testFeminine() {
        assertEquals("Пиковая", Suit.SPADES.getFeminine());
        assertEquals("Червовая", Suit.HEARTS.getFeminine());
    }

    @Test
    void testMasculine() {
        assertEquals("Пиковый", Suit.SPADES.getMasculine());
        assertEquals("Бубновый", Suit.DIAMONDS.getMasculine());
    }
}
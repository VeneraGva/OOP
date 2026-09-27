package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса Deck.
 */
class DeckTest {

    @Test
    void testInitialSize() {
        Deck deck = new Deck();
        assertEquals(52, deck.size());
    }

    @Test
    void testDrawReducesSize() {
        Deck deck = new Deck();
        deck.drawCard();
        assertEquals(51, deck.size());
    }

    @Test
    void testDrawAllCards() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            assertNotNull(deck.drawCard());
        }
        assertTrue(deck.isEmpty());
    }

    @Test
    void testRefillAfterEmpty() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }
        Card card = deck.drawCard();
        assertNotNull(card);
        assertEquals(51, deck.size());
    }

    @Test
    void testNotEmptyInitially() {
        Deck deck = new Deck();
        assertFalse(deck.isEmpty());
    }
}
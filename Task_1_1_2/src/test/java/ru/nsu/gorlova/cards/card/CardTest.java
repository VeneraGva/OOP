package ru.nsu.gorlova.cards.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Тесты для класса Card.
 */
class CardTest {

    /**
     * Проверяет значение дамы.
     */
    @Test
    void testValueQueen() {
        Card card = new Card(Suit.SPADES, Rank.QUEEN);
        assertEquals(10, card.value());
    }

    /**
     * Проверяет значение туза.
     */
    @Test
    void testValueAce() {
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        assertEquals(11, card.value());
    }

    /**
     * Проверяет значение числовой карты.
     */
    @Test
    void testValueNumber() {
        Card card = new Card(Suit.HEARTS, Rank.SEVEN);
        assertEquals(7, card.value());
    }

    /**
     * Проверяет, что туз — это туз.
     */
    @Test
    void testIsAceTrue() {
        Card card = new Card(Suit.SPADES, Rank.ACE);
        assertTrue(card.isAce());
    }

    /**
     * Проверяет, что не-туз — это не туз.
     */
    @Test
    void testIsAceFalse() {
        Card card = new Card(Suit.SPADES, Rank.KING);
        assertFalse(card.isAce());
    }

    /**
     * Проверяет toString для дамы.
     */
    @Test
    void testToStringQueen() {
        Card card = new Card(Suit.SPADES, Rank.QUEEN);
        assertEquals("Пиковая дама (10)", card.toString());
    }

    /**
     * Проверяет toString для туза.
     */
    @Test
    void testToStringAce() {
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        assertEquals("Туз Трефы (11)", card.toString());
    }

    /**
     * Проверяет toString для числовой карты.
     */
    @Test
    void testToStringNumber() {
        Card card = new Card(Suit.HEARTS, Rank.THREE);
        assertEquals("Тройка Червы (3)", card.toString());
    }

    /**
     * Проверяет toString для короля.
     */
    @Test
    void testToStringKing() {
        Card card = new Card(Suit.DIAMONDS, Rank.KING);
        assertEquals("Бубновый король (10)", card.toString());
    }

    /**
     * Проверяет getter масти.
     */
    @Test
    void testSuitGetter() {
        Card card = new Card(Suit.HEARTS, Rank.TEN);
        assertEquals(Suit.HEARTS, card.suit());
    }

    /**
     * Проверяет getter ранга.
     */
    @Test
    void testRankGetter() {
        Card card = new Card(Suit.HEARTS, Rank.TEN);
        assertEquals(Rank.TEN, card.rank());
    }

    /**
     * Проверяет toString для десятки.
     */
    @Test
    void testToStringTen() {
        Card card = new Card(Suit.CLUBS, Rank.TEN);
        assertEquals("Десятка Трефы (10)", card.toString());
    }

    /**
     * Проверяет toString для валета.
     */
    @Test
    void testToStringJack() {
        Card card = new Card(Suit.HEARTS, Rank.JACK);
        assertEquals("Валет Червы (10)", card.toString());
    }

    /**
     * Проверяет toString для двойки.
     */
    @Test
    void testToStringTwo() {
        Card card = new Card(Suit.DIAMONDS, Rank.TWO);
        assertEquals("Двойка Бубны (2)", card.toString());
    }
}
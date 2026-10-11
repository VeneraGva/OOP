package ru.nsu.gorlova.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Тесты для класса StringParser.
 */
class StringParserTest {

    /**
     * Проверяет парсинг двух переменных.
     */
    @Test
    void testParseTwoVariables() {
        Map<String, Integer> values = StringParser.parse("x = 10; y = 13");
        assertEquals(2, values.size());
        assertEquals(10, values.get("x"));
        assertEquals(13, values.get("y"));
    }

    /**
     * Проверяет парсинг одной переменной.
     */
    @Test
    void testParseOneVariable() {
        Map<String, Integer> values = StringParser.parse("x = 5");
        assertEquals(1, values.size());
        assertEquals(5, values.get("x"));
    }

    /**
     * Проверяет парсинг без пробелов.
     */
    @Test
    void testParseWithoutSpaces() {
        Map<String, Integer> values = StringParser.parse("x=10;y=13");
        assertEquals(2, values.size());
        assertEquals(10, values.get("x"));
        assertEquals(13, values.get("y"));
    }

    /**
     * Проверяет парсинг с лишними пробелами.
     */
    @Test
    void testParseWithExtraSpaces() {
        Map<String, Integer> values = StringParser.parse("  x   =   10  ;  y   =   13  ");
        assertEquals(2, values.size());
        assertEquals(10, values.get("x"));
        assertEquals(13, values.get("y"));
    }

    /**
     * Проверяет парсинг трёх переменных.
     */
    @Test
    void testParseThreeVariables() {
        Map<String, Integer> values = StringParser.parse("a = 1; b = 2; c = 3");
        assertEquals(3, values.size());
        assertEquals(1, values.get("a"));
        assertEquals(2, values.get("b"));
        assertEquals(3, values.get("c"));
    }

    /**
     * Проверяет парсинг многобуквенных имён.
     */
    @Test
    void testParseLongNames() {
        Map<String, Integer> values = StringParser.parse("alpha = 1; beta = 2");
        assertEquals(2, values.size());
        assertEquals(1, values.get("alpha"));
        assertEquals(2, values.get("beta"));
    }

    /**
     * Проверяет парсинг отрицательных значений.
     */
    @Test
    void testParseNegativeValues() {
        Map<String, Integer> values = StringParser.parse("x = -10; y = -13");
        assertEquals(2, values.size());
        assertEquals(-10, values.get("x"));
        assertEquals(-13, values.get("y"));
    }

    /**
     * Проверяет парсинг нулевых значений.
     */
    @Test
    void testParseZeroValues() {
        Map<String, Integer> values = StringParser.parse("x = 0; y = 0");
        assertEquals(2, values.size());
        assertEquals(0, values.get("x"));
        assertEquals(0, values.get("y"));
    }

    /**
     * Проверяет парсинг null.
     */
    @Test
    void testParseNull() {
        Map<String, Integer> values = StringParser.parse(null);
        assertTrue(values.isEmpty());
    }

    /**
     * Проверяет парсинг пустой строки.
     */
    @Test
    void testParseEmpty() {
        Map<String, Integer> values = StringParser.parse("");
        assertTrue(values.isEmpty());
    }

    /**
     * Проверяет парсинг строки из пробелов.
     */
    @Test
    void testParseSpacesOnly() {
        Map<String, Integer> values = StringParser.parse("    ");
        assertTrue(values.isEmpty());
    }

    /**
     * Проверяет парсинг строки без знака "=".
     */
    @Test
    void testParseNoEquals() {
        Map<String, Integer> values = StringParser.parse("garbage");
        assertTrue(values.isEmpty());
    }

    /**
     * Проверяет, что некорректные пары пропускаются.
     */
    @Test
    void testParseMixedGarbage() {
        Map<String, Integer> values = StringParser.parse("x = 10; garbage; y = 13");
        assertEquals(2, values.size());
        assertEquals(10, values.get("x"));
        assertEquals(13, values.get("y"));
    }

    /**
     * Проверяет, что пара с несколькими "=" пропускается.
     */
    @Test
    void testParseMultipleEquals() {
        Map<String, Integer> values = StringParser.parse("x = 10 = 20; y = 13");
        assertEquals(1, values.size());
        assertEquals(13, values.get("y"));
    }

    /**
     * Проверяет ошибку при нечисловом значении.
     */
    @Test
    void testParseNonNumericValue() {
        assertThrows(IllegalArgumentException.class,
                () -> StringParser.parse("x = abc"));
    }

    /**
     * Проверяет перезапись значения при повторении имени.
     */
    @Test
    void testParseDuplicateName() {
        Map<String, Integer> values = StringParser.parse("x = 10; x = 20");
        assertEquals(1, values.size());
        assertEquals(20, values.get("x"));
    }
}
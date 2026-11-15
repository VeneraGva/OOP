package ru.nsu.gorlova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;

class SubstringFindTest {

    private final SubstringFind finder = new SubstringFind();

    @Test
    void testEmptyFile() {
        List<Integer> result = finder.find("src/test/resources/empty.txt", "test");
        assertTrue(result.isEmpty());
    }

    @Test
    void testMultipleMatches() {
        List<Integer> result = finder.find("src/test/resources/english.txt", "hello");
        assertEquals(List.of(0, 12), result);
    }

    @Test
    void testOverlappingMatches() {
        List<Integer> result = finder.find("src/test/resources/overlap.txt", "аа");
        assertEquals(List.of(0, 1, 2), result);
    }

    @Test
    void testCyrillicUtf8() {
        List<Integer> result = finder.find("src/test/resources/cyrillic.txt", "привет");
        assertEquals(List.of(0, 11), result);
    }
}
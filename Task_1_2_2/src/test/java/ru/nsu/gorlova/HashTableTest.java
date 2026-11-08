package ru.nsu.gorlova;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class HashTableTest {

    private HashTable<String, Number> table;

    @BeforeEach
    void setUp() {
        table = new HashTable<>();
    }

    @Test
    void emptyTableHasNoElements() {
        assertNull(table.get("one"));
        assertFalse(table.containsKey("one"));
        assertEquals("{}", table.toString());
    }

    @Test
    void putAndGetSingleValue() {
        table.put("one", 1);
        assertEquals(1, table.get("one"));
    }

    @Test
    void putReplacesExistingValue() {
        table.put("one", 1);
        table.put("one", 2);
        assertEquals(2, table.get("one"));
    }

    @Test
    void getMissingKeyReturnsNull() {
        table.put("one", 1);
        assertNull(table.get("two"));
    }

    @Test
    void putNullKey() {
        table.put(null, 1);
        assertEquals(1, table.get(null));
        assertTrue(table.containsKey(null));
    }

    @Test
    void putNullValue() {
        table.put("one", null);
        assertNull(table.get("one"));
        assertTrue(table.containsKey("one"));
    }

    @Test
    void updateExistingKey() {
        table.put("one", 1);
        table.update("one", 1.0);
        assertEquals(1.0, table.get("one"));
    }

    @Test
    void updateMissingKeyDoesNothing() {
        table.update("one", 1.0);
        assertNull(table.get("one"));
        assertFalse(table.containsKey("one"));
    }

    @Test
    void containsKeyReturnsTrueForExisting() {
        table.put("one", 1);
        assertTrue(table.containsKey("one"));
    }

    @Test
    void containsKeyReturnsFalseForMissing() {
        assertFalse(table.containsKey("one"));
    }

    @Test
    void removeExistingKey() {
        table.put("one", 1);
        table.remove("one");
        assertNull(table.get("one"));
        assertFalse(table.containsKey("one"));
    }

    @Test
    void removeMissingKeyDoesNothing() {
        table.put("one", 1);
        table.remove("two");
        assertEquals(1, table.get("one"));
    }

    @Test
    void removeFromCollisionChain() {
        table.put("Aa", 1);
        table.put("BB", 2);
        table.put("Aa2", 3);
        table.remove("BB");
        assertEquals(1, table.get("Aa"));
        assertNull(table.get("BB"));
        assertEquals(3, table.get("Aa2"));
    }

    @Test
    void resizeKeepsAllElements() {
        for (int i = 0; i < 1000; i++) {
            table.put("key" + i, i);
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals(i, table.get("key" + i));
        }
    }

    @Test
    void collisionsAreResolved() {
        table.put("Aa", 1);
        table.put("BB", 2);
        assertEquals(1, table.get("Aa"));
        assertEquals(2, table.get("BB"));
    }

    @Test
    void equalsReturnsTrueForSameContent() {
        HashTable<String, Number> a = new HashTable<>();
        a.put("one", 1);
        a.put("two", 2);

        HashTable<String, Number> b = new HashTable<>();
        b.put("two", 2);
        b.put("one", 1);

        assertTrue(a.equals(b));
    }

    @Test
    void equalsReturnsFalseForDifferentValues() {
        HashTable<String, Number> a = new HashTable<>();
        a.put("one", 1);

        HashTable<String, Number> b = new HashTable<>();
        b.put("one", 2);

        assertFalse(a.equals(b));
    }

    @Test
    void equalsReturnsFalseForDifferentSizes() {
        HashTable<String, Number> a = new HashTable<>();
        a.put("one", 1);

        HashTable<String, Number> b = new HashTable<>();
        b.put("one", 1);
        b.put("two", 2);

        assertFalse(a.equals(b));
    }

    @Test
    void equalsReturnsFalseForDifferentKeys() {
        HashTable<String, Number> a = new HashTable<>();
        a.put("one", 1);

        HashTable<String, Number> b = new HashTable<>();
        b.put("two", 1);

        assertFalse(a.equals(b));
    }

    @Test
    void equalsSelf() {
        table.put("one", 1);
        assertTrue(table.equals(table));
    }

    @Test
    void equalsWithNullValue() {
        HashTable<String, Number> a = new HashTable<>();
        a.put("x", null);

        HashTable<String, Number> b = new HashTable<>();
        b.put("x", null);

        assertTrue(a.equals(b));
    }

    @Test
    void toStringEmpty() {
        assertEquals("{}", table.toString());
    }

    @Test
    void toStringSingle() {
        table.put("one", 1);
        assertEquals("{one=1}", table.toString());
    }

    @Test
    void toStringMultiple() {
        table.put("one", 1);
        table.put("two", 2);
        String s = table.toString();
        assertTrue(s.startsWith("{") && s.endsWith("}"));
        assertTrue(s.contains("one=1"));
        assertTrue(s.contains("two=2"));
    }

    @Test
    void iteratorOverEmptyTable() {
        Iterator<HashTable.Entry<String, Number>> it = table.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    void iteratorReturnsAllElements() {
        table.put("one", 1);
        table.put("two", 2);
        table.put("three", 3);

        int count = 0;
        for (HashTable.Entry<String, Number> e : table) {
            assertNotNull(e.getKey());
            assertNotNull(e.getValue());
            count++;
        }
        assertEquals(3, count);
    }

    @Test
    void iteratorThrowsNoSuchElement() {
        table.put("one", 1);
        Iterator<HashTable.Entry<String, Number>> it = table.iterator();
        it.next();
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void iteratorThrowsCMEOnPutDuringIteration() {
        table.put("one", 1);
        Iterator<HashTable.Entry<String, Number>> it = table.iterator();
        it.next();
        table.put("two", 2);
        assertThrows(ConcurrentModificationException.class, it::next);
    }

    @Test
    void iteratorThrowsCMEOnRemoveDuringIteration() {
        table.put("one", 1);
        table.put("two", 2);
        Iterator<HashTable.Entry<String, Number>> it = table.iterator();
        it.next();
        table.remove("one");
        assertThrows(ConcurrentModificationException.class, it::next);
    }

    @Test
    void iteratorDoesNotThrowCMEOnUpdateValue() {
        table.put("one", 1);
        Iterator<HashTable.Entry<String, Number>> it = table.iterator();
        it.next();
        table.update("one", 2);
        assertDoesNotThrow(it::hasNext);
    }

    @Test
    void iteratorRemoveDeletesCurrent() {
        table.put("a", 1);
        table.put("b", 2);
        table.put("c", 3);

        Iterator<HashTable.Entry<String, Number>> it = table.iterator();
        while (it.hasNext()) {
            if (it.next().getKey().equals("b")) {
                it.remove();
            }
        }

        assertNull(table.get("b"));
        assertFalse(table.containsKey("b"));
        assertEquals(1, table.get("a"));
        assertEquals(3, table.get("c"));
    }

    @Test
    void iteratorRemoveWithoutNextThrowsIllegalState() {
        table.put("one", 1);
        Iterator<HashTable.Entry<String, Number>> it = table.iterator();
        assertThrows(IllegalStateException.class, it::remove);
    }

    @Test
    void iteratorDoubleRemoveThrowsIllegalState() {
        table.put("one", 1);
        Iterator<HashTable.Entry<String, Number>> it = table.iterator();
        it.next();
        it.remove();
        assertThrows(IllegalStateException.class, it::remove);
    }

    @Test
    void iteratorRemoveDoesNotThrowCME() {
        table.put("a", 1);
        table.put("b", 2);
        Iterator<HashTable.Entry<String, Number>> it = table.iterator();
        it.next();
        assertDoesNotThrow(it::remove);
        assertDoesNotThrow(it::hasNext);
    }

    @Test
    void taskExample() {
        HashTable<String, Number> t = new HashTable<>();
        t.put("one", 1);
        t.update("one", 1.0);
        assertEquals(1.0, t.get("one"));
    }
}
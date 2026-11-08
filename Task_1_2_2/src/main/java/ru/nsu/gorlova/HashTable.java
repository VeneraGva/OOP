package ru.nsu.gorlova;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Хеш таблица.
 *
 * @param <K> тип ключа.
 * @param <V> тип значения.
 */
public class HashTable<K, V> implements Iterable<HashTable.Entry<K,V>>{

    /**
     * Размер хэш таблицы.
     */
    private static final int INITIAL_CAPACITY = 16;

    /**
     * Коэффициент загруженности.
     */
    private static final double LOAD_FACTOR = 0.75;

    /**
     * Функция, которая возвращает итератор хеш таблицы, перебирающий все элементы хеш таблицы.
     *
     * @return итератор хеш таблицы.
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashTableIterator();
    }

    /**
     * Элемент хеш таблицы.
     *
     * @param <K> ключ.
     * @param <V> значение.
     */
    public static class Entry<K, V> {

        /**
         * Поле ключа.
         */
        private final K key;

        /**
         * Поле значения.
         */
        private V value;

        /**
         * Поле следующего элемента в коллизии.
         */
        private Entry<K, V> next;

        /**
         * Конструктор элемента.
         *
         * @param key ключ.
         * @param value значение.
         */
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        /**
         * Функция получения ключа.
         *
         * @return ключ.
         */
        public K getKey() {
            return key;
        }

        /**
         * Функция получения значения.
         *
         * @return значение.
         */
        public V getValue() {
            return value;
        }

        /**
         * Функция получение следующего элемента с таким же индексом.
         *
         * @return следующий элемент.
         */
        public Entry<K, V> getNext() {
            return next;
        }

        /**
         * Функция изменения значения.
         *
         * @param update новое значение.
         */
        public void setValue(V update) {
            this.value = update;
        }

        /**
         * Функция для присваивания следующего элемента с таким же индексом.
         *
         * @param node новый следующий элемент.
         */
        public void setNext(Entry<K, V> node) {
            this.next = node;
        }
    }

    /**
     * Поле хеш таблицы.
     */
    private ArrayList<Entry<K, V>> table;

    /**
     * Поле размера хеш таблицы.
     */
    private int size;

    /**
     * Поле счетчика изменений.
     */
    private int modificationCounter;

    /**
     * Поле порога, после которого таблица расширяется.
     */
    private int thresholdResize;

    /**
     * Конструктор хеш таблицы.
     */
    public HashTable() {
        table = new ArrayList<>(INITIAL_CAPACITY);
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            table.add(null);
        }
        size = 0;
        modificationCounter = 0;
        thresholdResize = (int) (INITIAL_CAPACITY * LOAD_FACTOR);
    }

    /**
     * Функция определяющая хеш код для ключа.
     *
     * @param key ключ.
     * @return хеш код.
     */
    private int hashCode(K key) {
        if(key == null) {
            return 0;
        }
        int hashCode = key.hashCode();
        hashCode ^= hashCode >>> 16;
        hashCode *= 0x85ebca6b;
        hashCode ^= hashCode >>> 13;
        hashCode *= 0xc2b2ae35;
        hashCode ^= hashCode >>> 16;
        return hashCode;
    }

    /**
     * Функция определяющая индекс в таблицы по хеш коду.
     *
     * @param hash хеш код ключа.
     * @param capacity размер таблицы.
     * @return индекс в хеш таблице.
     */
    private int index(int hash, int capacity) {
        return hash & (capacity - 1);
    }

    /**
     * Функция расширяющая хеш таблицу в два раза.
     */
    private void resize() {
        ArrayList<Entry<K, V>> oldTable = table;
        int newCapacity = oldTable.size() * 2;
        ArrayList<Entry<K, V>> newTable = new ArrayList<>(newCapacity);
        for (int i = 0; i < newCapacity; i++) {
            newTable.add(null);
        }
        for (Entry<K, V> head : oldTable) {
            for (Entry<K, V> i = head; i != null; ) {
                Entry<K, V> next = i.getNext();
                int index = index(hashCode(i.getKey()), newCapacity);
                i.setNext(newTable.get(index));
                newTable.set(index, i);
                i = next;
            }
        }
        table = newTable;
        thresholdResize = (int) (newCapacity * LOAD_FACTOR);
    }

    /**
     * Функция, которая кладет ключи и значение в хеш таблицу.
     *
     * @param key ключ.
     * @param value значение.
     */
    public void put(K key, V value) {
        int index = index(hashCode(key), table.size());
        for (Entry<K, V> i = table.get(index); i != null; i = i.getNext()) {
            if (Objects.equals(i.getKey(), key)) {
                i.setValue(value);
                return;
            }
        }
        Entry<K, V> entry = new Entry<>(key, value);
        entry.setNext(table.get(index));
        table.set(index, entry);
        size++;
        modificationCounter++;
        if (size >= thresholdResize) {
            resize();
        }
    }

    /**
     * Функция получающая значение по ключу в хеш таблице.
     *
     * @param key ключ.
     * @return значение.
     */
    public V get(K key) {
        int index = index(hashCode(key), table.size());
        for (Entry<K, V> i = table.get(index); i != null; i = i.getNext()) {
            if (Objects.equals(i.getKey(), key)) {
                return i.getValue();
            }
        }
        return null;
    }

    /**
     * Обновление значения по ключу в хеш таблице.
     *
     * @param key ключ.
     * @param value значение.
     */
    public void update(K key, V value) {
        int index = index(hashCode(key), table.size());
        for (Entry<K, V> i = table.get(index); i != null; i = i.getNext()) {
            if (Objects.equals(i.getKey(), key)) {
                i.setValue(value);
                return;
            }
        }
    }

    /**
     * Функция проверяющая существование ключа в хеш таблице.
     *
     * @param key ключ.
     * @return если ключ существует, то true.
     */
    public boolean containsKey(K key) {
        int index = index(hashCode(key), table.size());
        for (Entry<K, V> i = table.get(index); i != null; i = i.getNext()) {
            if (Objects.equals(i.getKey(), key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Функция удаления ключа и значения по ключу в хеш таблице.
     *
     * @param key удаляемы ключ.
     */
    public void remove(K key) {
        int index = index(hashCode(key), table.size());
        Entry<K, V> previous = null;
        for (Entry<K, V> i = table.get(index); i != null; i = i.getNext()) {
            if (Objects.equals(i.getKey(), key)) {
                if (previous == null) {
                    table.set(index, i.getNext());
                } else {
                    previous.setNext(i.getNext());
                }
                size--;
                modificationCounter++;
                return;
            }
            previous = i;
        }
    }

    /**
     * Функция сравнивающая хеш таблицы.
     *
     * @param object объект, с которым сравнивают.
     * @return если хеш таблицы равны, то true.
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof HashTable)) {
            return false;
        }
        HashTable<K, V> other = (HashTable<K, V>) object;
        if (size != other.size) return false;
        for (Entry<K, V> i : this) {
            if (!other.containsKey(i.getKey())) {
                return false;
            }
            if (!Objects.equals(i.getValue(), other.get(i.getKey()))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Функция, которая выводит хеш таблицу в одну строку.
     *
     * @return строка.
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{");
        boolean first = true;
        for (Entry<K, V> i : this) {
            if (!first) string.append(", ");
            string.append(i.getKey()).append("=").append(i.getValue());
            first = false;
        }
        return string.append("}").toString();
    }

    /**
     * Итератор по элементам хеш таблицы.
     */
    private class HashTableIterator implements Iterator<Entry<K, V>> {

        /**
         * Поле ожидающего счетчика изменений.
         */
        private int expectedModificationCounter = modificationCounter;

        /**
         * Поле текущего индекса в хеш таблице.
         */
        private int currentIndex = -1;

        /**
         * Следующий элемент.
         */
        private Entry<K, V> nextEntry = null;

        /**
         * Предыдущий элемент.
         */
        private Entry<K, V> lastReturned = null;

        /**
         * Конструктор итератора.
         */
        HashTableIterator() {
            advance();
        }

        /**
         * Функция передвигающая индекс.
         */
        private void advance() {
            if (nextEntry != null && nextEntry.getNext() != null) {
                nextEntry = nextEntry.getNext();
                return;
            }
            nextEntry = null;
            while (currentIndex + 1 < table.size()) {
                currentIndex++;
                Entry<K, V> head = table.get(currentIndex);
                if (head != null) {
                    nextEntry = head;
                    return;
                }
            }
        }

        /**
         * Функция проверяющая, что хеш таблица не изменилась в момент цикла.
         */
        private void checkForModification() {
            if (modificationCounter != expectedModificationCounter) {
                throw new ConcurrentModificationException();
            }
        }

        /**
         * Функция проверяющая наличие следующего элемента.
         *
         * @return если следующий элемент существует, то true.
         */
        @Override
        public boolean hasNext() {
            checkForModification();
            return nextEntry != null;
        }

        /**
         * Функция возвращающая следующий элемент и передвигает текущий индекс.
         *
         * @return следующий элемент.
         */
        @Override
        public Entry<K, V> next() {
            checkForModification();
            if (nextEntry == null) {
                throw new NoSuchElementException();
            }
            lastReturned = nextEntry;
            advance();
            return lastReturned;
        }

        /**
         * Функция удаляющая элемент хеш таблицы в цикле, но не ломающая сам цикл.
         */
        @Override
        public void remove() {
            checkForModification();
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            HashTable.this.remove(lastReturned.getKey());
            expectedModificationCounter = modificationCounter;
            lastReturned = null;
        }
    }
}
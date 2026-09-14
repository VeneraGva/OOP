package org.example;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void testExampleFromTask() {
        int[] input = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, Main.heapsort(input));
    }

    @Test
    void testEmptyArray() {
        assertArrayEquals(new int[0], Main.heapsort(new int[0]));
    }

    @Test
    void testSingleElement() {
        assertArrayEquals(new int[]{42}, Main.heapsort(new int[]{42}));
    }

    @Test
    void testAlreadySorted() {
        int[] input = {1, 2, 3, 4, 5};
        assertArrayEquals(input, Main.heapsort(input));
    }

    @Test
    void testReverseSorted() {
        int[] input = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertArrayEquals(expected, Main.heapsort(input));
    }

    @Test
    void testDuplicates() {
        int[] input = {3, 1, 3, 2, 1, 2};
        int[] expected = {1, 1, 2, 2, 3, 3};
        assertArrayEquals(expected, Main.heapsort(input));
    }

    @Test
    void testNegativeNumbers() {
        int[] input = {-5, -1, -3, 0, 2, -8};
        int[] expected = {-8, -5, -3, -1, 0, 2};
        assertArrayEquals(expected, Main.heapsort(input));
    }

    @Test
    void testAllEqual() {
        int[] input = {7, 7, 7, 7, 7};
        assertArrayEquals(input, Main.heapsort(input));
    }

    @Test
    void testInputNotModified() {
        int[] input = {5, 4, 3, 2, 1};
        int[] copy = input.clone();
        Main.heapsort(input);
        assertArrayEquals(copy, input);
    }

    @Test
    void testRandomAgainstReference() {
        Random rnd = new Random(42);
        for (int iter = 0; iter < 200; iter++) {
            int size = rnd.nextInt(100);
            int[] input = new int[size];
            for (int i = 0; i < size; i++) {
                input[i] = rnd.nextInt(200) - 100;
            }
            int[] expected = input.clone();
            Arrays.sort(expected);
            assertArrayEquals(expected, Main.heapsort(input));
        }
    }

    @Test
    void testSortInPlace() {
        int[] arr = {5, 4, 3, 2, 1};
        Main.sortInPlace(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }
}
package ru.nsu.gorlova;

import java.util.List;

/**
 * Точка входа.
 */
public class Main {

    /**
     * Main.
     */
    public static void main(String[] args) {
        SubstringFind finder = new SubstringFind();
        List<Integer> boundaries = finder.find("src/main/resources/input.txt", "бра");
        System.out.println(boundaries);
    }
}
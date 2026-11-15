package ru.nsu.gorlova;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для поиска всех вхождений подстроки в файле.
 */
public class SubstringFind {

    /**
     * Находит индексы начала всех вхождений подстроки в файле.
     *
     * @param filePath путь файла
     * @param substring подстрока
     * @return массив из начальных индексов вхождения подстроки
     */
    public List<Integer> find(String filePath, String substring) {
        if (substring == null || substring.isEmpty()) {
            return new ArrayList<>();
        }
        List<Integer> boundaries = new ArrayList<>();
        int[] startIndexes = algorithmKnuthMorrisPratt(substring);
        int startIndex = 0;
        try (Reader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            int symbol = reader.read();
            int textIndex = 0;
            while (symbol != -1) {
                char currentChar = (char) symbol;
                while (startIndex != 0 && currentChar != substring.charAt(startIndex)) {
                    startIndex = startIndexes[startIndex - 1];
                }
                if (currentChar == substring.charAt(startIndex)) {
                    startIndex++;
                }
                if (startIndex == substring.length()) {
                    startIndex = startIndexes[startIndex - 1];
                    boundaries.add(textIndex - substring.length() + 1);
                }
                textIndex++;
                symbol = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("File read error: " + filePath, e);
        }
        return boundaries;
    }

    /**
     * Алгоритм Кнута-Морриса-Пратта.
     * Строит префикс-функцию для заданной подстроки.
     *
     * @param substring подстрока
     * @return массив значений префикс-функции
     */
    private int[] algorithmKnuthMorrisPratt(String substring) {
        int[] startIndexes = new int[substring.length()];
        int startIndex = 0;
        for (int i = 1; i < substring.length(); i++) {
            while (startIndex != 0 && substring.charAt(i) != substring.charAt(startIndex)) {
                startIndex = startIndexes[startIndex - 1];
            }
            if (substring.charAt(i) == substring.charAt(startIndex)) {
                startIndex++;
            }
            startIndexes[i] = startIndex;
        }
        return startIndexes;
    }
}
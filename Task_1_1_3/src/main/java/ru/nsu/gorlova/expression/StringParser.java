package ru.nsu.gorlova.expression;

import java.util.HashMap;
import java.util.Map;

/**
 * Парсер строк с присваиваниями переменных.
 */
public class StringParser {

    /**
     * Создаёт парсер.
     */
    public StringParser() {
    }

    /**
     * Парсит строку присваиваний в словарь значений.
     *
     * @param assignments строка вида "x = 10; y = 13"
     * @return словарь с именами переменных и их значениями
     */
    public static Map<String, Integer> parse(String assignments) {
        Map<String, Integer> values = new HashMap<>();
        if (assignments == null || assignments.trim().isEmpty()) {
            return values;
        }

        String[] pairs = assignments.split(";");
        for (String pair : pairs) {
            String[] parts = pair.trim().split("=");
            if (parts.length == 2) {
                String varName = parts[0].trim();
                int value = Integer.parseInt(parts[1].trim());
                values.put(varName, value);
            }
        }
        return values;
    }
}
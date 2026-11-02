package ru.nsu.gorlova.baseelements;

/**
 * Вершина графа.
 *
 * @param name Поле названия вершины графа.
 */
public record Vertex(String name) {

    /**
     * Функция, возвращающая название переменной.
     *
     * @return строковое название переменой.
     */
    @Override
    public String toString() {
        return name;
    }
}
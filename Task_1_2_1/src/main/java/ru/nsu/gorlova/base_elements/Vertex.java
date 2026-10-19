package ru.nsu.gorlova.base_elements;

/**
 * Вершина графа.
 *
 * @param name Поле названия вершины графа.
 */
public record Vertex(String name) {

    /**
     * Создание вершины графа.
     *
     * @param name название вершины графа.
     */
    public Vertex {
    }

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
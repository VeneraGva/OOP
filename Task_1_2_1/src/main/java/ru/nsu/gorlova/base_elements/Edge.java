package ru.nsu.gorlova.base_elements;

/**
 * Ориентированное ребро графа.
 *
 * @param inVertex  Поле, выходящей вершины.
 * @param outVertex Поле, входящей вершины.
 */
public record Edge(Vertex outVertex, Vertex inVertex) {

    /**
     * Создание ребра.
     *
     * @param inVertex  входящая вершина.
     * @param outVertex выходящая вершина.
     */
    public Edge {
    }

    /**
     * Функция, возвращающая строковую запись ребра.
     *
     * @return строковая запись ребра.
     */
    @Override
    public String toString() {
        return "( " + outVertex.toString() + "; " + inVertex.toString() + ") ";
    }
}

package ru.nsu.gorlova.graph;

import java.util.*;

/**
 * Абстрактный класс для реализаций графа: общие equals, hashCode, toString.
 */
public abstract class AbstractGraph implements Graph {

    /**
     * Сравнивает графы по множествам вершин и рёбер.
     *
     * @param o объект для сравнения.
     * @return правда, если графы равны.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph other = (Graph) o;
        return getVertices().equals(other.getVertices()) && getEdges().equals(other.getEdges());
    }

    /**
     * Возвращает хеш код графа, согласованный с {@link #equals(Object)}.
     *
     * @return хеш код.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getVertices(), getEdges());
    }

    /**
     * Возвращает строковую запись графа.
     *
     * @return строковая запись графа.
     */
    @Override
    public String toString() {
        return "V=" + getVertices() + ", E=" + getEdges();
    }
}
package ru.nsu.gorlova.graph;

import ru.nsu.gorlova.base_elements.Edge;
import ru.nsu.gorlova.base_elements.Vertex;
import java.util.Set;

/**
 * Интерфейс ориентированного графа.
 */
public interface Graph {

    /**
     * Добавляет вершину в граф один раз.
     *
     * @param vertex добавляемая вершина.
     */
    void addVertex(Vertex vertex);

    /**
     * Удаляет вершину и все инцидентные ей рёбра.
     *
     * @param vertex удаляемая вершина.
     */
    void removeVertex(Vertex vertex);

    /**
     * Проверяет наличие вершины в графе.
     *
     * @param vertex проверяемая вершина.
     * @return правда, если вершина есть в графе.
     */
    boolean hasVertex(Vertex vertex);

    /**
     * Возвращает множество всех вершин графа.
     *
     * @return множество вершин.
     */
    Set<Vertex> getVertices();

    /**
     * Возвращает количество вершин в графе.
     *
     * @return количество вершин.
     */
    int vertexCount();

    /**
     * Добавляет ребро в граф.
     *
     * @param edge добавляемое ребро.
     * @throws IllegalArgumentException если вершины ребра отсутствуют в графе.
     */
    void addEdge(Edge edge);

    /**
     * Удаляет ребро из графа.
     *
     * @param edge удаляемое ребро.
     */
    void removeEdge(Edge edge);

    /**
     * Проверяет наличие ребра в графе.
     *
     * @param edge проверяемое ребро.
     * @return правда, если ребро есть в графе.
     */
    boolean hasEdge(Edge edge);

    /**
     * Возвращает множество всех рёбер графа.
     *
     * @return множество рёбер.
     */
    Set<Edge> getEdges();

    /**
     * Возвращает количество рёбер в графе.
     *
     * @return количество рёбер.
     */
    int edgeCount();

    /**
     * Возвращает множество соседей вершины.
     *
     * @param vertex вершина, для которой ищутся соседи.
     * @return множество соседей.
     * @throws IllegalArgumentException если вершины нет в графе.
     */
    Set<Vertex> getNeighbors(Vertex vertex);

    /**
     * Возвращает входящую степень вершины.
     *
     * @param vertex вершина, для которой считается входящая степень.
     * @return входящая степень.
     * @throws IllegalArgumentException если вершины нет в графе.
     */
    int inDegree(Vertex vertex);
}
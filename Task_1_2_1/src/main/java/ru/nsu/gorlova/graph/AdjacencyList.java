package ru.nsu.gorlova.graph;

import ru.nsu.gorlova.base_elements.Edge;
import ru.nsu.gorlova.base_elements.Vertex;
import java.util.*;

/**
 * Список смежности.
 */
public class AdjacencyList extends AbstractGraph  {

    /**
     * Поле списка смежности.
     * Вершина -> (соседняя вершина -> кратность ребра между вершиной и соседней вершиной).
     */
    private final Map<Vertex, Map<Vertex, Integer>> adjacencyList = new HashMap<>();

    /**
     * Создание списка смежности.
     */
    public AdjacencyList() {
    }

    /**
     * Добавляет вершину в граф.
     *
     * @param vertex добавляемая вершина.
     */
    @Override
    public void addVertex(Vertex vertex) {
        adjacencyList.putIfAbsent(vertex, new HashMap<>());
    }

    /**
     * Удаление вершины из графа.
     *
     * @param vertex удаляемая вершина.
     */
    @Override
    public void removeVertex(Vertex vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return;
        }
        adjacencyList.remove(vertex);
        for (Map<Vertex, Integer> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
    }

    /**
     * Проверяет наличие вершины в графе.
     *
     * @param vertex проверяемая вершина.
     * @return правда, если вершина есть в графе.
     */
    @Override
    public boolean hasVertex(Vertex vertex) {
        return adjacencyList.containsKey(vertex);
    }

    /**
     * Возвращает множество всех вершин графа.
     *
     * @return множество вершин.
     */
    @Override
    public Set<Vertex> getVertices() {
        return new HashSet<>(adjacencyList.keySet());
    }

    /**
     * Возвращает множество всех вершин графа.
     *
     * @return множество вершин.
     */
    @Override
    public int vertexCount() {
        return adjacencyList.size();
    }

    /**
     * Добавляет ребро в граф.
     *
     * @param edge добавляемое ребро.
     * @throws IllegalArgumentException если вершины ребра отсутствуют в графе.
     */
    @Override
    public void addEdge(Edge edge) {
        Vertex from = edge.getOutVertex();
        Vertex to = edge.getInVertex();
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            throw new IllegalArgumentException("One or both edge vertices are not present in the graph");
        }
        Map<Vertex, Integer> neighbors = adjacencyList.get(from);
        int oldMultiplicity = neighbors.getOrDefault(to, 0);
        neighbors.put(to, oldMultiplicity + edge.getMultiplicity());
    }

    /**
     * Удаляет ребро из графа.
     *
     * @param edge удаляемое ребро.
     */
    @Override
    public void removeEdge(Edge edge) {
        Vertex from = edge.getOutVertex();
        Vertex to = edge.getInVertex();
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            return;
        }
        Map<Vertex, Integer> neighbors = adjacencyList.get(from);
        Integer oldMultiplicity = neighbors.get(to);
        if (oldMultiplicity == null) {
            return;
        }
        int newMultiplicity = oldMultiplicity - edge.getMultiplicity();
        if (newMultiplicity <= 0) {
            neighbors.remove(to);
        }
        else {
            neighbors.put(to, newMultiplicity);
        }
    }

    /**
     * Проверяет наличие ребра в графе.
     *
     * @param edge проверяемое ребро.
     * @return правда, если ребро есть в графе.
     */
    @Override
    public boolean hasEdge(Edge edge) {
        Vertex from = edge.getOutVertex();
        Vertex to = edge.getInVertex();
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            return false;
        }
        return adjacencyList.get(from).containsKey(to);
    }

    /**
     * Возвращает множество всех рёбер графа.
     *
     * @return множество рёбер.
     */
    @Override
    public Set<Edge> getEdges() {
        Set<Edge> result = new HashSet<>();
        for (Vertex from : adjacencyList.keySet()) {
            for (Map.Entry<Vertex, Integer> neighbor : adjacencyList.get(from).entrySet()) {
                result.add(new Edge(from, neighbor.getKey(), neighbor.getValue()));
            }
        }
        return result;
    }

    /**
     * Возвращает количество рёбер в графе.
     *
     * @return количество рёбер.
     */
    @Override
    public int edgeCount() {
        int count = 0;
        for (Map<Vertex, Integer> neighbors : adjacencyList.values()) {
            for (int multiplicity : neighbors.values()) {
                count += multiplicity;
            }
        }
        return count;
    }

    /**
     * Возвращает множество соседей вершины.
     *
     * @param vertex вершина, для которой ищутся соседи.
     * @return множество соседей.
     * @throws IllegalArgumentException если вершины нет в графе.
     */
    @Override
    public Set<Vertex> getNeighbors(Vertex vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            throw new IllegalArgumentException("The vertex is not in the graph: " + vertex);
        }
        return new HashSet<>(adjacencyList.get(vertex).keySet());
    }

    /**
     * Возвращает входящую степень вершины.
     *
     * @param vertex вершина, для которой считается входящая степень.
     * @return входящая степень.
     * @throws IllegalArgumentException если вершины нет в графе.
     */
    @Override
    public int inDegree(Vertex vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            throw new IllegalArgumentException("The vertex is not in the graph: " + vertex);
        }
        int sum = 0;
        for (Map<Vertex, Integer> neighbors : adjacencyList.values()) {
            Integer multiplicity = neighbors.get(vertex);
            if (multiplicity != null) {
                sum += multiplicity;
            }
        }
        return sum;
    }
}
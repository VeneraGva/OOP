package ru.nsu.gorlova.graph;

import ru.nsu.gorlova.base_elements.Edge;
import ru.nsu.gorlova.base_elements.Vertex;

import java.util.*;

/**
 * Матрица смежности.
 */
public class AdjacencyMatrix extends AbstractGraph  {

    /**
     * Список всех вершин графа (индекс вершины соответствует строке/столбцу матрицы).
     */
    private final List<Vertex> vertices = new ArrayList<>();

    /**
     * Отображение вершины в её индекс в матрице.
     */
    private final Map<Vertex, Integer> vertexToIndex = new HashMap<>();

    /**
     * Матрица смежности: matrix[i][j] — кратность ребра vertices[i] -> vertices[j].
     */
    private final List<List<Integer>> matrix = new ArrayList<>();

    /**
     * Создаёт матрицу смежности.
     */
    public AdjacencyMatrix() {
    }

    /**
     * Добавляет вершину в граф.
     *
     * @param vertex добавляемая вершина.
     */
    @Override
    public void addVertex(Vertex vertex) {
        if (vertexToIndex.containsKey(vertex)) {
            return;
        }
        int last = vertices.size();
        List<Integer> newRow = new ArrayList<>(Collections.nCopies(last + 1, 0));
        matrix.add(newRow);
        for (int index = 0; index < last; index++) {
            matrix.get(index).add(0);
        }
        vertexToIndex.put(vertex, last);
        vertices.add(vertex);
    }

    /**
     * Удаляет вершину и все инцидентные ей рёбра.
     *
     * @param vertex удаляемая вершина.
     */
    @Override
    public void removeVertex(Vertex vertex) {
        if (!vertexToIndex.containsKey(vertex)) {
            return;
        }
        int key = vertexToIndex.get(vertex);
        matrix.remove(key);
        for (List<Integer> row : matrix) {
            row.remove(key);
        }
        vertices.remove(key);
        vertexToIndex.remove(vertex);
        for (int index = key; index < vertices.size(); index++) {
            vertexToIndex.put(vertices.get(index), index);
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
        return vertexToIndex.containsKey(vertex);
    }

    /**
     * Возвращает множество всех вершин графа.
     *
     * @return множество вершин.
     */
    @Override
    public Set<Vertex> getVertices() {
        return new HashSet<>(vertices);
    }

    /**
     * Возвращает количество вершин в графе.
     *
     * @return количество вершин.
     */
    @Override
    public int vertexCount() {
        return vertices.size();
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
        if (!vertexToIndex.containsKey(from) || !vertexToIndex.containsKey(to)) {
            throw new IllegalArgumentException("Edge vertex or edge vertexes is not present in the graph.txt");
        }
        int indexFrom = vertexToIndex.get(from);
        int indexTo = vertexToIndex.get(to);
        matrix.get(indexFrom).set(indexTo, matrix.get(indexFrom).get(indexTo) + edge.getMultiplicity());
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
        if (!vertexToIndex.containsKey(from) || !vertexToIndex.containsKey(to)) {
            return;
        }
        int indexFrom = vertexToIndex.get(from);
        int indexTo = vertexToIndex.get(to);
        int current = matrix.get(indexFrom).get(indexTo);
        matrix.get(indexFrom).set(indexTo, Math.max(0, current - edge.getMultiplicity()));
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
        if (!vertexToIndex.containsKey(from) || !vertexToIndex.containsKey(to)) {
            return false;
        }
        return matrix.get(vertexToIndex.get(from)).get(vertexToIndex.get(to)) > 0;
    }

    /**
     * Возвращает множество всех рёбер графа.
     *
     * @return множество рёбер.
     */
    @Override
    public Set<Edge> getEdges() {
        Set<Edge> result = new HashSet<>();
        for (int row = 0; row < vertices.size(); row++) {
            for (int column = 0; column < vertices.size(); column++) {
                int multiplicity = matrix.get(row).get(column);
                if (multiplicity > 0) {
                    result.add(new Edge(vertices.get(row), vertices.get(column), multiplicity));
                }
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
        for (List<Integer> row : matrix) {
            for (int multiplicity : row) {
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
        if (!vertexToIndex.containsKey(vertex)) {
            throw new IllegalArgumentException("The vertex is not in the graph.txt: " + vertex);
        }
        int key = vertexToIndex.get(vertex);
        Set<Vertex> result = new HashSet<>();
        List<Integer> row = matrix.get(key);
        for (int index = 0; index < row.size(); index++) {
            if (row.get(index) > 0) {
                result.add(vertices.get(index));
            }
        }
        return result;
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
        if (!vertexToIndex.containsKey(vertex)) {
            throw new IllegalArgumentException("The vertex is not in the graph.txt: " + vertex);
        }
        int key = vertexToIndex.get(vertex);
        int sum = 0;
        for (List<Integer> row : matrix) {
            sum += row.get(key);
        }
        return sum;
    }
}
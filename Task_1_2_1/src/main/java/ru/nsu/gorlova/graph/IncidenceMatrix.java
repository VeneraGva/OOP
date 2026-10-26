package ru.nsu.gorlova.graph;

import ru.nsu.gorlova.base_elements.Edge;
import ru.nsu.gorlova.base_elements.Vertex;
import java.util.*;

/**
 * Матрица инцидентности.
 */
public class IncidenceMatrix extends AbstractGraph  {

    /**
     * Список всех вершин графа (индекс вершины соответствует строке матрицы).
     */
    private final List<Vertex> vertices = new ArrayList<>();

    /**
     * Список всех рёбер графа (индекс ребра соответствует столбцу матрицы).
     */
    private final List<Edge> edges = new ArrayList<>();

    /**
     * Матрица инцидентности: matrix[i][j] — кратность ребра j для вершины i
     * (положительная — вершина является началом ребра, отрицательная — концом, 0 — не инцидентна).
     */
    private final List<List<Integer>> matrix = new ArrayList<>();

    /**
     * Создаёт матрицу инцидентности.
     */
    public IncidenceMatrix() {
    }

    /**
     * Добавляет вершину в граф.
     *
     * @param vertex добавляемая вершина.
     */
    @Override
    public void addVertex(Vertex vertex) {
        if (vertices.contains(vertex)) {
            return;
        }
        List<Integer> newRow = new ArrayList<>(Collections.nCopies(edges.size(), 0));
        matrix.add(newRow);
        vertices.add(vertex);
    }

    /**
     * Удаляет вершину и все инцидентные ей рёбра.
     *
     * @param vertex удаляемая вершина.
     */
    @Override
    public void removeVertex(Vertex vertex) {
        int removedIndex = vertices.indexOf(vertex);
        if (removedIndex < 0) {
            return;
        }
        for (int column = edges.size() - 1; column >= 0; column--) {
            Edge edge = edges.get(column);
            if (edge.getOutVertex().equals(vertex) || edge.getInVertex().equals(vertex)) {
                edges.remove(column);
                for (List<Integer> row : matrix) {
                    row.remove(column);
                }
            }
        }
        matrix.remove(removedIndex);
        vertices.remove(removedIndex);
    }

    /**
     * Проверяет наличие вершины в графе.
     *
     * @param vertex проверяемая вершина.
     * @return правда, если вершина есть в графе.
     */
    @Override
    public boolean hasVertex(Vertex vertex) {
        return vertices.contains(vertex);
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
        if (!vertices.contains(from) || !vertices.contains(to)) {
            throw new IllegalArgumentException("One or both edge vertices are not present in the graph.txt");
        }
        int exist = findEdgeColumn(from, to);
        if (exist >= 0) {
            int newMultiplicity = edges.get(exist).getMultiplicity() + edge.getMultiplicity();
            edges.set(exist, new Edge(from, to, newMultiplicity));
            for (int row = 0; row < vertices.size(); row++) {
                int value = matrix.get(row).get(exist);
                if (value > 0) {
                    matrix.get(row).set(exist, newMultiplicity);
                }
                else if (value < 0) {
                    matrix.get(row).set(exist, -newMultiplicity);
                }
            }
            return;
        }
        int column = edges.size();
        edges.add(edge);
        int fromIndex = vertices.indexOf(from);
        int toIndex = vertices.indexOf(to);
        for (List<Integer> row : matrix) {
            row.add(0);
        }
        matrix.get(fromIndex).set(column, edge.getMultiplicity());
        matrix.get(toIndex).set(column, -edge.getMultiplicity());
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
        if (!vertices.contains(from) || !vertices.contains(to)) {
            return;
        }
        int column = findEdgeColumn(from, to);
        if (column < 0) {
            return;
        }
        int newMultiplicity = edges.get(column).getMultiplicity() - edge.getMultiplicity();
        if (newMultiplicity <= 0) {
            edges.remove(column);
            for (List<Integer> row : matrix) {
                row.remove(column);
            }
            return;
        }
        edges.set(column, new Edge(from, to, newMultiplicity));
        for (int row = 0; row < vertices.size(); row++) {
            int value = matrix.get(row).get(column);
            if (value > 0) {
                matrix.get(row).set(column, newMultiplicity);
            } else if (value < 0) {
                matrix.get(row).set(column, -newMultiplicity);
            }
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
        return edges.contains(edge);
    }

    /**
     * Возвращает множество всех рёбер графа.
     *
     * @return множество рёбер.
     */
    @Override
    public Set<Edge> getEdges() {
        return new HashSet<>(edges);
    }

    /**
     * Возвращает количество рёбер в графе.
     *
     * @return количество рёбер.
     */
    @Override
    public int edgeCount() {
        int count = 0;
        for (Edge edge : edges) {
            count += edge.getMultiplicity();
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
        int rowIndex = vertices.indexOf(vertex);
        if (rowIndex < 0) {
            throw new IllegalArgumentException("The vertex is not in the graph.txt: " + vertex);
        }
        Set<Vertex> result = new HashSet<>();
        List<Integer> row = matrix.get(rowIndex);
        for (int column = 0; column < row.size(); column++) {
            if (row.get(column) > 0) {
                result.add(edges.get(column).getInVertex());
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
        int rowIndex = vertices.indexOf(vertex);
        if (rowIndex < 0) {
            throw new IllegalArgumentException("The vertex is not in the graph.txt: " + vertex);
        }
        int sum = 0;
        for (int column = 0; column < edges.size(); column++) {
            if (matrix.get(rowIndex).get(column) < 0) {
                sum += edges.get(column).getMultiplicity();
            }
        }
        return sum;
    }

    /**
     * Находит номер столбца матрицы, соответствующего ребра.
     *
     * @param from выходящая вершина ребра.
     * @param to входящая вершина ребра.
     * @return индекс столбца или -1, если ребра нет.
     */
    private int findEdgeColumn(Vertex from, Vertex to) {
        for (int column = 0; column < edges.size(); column++) {
            Edge edge = edges.get(column);
            if (edge.getOutVertex().equals(from) && edge.getInVertex().equals(to)) {
                return column;
            }
        }
        return -1;
    }
}
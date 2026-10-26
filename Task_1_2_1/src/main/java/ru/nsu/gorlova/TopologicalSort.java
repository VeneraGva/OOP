package ru.nsu.gorlova;

import ru.nsu.gorlova.base_elements.Vertex;
import ru.nsu.gorlova.graph.Graph;
import java.util.*;

/**
 * Алгоритмы топологической сортировки ориентированного графа.
 */
public class TopologicalSort {

    /**
     * Создание топологической сортировки.
     */
    public TopologicalSort() {
    }

    /**
     * Топологическая сортировка алгоритмом Кана.
     *
     * @param graph граф.
     * @return список вершин в топологическом порядке.
     * @throws IllegalStateException если в графе есть цикл.
     */
    public List<Vertex> topologicalSortKahn(Graph graph) {
        Map<Vertex, Integer> inDegree = new HashMap<>();
        for (Vertex vertex : graph.getVertices()) {
            inDegree.put(vertex, graph.inDegree(vertex));
        }
        Deque<Vertex> queue = new ArrayDeque<>();
        for (Vertex vertex : graph.getVertices()) {
            if (inDegree.get(vertex) == 0) {
                queue.add(vertex);
            }
        }
        List<Vertex> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            result.add(vertex);
            for (Vertex neighbor : graph.getNeighbors(vertex)) {
                int newInDegree = inDegree.get(neighbor) - 1;
                inDegree.put(neighbor, newInDegree);
                if (newInDegree == 0) {
                    queue.add(neighbor);
                }
            }
        }
        if (result.size() != graph.vertexCount()) {
            throw new IllegalStateException("Graph contains a cycle");
        }
        return result;
    }

    /**
     * Топологическая сортировка обходом в глубину (DFS).
     *
     * @param graph граф.
     * @return список вершин в топологическом порядке.
     * @throws IllegalStateException если в графе есть цикл.
     */
    public List<Vertex> topologicalSortDfs(Graph graph) {
        Set<Vertex> visited = new HashSet<>();
        Set<Vertex> stack = new HashSet<>();
        Deque<Vertex> result = new ArrayDeque<>();
        for (Vertex vertex : graph.getVertices()) {
            if (!visited.contains(vertex)) {
                dfs(graph, vertex, visited, stack, result);
            }
        }
        return new ArrayList<>(result);
    }

    /**
     * Рекурсивно обходит вершину.
     *
     * @param graph граф.
     *  * @param vertex текущая вершина.
     *  * @param visited множество уже посещённых вершин.
     *  * @param stack множество вершин на текущем пути.
     *  * @param result очередь, в начало которой добавляются вершины после обработки.
     */
    private void dfs(Graph graph, Vertex vertex, Set<Vertex> visited, Set<Vertex> stack, Deque<Vertex> result) {
        visited.add(vertex);
        stack.add(vertex);
        for (Vertex neighbor : graph.getNeighbors(vertex)) {
            if (stack.contains(neighbor)) {
                throw new IllegalStateException("Graph contains a cycle");
            }
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, visited, stack, result);
            }
        }
        stack.remove(vertex);
        result.addFirst(vertex);
    }
}
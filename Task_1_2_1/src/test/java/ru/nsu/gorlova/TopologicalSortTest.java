package ru.nsu.gorlova;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.nsu.gorlova.base_elements.Edge;
import ru.nsu.gorlova.base_elements.Vertex;
import ru.nsu.gorlova.graph.Graph;
import ru.nsu.gorlova.graph.AdjacencyList;
import java.util.*;

class TopologicalSortTest {

    private final TopologicalSort sorter = new TopologicalSort();

    private Graph dag() {
        Graph graph = new AdjacencyList();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addEdge(new Edge(a, c, 1));
        graph.addEdge(new Edge(b, c, 1));
        graph.addEdge(new Edge(c, d, 1));
        return graph;
    }

    private void assertValidOrder(Graph graph, List<Vertex> order) {
        assertEquals(graph.vertexCount(), order.size());
        Map<Vertex, Integer> pos = new HashMap<>();
        for (int i = 0; i < order.size(); i++) {
            pos.put(order.get(i), i);
        }
        for (Edge e : graph.getEdges()) {
            assertTrue(pos.get(e.getOutVertex()) < pos.get(e.getInVertex()),
                    "Edge " + e + " violates topological order");
        }
    }

    @Test
    void kahn_dag_returnsValidOrder() {
        Graph graph = dag();
        List<Vertex> order = sorter.topologicalSortKahn(graph);
        assertValidOrder(graph, order);
    }

    @Test
    void dfs_dag_returnsValidOrder() {
        Graph graph = dag();
        List<Vertex> order = sorter.topologicalSortDfs(graph);
        assertValidOrder(graph, order);
    }

    @Test
    void kahn_emptyGraph_returnsEmpty() {
        Graph graph = new AdjacencyList();
        assertTrue(sorter.topologicalSortKahn(graph).isEmpty());
    }

    @Test
    void dfs_emptyGraph_returnsEmpty() {
        Graph graph = new AdjacencyList();
        assertTrue(sorter.topologicalSortDfs(graph).isEmpty());
    }

    @Test
    void kahn_singleVertex_returnsIt() {
        Graph graph = new AdjacencyList();
        Vertex a = new Vertex("A");
        graph.addVertex(a);
        assertEquals(List.of(a), sorter.topologicalSortKahn(graph));
    }

    @Test
    void dfs_singleVertex_returnsIt() {
        Graph graph = new AdjacencyList();
        Vertex a = new Vertex("A");
        graph.addVertex(a);
        assertEquals(List.of(a), sorter.topologicalSortDfs(graph));
    }

    @Test
    void kahn_cycle_throws() {
        Graph graph = new AdjacencyList();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(new Edge(a, b, 1));
        graph.addEdge(new Edge(b, a, 1));
        assertThrows(IllegalStateException.class, () -> sorter.topologicalSortKahn(graph));
    }

    @Test
    void dfs_cycle_throws() {
        Graph graph = new AdjacencyList();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(new Edge(a, b, 1));
        graph.addEdge(new Edge(b, a, 1));
        assertThrows(IllegalStateException.class, () -> sorter.topologicalSortDfs(graph));
    }

    @Test
    void dfs_multiplicity_returnsValidOrder() {
        Graph graph = new AdjacencyList();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(new Edge(a, b, 2));
        List<Vertex> order = sorter.topologicalSortDfs(graph);
        assertValidOrder(graph, order);
    }

    @Test
    void kahn_petlya_throws() {
        Graph graph = new AdjacencyList();
        Vertex a = new Vertex("A");
        graph.addVertex(a);
        graph.addEdge(new Edge(a, a, 1));
        assertThrows(IllegalStateException.class, () -> sorter.topologicalSortKahn(graph));
    }

    @Test
    void dfs_petlya_throws() {
        Graph graph = new AdjacencyList();
        Vertex a = new Vertex("A");
        graph.addVertex(a);
        graph.addEdge(new Edge(a, a, 1));
        assertThrows(IllegalStateException.class, () -> sorter.topologicalSortDfs(graph));
    }
}
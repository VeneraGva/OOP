package ru.nsu.gorlova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import ru.nsu.gorlova.baseelements.Edge;
import ru.nsu.gorlova.baseelements.Vertex;
import ru.nsu.gorlova.graph.AdjacencyList;
import ru.nsu.gorlova.graph.Graph;

class TopologicalSortTest {

    private final TopologicalSort sorter = new TopologicalSort();

    private Graph dag() {
        Graph graph = new AdjacencyList();
        Vertex aa = new Vertex("A");
        Vertex bb = new Vertex("B");
        Vertex cc = new Vertex("C");
        Vertex dd = new Vertex("D");
        graph.addVertex(aa);
        graph.addVertex(bb);
        graph.addVertex(cc);
        graph.addVertex(dd);
        graph.addEdge(new Edge(aa, cc, 1));
        graph.addEdge(new Edge(bb, cc, 1));
        graph.addEdge(new Edge(cc, dd, 1));
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
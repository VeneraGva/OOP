package ru.nsu.gorlova.graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.gorlova.base_elements.Edge;
import ru.nsu.gorlova.base_elements.Vertex;
import java.util.Set;

class AdjacencyListTest {

    private Graph graph;
    private final Vertex a = new Vertex("A");
    private final Vertex b = new Vertex("B");
    private final Vertex c = new Vertex("C");

    @BeforeEach
    void setUp() {
        graph = new AdjacencyList();
    }

    @Test
    void addVertex_increasesCount() {
        graph.addVertex(a);
        assertEquals(1, graph.vertexCount());
        assertTrue(graph.hasVertex(a));
    }

    @Test
    void addVertex_duplicate_ignored() {
        graph.addVertex(a);
        graph.addVertex(a);
        assertEquals(1, graph.vertexCount());
    }

    @Test
    void addVertex_multiple() {
        graph.addVertex(a);
        graph.addVertex(b);
        assertEquals(2, graph.vertexCount());
        assertEquals(Set.of(a, b), graph.getVertices());
    }

    @Test
    void removeVertex_existing_removed() {
        graph.addVertex(a);
        graph.removeVertex(a);
        assertFalse(graph.hasVertex(a));
        assertEquals(0, graph.vertexCount());
    }

    @Test
    void removeVertex_missing_noOp() {
        graph.addVertex(a);
        graph.removeVertex(b);
        assertEquals(1, graph.vertexCount());
    }

    @Test
    void removeVertex_removesIncidentEdges() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addEdge(new Edge(a, b, 1));
        graph.addEdge(new Edge(c, b, 1));
        graph.addEdge(new Edge(b, c, 1));

        graph.removeVertex(b);

        assertEquals(0, graph.edgeCount());
        assertFalse(graph.hasVertex(b));
        assertTrue(graph.getNeighbors(a).isEmpty());
    }

    @Test
    void addEdge_increasesCount() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(new Edge(a, b, 1));
        assertEquals(1, graph.edgeCount());
        assertTrue(graph.hasEdge(new Edge(a, b, 1)));
    }

    @Test
    void addEdge_missingVertex_throws() {
        graph.addVertex(a);
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(new Edge(a, b, 1)));
    }

    @Test
    void addEdge_accumulatesMultiplicity() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(new Edge(a, b, 1));
        graph.addEdge(new Edge(a, b, 2));
        assertEquals(3, graph.edgeCount());
        assertTrue(graph.hasEdge(new Edge(a, b, 1)));
    }

    @Test
    void removeEdge_existing_removed() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(new Edge(a, b, 1));
        graph.removeEdge(new Edge(a, b, 1));
        assertFalse(graph.hasEdge(new Edge(a, b, 1)));
        assertEquals(0, graph.edgeCount());
    }

    @Test
    void removeEdge_missing_noOp() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.removeEdge(new Edge(a, b, 1));
        assertEquals(0, graph.edgeCount());
    }

    @Test
    void removeEdge_partialMultiplicity_keepsEdge() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(new Edge(a, b, 3));
        graph.removeEdge(new Edge(a, b, 1));
        assertTrue(graph.hasEdge(new Edge(a, b, 1)));
        assertEquals(2, graph.edgeCount());
    }

    @Test
    void hasEdge_directionMatters() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(new Edge(a, b, 1));
        assertTrue(graph.hasEdge(new Edge(a, b, 1)));
        assertFalse(graph.hasEdge(new Edge(b, a, 1)));
    }

    @Test
    void hasEdge_missingVertex_false() {
        graph.addVertex(a);
        assertFalse(graph.hasEdge(new Edge(a, b, 1)));
    }

    @Test
    void getNeighbors_outgoingOnly() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addEdge(new Edge(a, b, 1));
        graph.addEdge(new Edge(a, c, 1));
        graph.addEdge(new Edge(c, a, 1));

        assertEquals(Set.of(b, c), graph.getNeighbors(a));
    }

    @Test
    void getNeighbors_empty_forIsolated() {
        graph.addVertex(a);
        assertTrue(graph.getNeighbors(a).isEmpty());
    }

    @Test
    void getNeighbors_missingVertex_throws() {
        assertThrows(IllegalArgumentException.class, () -> graph.getNeighbors(a));
    }

    @Test
    void inDegree_countsIncoming() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addEdge(new Edge(a, b, 1));
        graph.addEdge(new Edge(c, b, 1));
        assertEquals(2, graph.inDegree(b));
    }

    @Test
    void inDegree_countsMultiplicity() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(new Edge(a, b, 3));
        assertEquals(3, graph.inDegree(b));
    }

    @Test
    void inDegree_zero_forSource() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(new Edge(a, b, 1));
        assertEquals(0, graph.inDegree(a));
    }

    @Test
    void inDegree_missingVertex_throws() {
        assertThrows(IllegalArgumentException.class, () -> graph.inDegree(a));
    }

    @Test
    void getEdges_returnsAllWithMultiplicity() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(new Edge(a, b, 2));
        Set<Edge> edges = graph.getEdges();
        assertEquals(1, edges.size());
        Edge edge = edges.iterator().next();
        assertEquals(a, edge.getOutVertex());
        assertEquals(b, edge.getInVertex());
        assertEquals(2, edge.getMultiplicity());
    }

    @Test
    void edgeCount_empty() {
        assertEquals(0, graph.edgeCount());
    }
}
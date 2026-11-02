package ru.nsu.gorlova.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.gorlova.baseelements.Edge;
import ru.nsu.gorlova.baseelements.Vertex;

class AdjacencyListTest {

    private Graph graph;
    private final Vertex vertexA = new Vertex("A");
    private final Vertex vertexB = new Vertex("B");
    private final Vertex vertexC = new Vertex("C");

    @BeforeEach
    void setUp() {
        graph = new AdjacencyList();
    }

    @Test
    void addVertex_increasesCount() {
        graph.addVertex(vertexA);
        assertEquals(1, graph.vertexCount());
        assertTrue(graph.hasVertex(vertexA));
    }

    @Test
    void addVertex_duplicate_ignored() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexA);
        assertEquals(1, graph.vertexCount());
    }

    @Test
    void addVertex_multiple() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        assertEquals(2, graph.vertexCount());
        assertEquals(Set.of(vertexA, vertexB), graph.getVertices());
    }

    @Test
    void removeVertex_existing_removed() {
        graph.addVertex(vertexA);
        graph.removeVertex(vertexA);
        assertFalse(graph.hasVertex(vertexA));
        assertEquals(0, graph.vertexCount());
    }

    @Test
    void removeVertex_missing_noOp() {
        graph.addVertex(vertexA);
        graph.removeVertex(vertexB);
        assertEquals(1, graph.vertexCount());
    }

    @Test
    void removeVertex_removesIncidentEdges() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addEdge(new Edge(vertexA, vertexB, 1));
        graph.addEdge(new Edge(vertexC, vertexB, 1));
        graph.addEdge(new Edge(vertexB, vertexC, 1));

        graph.removeVertex(vertexB);

        assertEquals(0, graph.edgeCount());
        assertFalse(graph.hasVertex(vertexB));
        assertTrue(graph.getNeighbors(vertexA).isEmpty());
    }

    @Test
    void addEdge_increasesCount() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(new Edge(vertexA, vertexB, 1));
        assertEquals(1, graph.edgeCount());
        assertTrue(graph.hasEdge(new Edge(vertexA, vertexB, 1)));
    }

    @Test
    void addEdge_missingVertex_throws() {
        graph.addVertex(vertexA);
        assertThrows(IllegalArgumentException.class,
                () -> graph.addEdge(new Edge(vertexA, vertexB, 1)));
    }

    @Test
    void addEdge_accumulatesMultiplicity() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(new Edge(vertexA, vertexB, 1));
        graph.addEdge(new Edge(vertexA, vertexB, 2));
        assertEquals(3, graph.edgeCount());
        assertTrue(graph.hasEdge(new Edge(vertexA, vertexB, 1)));
    }

    @Test
    void removeEdge_existing_removed() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(new Edge(vertexA, vertexB, 1));
        graph.removeEdge(new Edge(vertexA, vertexB, 1));
        assertFalse(graph.hasEdge(new Edge(vertexA, vertexB, 1)));
        assertEquals(0, graph.edgeCount());
    }

    @Test
    void removeEdge_missing_noOp() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.removeEdge(new Edge(vertexA, vertexB, 1));
        assertEquals(0, graph.edgeCount());
    }

    @Test
    void removeEdge_partialMultiplicity_keepsEdge() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(new Edge(vertexA, vertexB, 3));
        graph.removeEdge(new Edge(vertexA, vertexB, 1));
        assertTrue(graph.hasEdge(new Edge(vertexA, vertexB, 1)));
        assertEquals(2, graph.edgeCount());
    }

    @Test
    void hasEdge_directionMatters() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(new Edge(vertexA, vertexB, 1));
        assertTrue(graph.hasEdge(new Edge(vertexA, vertexB, 1)));
        assertFalse(graph.hasEdge(new Edge(vertexB, vertexA, 1)));
    }

    @Test
    void hasEdge_missingVertex_false() {
        graph.addVertex(vertexA);
        assertFalse(graph.hasEdge(new Edge(vertexA, vertexB, 1)));
    }

    @Test
    void getNeighbors_outgoingOnly() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addEdge(new Edge(vertexA, vertexB, 1));
        graph.addEdge(new Edge(vertexA, vertexC, 1));
        graph.addEdge(new Edge(vertexC, vertexA, 1));

        assertEquals(Set.of(vertexB, vertexC), graph.getNeighbors(vertexA));
    }

    @Test
    void getNeighbors_empty_forIsolated() {
        graph.addVertex(vertexA);
        assertTrue(graph.getNeighbors(vertexA).isEmpty());
    }

    @Test
    void getNeighbors_missingVertex_throws() {
        assertThrows(IllegalArgumentException.class, () -> graph.getNeighbors(vertexA));
    }

    @Test
    void inDegree_countsIncoming() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addEdge(new Edge(vertexA, vertexB, 1));
        graph.addEdge(new Edge(vertexC, vertexB, 1));
        assertEquals(2, graph.inDegree(vertexB));
    }

    @Test
    void inDegree_countsMultiplicity() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(new Edge(vertexA, vertexB, 3));
        assertEquals(3, graph.inDegree(vertexB));
    }

    @Test
    void inDegree_zero_forSource() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(new Edge(vertexA, vertexB, 1));
        assertEquals(0, graph.inDegree(vertexA));
    }

    @Test
    void inDegree_missingVertex_throws() {
        assertThrows(IllegalArgumentException.class, () -> graph.inDegree(vertexA));
    }

    @Test
    void getEdges_returnsAllWithMultiplicity() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(new Edge(vertexA, vertexB, 2));
        Set<Edge> edges = graph.getEdges();
        assertEquals(1, edges.size());
        Edge edge = edges.iterator().next();
        assertEquals(vertexA, edge.getOutVertex());
        assertEquals(vertexB, edge.getInVertex());
        assertEquals(2, edge.getMultiplicity());
    }

    @Test
    void edgeCount_empty() {
        assertEquals(0, graph.edgeCount());
    }
}
package ru.nsu.gorlova;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.nsu.gorlova.base_elements.Edge;
import ru.nsu.gorlova.base_elements.Vertex;
import ru.nsu.gorlova.graph.Graph;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

class FileGraphReaderTest {

    private Reader openResource(String name) {
        InputStream is = FileGraphReaderTest.class.getResourceAsStream("/" + name);
        assertNotNull(is, "Resource not found: " + name);
        return new InputStreamReader(is);
    }

    @Test
    void readValidGraph() throws IOException {
        try (GraphReader reader = new FileGraphReader(openResource("graph-valid.txt"))) {
            Graph graph = reader.readNextGraph();
            assertEquals(4, graph.vertexCount());
            assertEquals(7, graph.edgeCount());
            assertTrue(graph.hasVertex(new Vertex("yea")));
            assertTrue(graph.hasEdge(new Edge(new Vertex("yea"), new Vertex("no"), 1)));
        }
    }

    @Test
    void readEmptyGraph() throws IOException {
        try (GraphReader reader = new FileGraphReader(openResource("graph-empty.txt"))) {
            Graph graph = reader.readNextGraph();
            assertEquals(0, graph.vertexCount());
            assertEquals(0, graph.edgeCount());
        }
    }

    @Test
    void readGraphWithoutEdges() throws IOException {
        try (GraphReader reader = new FileGraphReader(openResource("graph-no-edges.txt"))) {
            Graph graph = reader.readNextGraph();
            assertEquals(3, graph.vertexCount());
            assertEquals(0, graph.edgeCount());
        }
    }

    @Test
    void readEdgeWithMissingVertex_throws() {
        try (GraphReader reader = new FileGraphReader(openResource("graph-missing-vertex.txt"))) {
            assertThrows(IllegalArgumentException.class, reader::readNextGraph);
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    @Test
    void readTruncatedFile_throws() {
        try (GraphReader reader = new FileGraphReader(openResource("graph-truncated.txt"))) {
            assertThrows(RuntimeException.class, reader::readNextGraph);
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }
}
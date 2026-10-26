package ru.nsu.gorlova;

import ru.nsu.gorlova.base_elements.Edge;
import ru.nsu.gorlova.base_elements.Vertex;
import ru.nsu.gorlova.graph.AdjacencyList;
import ru.nsu.gorlova.graph.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class FileGraphReader implements GraphReader {

    private final BufferedReader lineReader;
    private boolean isClosed = false;

    public FileGraphReader(Reader reader) {
        if (reader == null) {
            throw new IllegalArgumentException("Base reader cannot be null");
        }
        this.lineReader = new BufferedReader(reader);
    }

    @Override
    public Graph readNextGraph() throws IOException, IllegalStateException {
        if (isClosed) {
            throw new IllegalStateException("Cannot read from a closed reader");
        }

        int vertexCount = Integer.parseInt(lineReader.readLine().trim());
        Graph graph = new AdjacencyList();
        for (int i = 0; i < vertexCount; i++) {
            graph.addVertex(new Vertex(lineReader.readLine().trim()));
        }

        int edgeCount = Integer.parseInt(lineReader.readLine().trim());
        for (int i = 0; i < edgeCount; i++) {
            String[] parts = lineReader.readLine().trim().split("\\s+");
            graph.addEdge(new Edge(new Vertex(parts[0]), new Vertex(parts[1]), Integer.parseInt(parts[2])));
        }

        return graph;
    }

    @Override
    public void close() throws IOException {
        if (!isClosed) {
            lineReader.close();
            isClosed = true;
        }
    }
}

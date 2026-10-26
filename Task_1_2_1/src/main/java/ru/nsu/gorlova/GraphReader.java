package ru.nsu.gorlova;

import ru.nsu.gorlova.graph.Graph;
import java.io.IOException;

public interface GraphReader extends AutoCloseable {

    Graph readNextGraph() throws IOException, IllegalStateException;

    @Override
    void close() throws IOException;
}
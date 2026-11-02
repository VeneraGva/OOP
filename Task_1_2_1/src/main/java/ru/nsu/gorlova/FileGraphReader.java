package ru.nsu.gorlova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import ru.nsu.gorlova.baseelements.Edge;
import ru.nsu.gorlova.baseelements.Vertex;
import ru.nsu.gorlova.graph.AdjacencyList;
import ru.nsu.gorlova.graph.Graph;

/**
 * Считыватель графа с файла.
 */
public class FileGraphReader implements GraphReader {

    /**
     * Поле буфера.
     */
    private final BufferedReader lineReader;

    /**
     * Поле флага закрытия файлы.
     */
    private boolean isClosed = false;

    /**
     * Создание считывателя графа в файле.
     *
     * @param reader файл, с которого считывается граф.
     */
    public FileGraphReader(Reader reader) {
        if (reader == null) {
            throw new IllegalArgumentException("Base reader cannot be null");
        }
        this.lineReader = new BufferedReader(reader);
    }

    /**
     * Функция для считывания графа.
     *
     * @return граф.
     * @throws IOException выбрасывается при неправильном чтении.
     * @throws IllegalStateException выбрасывается, если файл закрыт.
     */
    @Override
    public Graph readNextGraph() throws IOException, IllegalStateException {
        if (isClosed) {
            throw new IllegalStateException("Cannot read from a closed reader");
        }
        int vertexCount = Integer.parseInt(lineReader.readLine().trim());
        Graph graph = new AdjacencyList();
        for (int index = 0; index < vertexCount; index++) {
            graph.addVertex(new Vertex(lineReader.readLine().trim()));
        }
        int edgeCount = Integer.parseInt(lineReader.readLine().trim());
        for (int index = 0; index < edgeCount; index++) {
            String[] parts = lineReader.readLine().trim().split("\\s+");
            graph.addEdge(new Edge(new Vertex(parts[0]),
                    new Vertex(parts[1]), Integer.parseInt(parts[2])));
        }
        return graph;
    }

    /**
     * Закрытие файла.
     *
     * @throws IOException если не закроется.
     */
    @Override
    public void close() throws IOException {
        if (!isClosed) {
            lineReader.close();
            isClosed = true;
        }
    }
}

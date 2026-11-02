package ru.nsu.gorlova;

import java.io.IOException;
import ru.nsu.gorlova.graph.Graph;

/**
 * Интерфейс считывателя графа.
 */
public interface GraphReader extends AutoCloseable {

    /**
     * Функция для считывания графа.
     *
     * @return граф.
     * @throws IOException выбрасывается при неправильном чтении.
     * @throws IllegalStateException выбрасывается, если файл закрыт.
     */
    Graph readNextGraph() throws IOException, IllegalStateException;

    /**
     * Закрытие файла.
     *
     * @throws IOException если не закроется.
     */
    @Override
    void close() throws IOException;
}
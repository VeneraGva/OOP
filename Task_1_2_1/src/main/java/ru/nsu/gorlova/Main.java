package ru.nsu.gorlova;

import ru.nsu.gorlova.graph.Graph;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {

    public static void main(String[] args) {
        InputStream is = Main.class.getResourceAsStream("/graph.txt");
        if (is == null) {
            System.err.println("graph.txt.txt not found in resources");
            return;
        }

        Graph graph;
        try (Reader reader = new InputStreamReader(is);
             GraphReader graphReader = new FileGraphReader(reader)) {
            graph = graphReader.readNextGraph();
        } catch (IOException | IllegalStateException e) {
            System.err.println("Read error: " + e.getMessage());
            return;
        }

        System.out.println("Vertices: " + graph.vertexCount());
        System.out.println("Edges: " + graph.edgeCount());
        System.out.println("Neighbors of yea: " + graph.getNeighbors(new ru.nsu.gorlova.base_elements.Vertex("yea")));
        System.out.println("In-degree of no: " + graph.inDegree(new ru.nsu.gorlova.base_elements.Vertex("no")));
    }
}
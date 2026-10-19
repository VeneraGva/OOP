package ru.nsu.gorlova.graph;

import ru.nsu.gorlova.base_elements.Edge;
import ru.nsu.gorlova.base_elements.Vertex;

import java.util.Set;

public class Adjacency_list implements Graph {

    @Override
    public void addVertex(Vertex vertex) {

    }

    @Override
    public void deleteVertex(Vertex vertex) {

    }

    @Override
    public boolean hasVertex(Vertex vertex) {
        return false;
    }

    @Override
    public Set<Vertex> getVertices() {
        return Set.of();
    }

    @Override
    public int vertexCount() {
        return 0;
    }

    @Override
    public void addEdge(Edge edge) {

    }

    @Override
    public void deleteEdge(Edge edge) {

    }

    @Override
    public boolean hasEdge(Edge edge) {
        return false;
    }

    @Override
    public Set<Edge> getEdges() {
        return Set.of();
    }

    @Override
    public int edgeCount() {
        return 0;
    }

    @Override
    public Set<Vertex> getNeighbors(Vertex vertex) {
        return Set.of();
    }

    @Override
    public int inDegree(Vertex vertex) {
        return 0;
    }
}

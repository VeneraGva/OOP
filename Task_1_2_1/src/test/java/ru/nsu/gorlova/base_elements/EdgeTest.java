package ru.nsu.gorlova.base_elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EdgeTest {

    private final Vertex a = new Vertex("A");
    private final Vertex b = new Vertex("B");

    @Test
    void constructor_storesFields() {
        Edge edge = new Edge(a, b, 2);
        assertEquals(a, edge.getOutVertex());
        assertEquals(b, edge.getInVertex());
        assertEquals(2, edge.getMultiplicity());
    }

    @Test
    void toString_formatsCorrectly() {
        Edge edge = new Edge(a, b, 2);
        assertEquals("[ ( A; B), 2 ]", edge.toString());
    }

    @Test
    void equals_sameVertices_true() {
        Edge e1 = new Edge(a, b, 1);
        Edge e2 = new Edge(a, b, 5);
        assertEquals(e1, e2);
    }

    @Test
    void equals_multiplicityIgnored() {
        Edge e1 = new Edge(a, b, 1);
        Edge e2 = new Edge(a, b, 100);
        assertEquals(e1, e2);
    }

    @Test
    void equals_differentFrom_false() {
        Edge e1 = new Edge(a, b, 1);
        Edge e2 = new Edge(b, a, 1);
        assertNotEquals(e1, e2);
    }

    @Test
    void equals_differentTo_false() {
        Vertex c = new Vertex("C");
        Edge e1 = new Edge(a, b, 1);
        Edge e2 = new Edge(a, c, 1);
        assertNotEquals(e1, e2);
    }

    @Test
    void equals_null_false() {
        Edge edge = new Edge(a, b, 1);
        assertNotEquals(edge, null);
    }

    @Test
    void equals_otherType_false() {
        Edge edge = new Edge(a, b, 1);
        assertNotEquals(edge, "A B");
    }

    @Test
    void equals_sameReference_true() {
        Edge edge = new Edge(a, b, 1);
        assertEquals(edge, edge);
    }

    @Test
    void hashCode_sameVertices_equal() {
        Edge e1 = new Edge(a, b, 1);
        Edge e2 = new Edge(a, b, 5);
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    void hashCode_differentVertices_notEqual() {
        Edge e1 = new Edge(a, b, 1);
        Edge e2 = new Edge(b, a, 1);
        assertNotEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    void setMultiplicity_valid_updates() {
        Edge edge = new Edge(a, b, 1);
        edge.setMultiplicity(5);
        assertEquals(5, edge.getMultiplicity());
    }

    @Test
    void setMultiplicity_zero_throws() {
        Edge edge = new Edge(a, b, 1);
        assertThrows(IllegalArgumentException.class, () -> edge.setMultiplicity(0));
    }

    @Test
    void setMultiplicity_negative_throws() {
        Edge edge = new Edge(a, b, 1);
        assertThrows(IllegalArgumentException.class, () -> edge.setMultiplicity(-3));
    }
}
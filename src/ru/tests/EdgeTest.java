package ru.tests;

import org.junit.Test;
import ru.graph.DirectedEdge;
import ru.graph.Edge;
import ru.graph.Vertex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EdgeTest {
    Vertex<String> a = new Vertex<>("a");
    Vertex<String> b = new Vertex<>("b");
    Edge<String> edge = new Edge<>(a, b);

    @Test
    public void shouldReturnOtherVertex() {
        assertEquals(b, edge.getOther(a));
    }

    @Test
    public void shouldThrowExceptionWhenVertexDoesntBelowToTheEdge() {
        assertThrows(IllegalArgumentException.class, () -> edge.getOther(new Vertex<>("c")));
    }

    @Test
    public void getStartAndGetEndShouldReturnProperVertices() {
        DirectedEdge<String> directedEdge = new DirectedEdge<>(a, b);

        assertEquals(a, directedEdge.getStart());
        assertEquals(b, directedEdge.getEnd());
    }
}

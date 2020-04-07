package ru.tests;

import org.junit.Test;
import ru.graph.DirectedEdge;
import ru.graph.Edge;
import ru.graph.Vertex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EdgeTest {
    Vertex a = new Vertex();
    Vertex b = new Vertex();
    Edge edge = new Edge(a, b);

    @Test
    public void shouldReturnOtherVertex() {
        assertEquals(b, edge.getOther(a));
    }

    @Test
    public void shouldThrowExceptionWhenVertexDoesntBelowToTheEdge() {
        assertThrows(IllegalArgumentException.class, () -> edge.getOther(new Vertex()));
    }

    @Test
    public void getStartAndGetEndShouldReturnProperVertices() {
        DirectedEdge directedEdge = new DirectedEdge(a, b);

        assertEquals(a, directedEdge.getStart());
        assertEquals(b, directedEdge.getEnd());
    }
}

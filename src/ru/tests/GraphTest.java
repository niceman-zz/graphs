package ru.tests;

import org.junit.Test;
import ru.graph.Edge;
import ru.graph.Graph;
import ru.graph.Vertex;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    @Test
    public void verticesShouldContainEdgeBetweenThem() {
        Graph graph = new Graph();
        Vertex a = graph.addVertex();
        Vertex b = graph.addVertex();

        Edge edge = graph.addEdge(a, b);

        assertEquals(edge, a.getEdges().get(0));
        assertEquals(edge, b.getEdges().get(0));
    }

    @Test
    public void shouldFindOneEdgePathInBothDirections() {
        Graph graph = new Graph();
        Vertex a = graph.addVertex();
        Vertex b = graph.addVertex();
        graph.addEdge(a, b);

        List<Edge> abPath = graph.getPath(a, b);
        List<Edge> baPath = graph.getPath(b, a);

        assertEquals(1, abPath.size());
        assertEquals(abPath, baPath);
    }

    @Test
    public void shouldFindTwoEdgePathInBothDirections() {
        Graph graph = new Graph();
        Vertex a = graph.addVertex();
        Vertex b = graph.addVertex();
        Vertex c = graph.addVertex();
        Vertex d = graph.addVertex();
        Edge ab = graph.addEdge(a, b);
        Edge ac = graph.addEdge(a, c);
        Edge cd = graph.addEdge(c, d);

        List<Edge> adPath = graph.getPath(a, d);
        List<Edge> daPath = graph.getPath(d, a);

        // test a -> d path
        assertEquals(2, adPath.size());
        assertEquals(ac, adPath.get(0));
        assertEquals(cd, adPath.get(1));

        // test d -> a path
        assertEquals(2, daPath.size());
        assertEquals(cd, daPath.get(0));
        assertEquals(ac, daPath.get(1));
    }

    @Test
    public void shouldNotFindPath() {
        Graph graph = new Graph();
        Vertex a = graph.addVertex();
        Vertex b = graph.addVertex();
        Vertex c = graph.addVertex();
        Vertex d = graph.addVertex();
        graph.addEdge(a, b);
        graph.addEdge(c, d);

        List<Edge> path = graph.getPath(a, d);

        assertTrue(path.isEmpty());
    }
}

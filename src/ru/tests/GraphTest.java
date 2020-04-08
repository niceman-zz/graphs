package ru.tests;

import org.junit.Test;
import ru.graph.Edge;
import ru.graph.Graph;
import ru.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    @Test
    public void verticesShouldContainEdgeBetweenThem() {
        Graph<String> graph = new Graph<>();
        Vertex<String> a = graph.addVertex("a");
        Vertex<String> b = graph.addVertex("b");

        Edge<String> edge = graph.addEdge(a, b);

        assertEquals(edge, a.getEdges().get(0));
        assertEquals(edge, b.getEdges().get(0));
    }

    @Test
    public void loopEdgeShouldAddOnlyOneEdgeToVertex() {
        Graph<String> graph = new Graph<>();
        Vertex<String> a = graph.addVertex("a");

        graph.addEdge(a, a);

        assertEquals(1, a.getEdges().size());
    }

    @Test
    public void shouldFindOneEdgePathInBothDirections() {
        Graph<String> graph = new Graph<>();
        Vertex<String> a = graph.addVertex("a");
        Vertex<String> b = graph.addVertex("b");
        graph.addEdge(a, b);

        List<Edge<String>> abPath = graph.getPath(a, b);
        List<Edge<String>> baPath = graph.getPath(b, a);

        assertEquals(1, abPath.size());
        assertEquals(abPath, baPath);
    }

    @Test
    public void shouldFindTwoEdgePathInBothDirections() {
        Graph<String> graph = new Graph<>();
        Vertex<String> a = graph.addVertex("a");
        Vertex<String> b = graph.addVertex("b");
        Vertex<String> c = graph.addVertex("c");
        Vertex<String> d = graph.addVertex("d");
        Edge<String> ab = graph.addEdge(a, b);
        Edge<String> ac = graph.addEdge(a, c);
        Edge<String> cd = graph.addEdge(c, d);

        List<Edge<String>> adPath = graph.getPath(a, d);
        List<Edge<String>> daPath = graph.getPath(d, a);

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
        Graph<String> graph = new Graph<>();
        Vertex<String> a = graph.addVertex("a");
        Vertex<String> b = graph.addVertex("b");
        Vertex<String> c = graph.addVertex("c");
        Vertex<String> d = graph.addVertex("d");
        graph.addEdge(a, b);
        graph.addEdge(c, d);

        List<Edge<String>> path = graph.getPath(a, d);

        assertTrue(path.isEmpty());
    }

    @Test
    public void shouldFindReturnPath() {
        Graph<String> graph = new Graph<>();
        Vertex<String> a = graph.addVertex("a");
        Vertex<String> b = graph.addVertex("b");
        graph.addEdge(a, b);

        List<Edge<String>> path = graph.getPath(a, a);

        assertEquals(2, path.size());
        assertEquals(path.get(0), path.get(1));
    }

    @Test
    public void shouldFindLoopPath() {
        Graph<String> graph = new Graph<>();
        Vertex<String> a = graph.addVertex("a");
        Vertex<String> b = graph.addVertex("b");
        Edge<String> edge = graph.addEdge(a, a);
        graph.addEdge(a, b);

        List<Edge<String>> path = graph.getPath(a, a);

        assertEquals(1, path.size());
        assertEquals(edge, path.get(0));
    }

    @Test
    public void shouldNotIncludeLoopInPath() {
        Graph<String> graph = new Graph<>();
        Vertex<String> a = graph.addVertex("a");
        Vertex<String> b = graph.addVertex("b");
        Vertex<String> c = graph.addVertex("c");
        List<Edge<String>> expectedPath = new ArrayList<>();
        expectedPath.add(graph.addEdge(a, b));
        graph.addEdge(b, b); // not included
        expectedPath.add(graph.addEdge(b, c));

        List<Edge<String>> path = graph.getPath(a, c);

        assertEquals(expectedPath, path);
    }
}

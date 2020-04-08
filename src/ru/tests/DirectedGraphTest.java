package ru.tests;

import org.junit.Test;
import ru.graph.DirectedGraph;
import ru.graph.Edge;
import ru.graph.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DirectedGraphTest {
    @Test
    public void shouldFindPathInOneDirectionAndShouldntInAnother() {
        DirectedGraph<String> graph = new DirectedGraph<>();
        Vertex<String> a = graph.addVertex("a");
        Vertex<String> b = graph.addVertex("b");
        graph.addEdge(a, b);

        List<Edge<String>> abPath = graph.getPath(a, b);
        List<Edge<String>> baPath = graph.getPath(b, a);

        assertEquals(1, abPath.size());
        assertTrue(baPath.isEmpty());
    }

    @Test
    public void shouldFindTwoEdgePath() {
        DirectedGraph<String> graph = new DirectedGraph<>();
        Vertex<String> a = graph.addVertex("a");
        Vertex<String> b = graph.addVertex("b");
        Vertex<String> c = graph.addVertex("c");
        Vertex<String> d = graph.addVertex("d");
        Edge<String> ab = graph.addEdge(a, b);
        Edge<String> ac = graph.addEdge(a, c);
        Edge<String> cd = graph.addEdge(c, d);

        List<Edge<String>> path = graph.getPath(a, d);

        assertFalse(path.isEmpty());
        assertEquals(2, path.size());
        assertEquals(ac, path.get(0));
        assertEquals(cd, path.get(1));
    }

    @Test
    public void shouldNotFindPathBecauseOfEdgeDirection() {
        DirectedGraph<String> graph = new DirectedGraph<>();
        Vertex<String> a = graph.addVertex("a");
        Vertex<String> b = graph.addVertex("b");
        Vertex<String> c = graph.addVertex("c");
        graph.addEdge(a, b);
        graph.addEdge(c, b);

        List<Edge<String>> path = graph.getPath(a, c);

        assertTrue(path.isEmpty());
    }

    @Test
    public void somePathMessShouldGoFine() {
        DirectedGraph<String> graph = new DirectedGraph<>();
        Vertex<String> a = graph.addVertex("a");
        Vertex<String> b = graph.addVertex("b");
        Vertex<String> c = graph.addVertex("c");
        Vertex<String> d = graph.addVertex("d");
        Vertex<String> e = graph.addVertex("e");
        Vertex<String> f = graph.addVertex("f");
        Edge<String> ab = graph.addEdge(a, b);
        Edge<String> bd = graph.addEdge(b, d);
        Edge<String> dc = graph.addEdge(d, c);
        Edge<String> cf = graph.addEdge(c, f);
        Edge<String> ef = graph.addEdge(e, f);
        Edge<String> ec = graph.addEdge(e, c);
        Edge<String> cb = graph.addEdge(c, b);
        Edge<String> ba = graph.addEdge(b, a);
        List<Edge<String>> afPathExpected = Arrays.asList(ab, bd, dc, cf);
        List<Edge<String>> eaPathExpected = Arrays.asList(ec, cb, ba);
        List<Edge<String>> efPathExpected = Arrays.asList(ef);
        List<Edge<String>> dcPathExpected = Arrays.asList(dc);

        List<Edge<String>> afPath = graph.getPath(a, f);
        List<Edge<String>> faPath = graph.getPath(f, a);
        List<Edge<String>> aePath = graph.getPath(a, e);
        List<Edge<String>> efPath = graph.getPath(e, f);
        List<Edge<String>> eaPath = graph.getPath(e, a);
        List<Edge<String>> dcPath = graph.getPath(d, c);

        assertTrue(faPath.isEmpty());
        assertTrue(aePath.isEmpty());
        assertEquals(afPathExpected, afPath);
        assertEquals(eaPathExpected, eaPath);
        assertEquals(efPathExpected, efPath);
        assertEquals(dcPathExpected, dcPath);
    }

    @Test
    public void shouldFindReturnPath() {
        DirectedGraph<String> graph = new DirectedGraph<>();
        Vertex<String> a = graph.addVertex("a");
        Vertex<String> b = graph.addVertex("b");
        Vertex<String> c = graph.addVertex("c");
        List<Edge<String>> expectedPath = new ArrayList<>();
        expectedPath.add(graph.addEdge(a, b));
        expectedPath.add(graph.addEdge(b, c));
        expectedPath.add(graph.addEdge(c, a));

        List<Edge<String>> path = graph.getPath(a, a);

        assertEquals(expectedPath, path);
    }
}

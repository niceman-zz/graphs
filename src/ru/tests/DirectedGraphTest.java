package ru.tests;

import org.junit.Test;
import ru.graph.DirectedGraph;
import ru.graph.Edge;
import ru.graph.Vertex;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DirectedGraphTest {
    @Test
    public void shouldFindPathInOneDirectionAndShouldntInAnother() {
        DirectedGraph graph = new DirectedGraph();
        Vertex a = graph.addVertex();
        Vertex b = graph.addVertex();
        graph.addEdge(a, b);

        List<Edge> abPath = graph.getPath(a, b);
        List<Edge> baPath = graph.getPath(b, a);

        assertEquals(1, abPath.size());
        assertTrue(baPath.isEmpty());
    }

    @Test
    public void shouldFindTwoEdgePath() {
        DirectedGraph graph = new DirectedGraph();
        Vertex a = graph.addVertex();
        Vertex b = graph.addVertex();
        Vertex c = graph.addVertex();
        Vertex d = graph.addVertex();
        Edge ab = graph.addEdge(a, b);
        Edge ac = graph.addEdge(a, c);
        Edge cd = graph.addEdge(c, d);

        List<Edge> path = graph.getPath(a, d);

        assertFalse(path.isEmpty());
        assertEquals(2, path.size());
        assertEquals(ac, path.get(0));
        assertEquals(cd, path.get(1));
    }

    @Test
    public void shouldNotFindPathBecauseOfEdgeDirection() {
        DirectedGraph graph = new DirectedGraph();
        Vertex a = graph.addVertex();
        Vertex b = graph.addVertex();
        Vertex c = graph.addVertex();
        graph.addEdge(a, b);
        graph.addEdge(c, b);

        List<Edge> path = graph.getPath(a, c);

        assertTrue(path.isEmpty());
    }

    @Test
    public void somePathMessShouldGoFine() {
        DirectedGraph graph = new DirectedGraph();
        Vertex a = graph.addVertex();
        Vertex b = graph.addVertex();
        Vertex c = graph.addVertex();
        Vertex d = graph.addVertex();
        Vertex e = graph.addVertex();
        Vertex f = graph.addVertex();
        Edge ab = graph.addEdge(a, b);
        Edge bd = graph.addEdge(b, d);
        Edge dc = graph.addEdge(d, c);
        Edge cf = graph.addEdge(c, f);
        Edge ef = graph.addEdge(e, f);
        Edge ec = graph.addEdge(e, c);
        Edge cb = graph.addEdge(c, b);
        Edge ba = graph.addEdge(b, a);
        List<Edge> afPathExpected = Arrays.asList(ab, bd, dc, cf);
        List<Edge> eaPathExpected = Arrays.asList(ec, cb, ba);
        List<Edge> efPathExpected = Arrays.asList(ef);
        List<Edge> dcPathExpected = Arrays.asList(dc);

        List<Edge> afPath = graph.getPath(a, f);
        List<Edge> faPath = graph.getPath(f, a);
        List<Edge> aePath = graph.getPath(a, e);
        List<Edge> efPath = graph.getPath(e, f);
        List<Edge> eaPath = graph.getPath(e, a);
        List<Edge> dcPath = graph.getPath(d, c);

        assertTrue(faPath.isEmpty());
        assertTrue(aePath.isEmpty());
        assertEquals(afPathExpected, afPath);
        assertEquals(eaPathExpected, eaPath);
        assertEquals(efPathExpected, efPath);
        assertEquals(dcPathExpected, dcPath);
    }
}

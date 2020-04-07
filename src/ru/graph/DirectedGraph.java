package ru.graph;

import java.util.LinkedList;
import java.util.Map;

public class DirectedGraph extends Graph {
    @Override
    DirectedEdge createEdge(Vertex start, Vertex end) {
        return new DirectedEdge(start, end);
    }

    @Override
    boolean findFinish(Edge edge, Vertex current, Vertex finish, LinkedList<Edge> path, Map<Vertex, Boolean> passed) {
        DirectedEdge directedEdge = (DirectedEdge) edge;
        if (directedEdge.getStart() == current) {
            path.add(edge);
            if (edge.getOther(current) == finish || traverse(edge.getOther(current), finish, path, passed)) {
                return true;
            }
            path.removeLast();
        }
        return false;
    }
}

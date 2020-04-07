package ru.graph;

import java.util.*;

public class Graph {
    List<Vertex> vertices = new ArrayList<>();
    List<Edge> edges = new ArrayList<>();

    public Vertex addVertex() {
        Vertex vertex = new Vertex();
        vertices.add(vertex);
        return vertex;
    }

    public Edge addEdge(Vertex a, Vertex b) {
        Edge edge = createEdge(a, b);
        a.getEdges().add(edge);
        b.getEdges().add(edge);
        edges.add(edge);
        return edge;
    }

    Edge createEdge(Vertex a, Vertex b) {
        return new Edge(a, b);
    }

    public List<Edge> getPath(Vertex start, Vertex finish) {
        Map<Vertex, Boolean> passed = new HashMap<>();
        vertices.forEach(vertex -> passed.put(vertex, false));
        LinkedList<Edge> path = new LinkedList<>();
        traverse(start, finish, path, passed);
        return path;
    }

    boolean traverse(Vertex current, Vertex finish, LinkedList<Edge> path, Map<Vertex, Boolean> passed) {
        if (passed.get(current)) {
            return false;
        }
        passed.put(current, true);
        for (Edge edge : current.getEdges()) {
            if (findFinish(edge, current, finish, path, passed)) {
                return true;
            }
        }
        return false;
    }

    boolean findFinish(Edge edge, Vertex current, Vertex finish, LinkedList<Edge> path, Map<Vertex, Boolean> passed) {
        path.add(edge);
        if (edge.getOther(current) == finish || traverse(edge.getOther(current), finish, path, passed)) {
            return true;
        }
        path.removeLast();
        return false;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }
}

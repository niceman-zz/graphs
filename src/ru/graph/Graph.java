package ru.graph;

import java.util.*;

public class Graph<E> {
    List<Vertex<E>> vertices = new ArrayList<>();
    List<Edge<E>> edges = new ArrayList<>();

    public Vertex<E> addVertex(E value) {
        Vertex<E> vertex = new Vertex<>(value);
        vertices.add(vertex);
        return vertex;
    }

    public Edge<E> addEdge(Vertex<E> a, Vertex<E> b) {
        Edge<E> edge = createEdge(a, b);
        a.getEdges().add(edge);
        if (a != b) {
            b.getEdges().add(edge);
        }
        edges.add(edge);
        return edge;
    }

    Edge<E> createEdge(Vertex<E> a, Vertex<E> b) {
        return new Edge<>(a, b);
    }

    public List<Edge<E>> getPath(Vertex<E> start, Vertex<E> finish) {
        if (start == finish) {
            Edge<E> loopEdge = findLoopEdge(start);
            if (loopEdge != null) {
                return Collections.singletonList(loopEdge);
            }
        }
        Map<Vertex<E>, Boolean> passed = new HashMap<>();
        vertices.forEach(vertex -> passed.put(vertex, false));
        LinkedList<Edge<E>> path = new LinkedList<>();
        traverse(start, finish, path, passed);
        return path;
    }

    private Edge<E> findLoopEdge(Vertex<E> vertex) {
        for (Edge<E> edge: vertex.getEdges()) {
            if (edge.getOther(vertex) == vertex) {
                return edge;
            }
        }
        return null;
    }

    boolean traverse(Vertex<E> current, Vertex<E> finish, LinkedList<Edge<E>> path, Map<Vertex<E>, Boolean> passed) {
        if (passed.get(current)) {
            return false;
        }
        passed.put(current, true);
        for (Edge<E> edge : current.getEdges()) {
            if (findFinish(edge, current, finish, path, passed)) {
                return true;
            }
        }
        return false;
    }

    boolean findFinish(Edge<E> edge, Vertex<E> current, Vertex<E> finish, LinkedList<Edge<E>> path,
                       Map<Vertex<E>, Boolean> passed) {
        path.add(edge);
        if (edge.getOther(current) == finish || traverse(edge.getOther(current), finish, path, passed)) {
            return true;
        }
        path.removeLast();
        return false;
    }

    public List<Vertex<E>> getVertices() {
        return vertices;
    }

    public void printGraph() {
        System.out.println("Vertex\tLinked vertices");
        for (Vertex<E> vertex: vertices) {
            System.out.print(vertex.getValue() + "\t\t");
            for (Edge<E> edge: vertex.getEdges()) {
                printNextVertex(edge, vertex);
            }
            System.out.println();
        }
    }

    void printNextVertex(Edge<E> edge, Vertex<E> currentVertex) {
        System.out.print(" " + edge.getOther(currentVertex));
    }
}

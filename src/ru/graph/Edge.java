package ru.graph;

public class Edge {
    final Vertex a;
    final Vertex b;

    public Edge(Vertex a, Vertex b) {
        this.a = a;
        this.b = b;
    }

    public Vertex[] getVertices() {
        return new Vertex[] {a, b};
    }

    public Vertex getOther(Vertex vertex) {
        if (vertex == a) {
            return b;
        } else if (vertex == b) {
            return a;
        }
        throw new IllegalArgumentException("This edge doesn't contain vertex: " + vertex);
    }
}

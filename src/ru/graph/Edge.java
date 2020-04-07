package ru.graph;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(a, edge.a) &&
                Objects.equals(b, edge.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}

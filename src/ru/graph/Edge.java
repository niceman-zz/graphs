package ru.graph;

import java.util.Objects;

public class Edge<E> {
    final Vertex<E> a;
    final Vertex<E> b;

    public Edge(Vertex<E> a, Vertex<E> b) {
        this.a = a;
        this.b = b;
    }

    public Vertex<E> getOther(Vertex<E> vertex) {
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
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(a, edge.a) &&
                Objects.equals(b, edge.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}

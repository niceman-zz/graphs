package ru.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex<E> {
    private final E value;
    private final List<Edge<E>> edges;

    public Vertex(E value) {
        this.value = value;
        edges = new ArrayList<>();
    }

    public List<Edge<E>> getEdges() {
        return edges;
    }

    public E getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return Objects.equals(value, vertex.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

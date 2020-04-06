package ru.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Vertex {
    private static final AtomicInteger counter = new AtomicInteger(0);

    private final int index;
    private final List<Edge> edges;

    public Vertex() {
        index = counter.getAndIncrement();
        edges = new ArrayList<>();
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public String getName() {
        return String.valueOf(index);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return index == vertex.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}

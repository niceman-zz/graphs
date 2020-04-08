package ru.graph;

public class DirectedEdge<E> extends Edge<E> {
    public DirectedEdge(Vertex<E> start, Vertex<E> end) {
        super(start, end);
    }

    public Vertex<E> getStart() {
        return a;
    }

    public Vertex<E> getEnd() {
        return b;
    }
}

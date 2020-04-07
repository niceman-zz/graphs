package ru.graph;

public class DirectedEdge extends Edge {
    public DirectedEdge(Vertex start, Vertex end) {
        super(start, end);
    }

    public Vertex getStart() {
        return a;
    }

    public Vertex getEnd() {
        return b;
    }
}

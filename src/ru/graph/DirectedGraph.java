package ru.graph;

import java.util.LinkedList;
import java.util.Map;

public class DirectedGraph<E> extends Graph<E> {
    @Override
    DirectedEdge<E> createEdge(Vertex<E> start, Vertex<E> end) {
        return new DirectedEdge<>(start, end);
    }

    @Override
    boolean findFinish(Edge<E> edge, Vertex<E> current, Vertex<E> finish, LinkedList<Edge<E>> path,
                       Map<Vertex<E>, Boolean> passed) {
        DirectedEdge<E> directedEdge = (DirectedEdge<E>) edge;
        if (directedEdge.getStart() == current) {
            path.add(edge);
            if (edge.getOther(current) == finish || traverse(edge.getOther(current), finish, path, passed)) {
                return true;
            }
            path.removeLast();
        }
        return false;
    }

    @Override
    void printNextVertex(Edge<E> edge, Vertex<E> currentVertex) {
        if (((DirectedEdge<E>) edge).getStart() == currentVertex) {
            System.out.print(" " + edge.getOther(currentVertex));
        }
    }
}

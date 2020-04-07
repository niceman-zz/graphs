package ru.graph;

import java.util.*;

public class Main {
    private final static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    private Graph graph;

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    public void run() {
        createGraph();
        showMainMenu();
        System.out.println();
        System.out.println("To see it again just type 'menu'\nTo exit type 'exit'.");

        do {
            System.out.print("> ");
            try {
                eval(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private void createGraph() {
        System.out.println("Which graph do you want to create?");
        System.out.println("(1) Undirected");
        System.out.println("(2) Directed");
        System.out.print("(1) > ");
        String graphType;
        do {
            graphType = scanner.nextLine();
            if (graphType.isEmpty()) {
                graphType = "1";
            }
            if (!graphType.equals("1") && !graphType.equals("2")) {
                System.out.print("Wrong graph type! Choose between 1 and 2: ");
                graphType = null;
            }
        } while (graphType == null);
        if (graphType.equals("2")) {
            graph = new DirectedGraph();
            System.out.println("A directed graph has been created.");
        } else {
            graph = new Graph();
            System.out.println("An undirected graph has been created.");
        }
        System.out.println();
    }

    private void showMainMenu() {
        System.out.println("Commands:\n" +
                           "add-vertex\n" +
                           "add-vertices\n" +
                           "add-edge\n" +
                           "get-path\n" +
                           "print-graph");
    }

    private void eval(String command) {
        switch (command) {
            case "menu": showMainMenu(); break;
            case "add-vertex": addVertex(); break;
            case "add-vertices": addVertices(); break;
            case "add-edge": addEdge(); break;
            case "get-path": getPath(); break;
            case "print-graph": printGraph(); break;
            case "exit": exit();
            default: System.out.println("Unknown command");
        }
    }

    private void addVertex() {
        Vertex vertex = graph.addVertex();
        System.out.println("Added vertex: " + vertex);
    }

    private void addVertices() {
        System.out.print("How many vertices you'd like to add? ");
        int num = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < num; i++) {
            graph.addVertex();
        }
        System.out.println("Added " + num + " vertices.");
    }

    private void addEdge() {
        if (graphIsEmpty() || isOnlyOneVertex()) {
            return;
        }
        Vertex[] vertices = chooseVertices();
        graph.addEdge(vertices[0], vertices[1]);
        System.out.println(String.format("A new edge between %s and %s has been added.", vertices[0].getName(),
                vertices[1].getName()));
    }

    private Vertex[] chooseVertices() {
        Map<String, Vertex> verticesMap = new HashMap<>();
        graph.getVertices().forEach(vertex -> verticesMap.put(vertex.getName(), vertex));
        showVertices();
        Vertex start = chooseVertex("start", verticesMap);
        Vertex end = chooseVertex("end", verticesMap);
        return new Vertex[] {start, end};
    }

    private void showVertices() {
        System.out.println("Vertices list:");
        graph.getVertices().forEach(System.out::println);
    }

    private Vertex chooseVertex(String name, Map<String, Vertex> verticesMap) {
        String vertexName = null;
        System.out.print("Choose " + name + " vertex: ");
        do {
            vertexName = scanner.nextLine();
            if (!verticesMap.containsKey(vertexName)) {
                System.out.print("Wrong vertex, try again: ");
                vertexName = null;
            }
        } while (vertexName == null);
        return verticesMap.get(vertexName);
    }

    private void getPath() {
        if (graphIsEmpty() || isOnlyOneVertex()) {
            return;
        }
        Vertex[] vertices = chooseVertices();
        List<Edge> path = graph.getPath(vertices[0], vertices[1]);
        if (path.isEmpty()) {
            System.out.println(String.format("There's no path between vertices %s and %s", vertices[0].getName(),
                    vertices[1].getName()));
            return;
        }
        Vertex prev = vertices[0];
        System.out.print(prev);
        for (Edge edge: path) {
            Vertex current = edge.getOther(prev);
            System.out.print(" => " + current);
            prev = current;
        };
        System.out.println();
    }

    private boolean graphIsEmpty() {
        if (graph.getVertices().isEmpty()) {
            System.out.println("Graph is empty.");
            return true;
        }
        return false;
    }

    private boolean isOnlyOneVertex() {
        if (graph.getVertices().size() == 1) {
            System.out.println("The graph contains only one vertex and loops aren't supported.");
            return true;
        }
        return false;
    }

    private void printGraph() {
        boolean directed = graph instanceof DirectedGraph;
        List<Vertex> vertices = graph.getVertices();
        System.out.println("Vertex\tLinked vertices");
        for (Vertex vertex: vertices) {
            System.out.print(vertex.getName() + "\t\t");
            for (Edge edge: vertex.getEdges()) {
                if (!directed || ((DirectedEdge) edge).getStart() == vertex) {
                    System.out.print(" " + edge.getOther(vertex));
                }
            }
            System.out.println();
        }
    }

    private void exit() {
        System.exit(0);
    }
}

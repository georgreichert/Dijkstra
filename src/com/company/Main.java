package com.company;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");
        graph.addNode("H");
        graph.addNode("I");

        graph.addEdge("A", "B", 2, true);
        graph.addEdge("A", "C", 3, true);
        graph.addEdge("B", "D", 3, true);
        graph.addEdge("B", "E", 4, true);
        graph.addEdge("C", "D", 1, true);
        graph.addEdge("D", "E", 3, true);
        graph.addEdge("D", "F", 5, true);
        graph.addEdge("E", "F", 4, true);
        graph.addEdge("E", "G", 6, true);
        graph.addEdge("F", "G", 2, true);
        graph.addEdge("D", "H", 1, true);
        graph.addEdge("H", "I", 1, true);
        graph.addEdge("C", "I", 8, true);

        LinkedList<Node> path = graph.shortestPathDijkstra("A", "I");
        System.out.println(path.size());

        graph.print();

        for (Node node : path) {
            System.out.println(node != null ? node.getName() : "null");
        }
    }
}

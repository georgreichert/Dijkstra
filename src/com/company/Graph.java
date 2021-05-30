package com.company;

import java.util.*;

public class Graph {
    private HashMap<String, Node> nodes = new HashMap<String, Node>();

    public void addNode(String name) {
        if (!nodes.containsKey(name)) {
            nodes.put(name, new Node(name));
        }
    }

    public void addEdge(String from, String to, int weight, String line) {
        addEdge(from, to, weight, line, false);
    }

    public void addEdge(String from, String to, int weight, String line, boolean bidirectional) {
        Node start = nodes.get(from);
        if (start != null) {
            Node target = nodes.get(to);
            if (target != null) {
                start.addEdge(target, weight, line);
                if (bidirectional) {
                    target.addEdge(start, weight, line);
                }
            }
        }
    }

    public LinkedList<Node> shortestPathDijkstra(String from, String to) {
        PriorityQueue<Node> nodeQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getCurrentCost() < o2.getCurrentCost() ?
                        -1 :
                        (o1.getCurrentCost() > o2.getCurrentCost() ?
                            1 : 0);
            }
        });
        nodes.forEach((name, node) -> {
            node.initialize();
        });

        Node start = nodes.get(from);
        LinkedList<Node> path = new LinkedList<Node>();
        if (start != null) {
            start.setCurrentCost(0);
            Node target = nodes.get(to);
            if (target != null) {
                start.dijkstra(target, nodeQueue, path);
            }
        }
        return path;
    }

    public void print() {
        this.nodes.forEach((startName, startNode) -> {
            System.out.print(startName + ": ");
            this.nodes.forEach((targetName, targetNode) -> {
                int weight = startNode.getEdgeWeight(targetName);
                if (weight >= 0) {
                    System.out.print("[" + targetName + "," + weight + "] ");
                }
            });
            System.out.println();
        });
    }
}

package com.company;

import java.util.*;

public class Graph {
    private HashMap<String, Node> nodes = new HashMap<String, Node>();

    public void addNode(String name) {
        if (!nodes.containsKey(name)) { // only add if not already added
            nodes.put(name, new Node(name));
        }
    }

    // for unidirectional edges
    public void addEdge(String from, String to, int weight, String line) {
        addEdge(from, to, weight, line, false);
    }

    public void addEdge(String from, String to, int weight, String line, boolean bidirectional) {
        Node start = nodes.get(from);
        if (start != null) { // check if start node exists
            Node target = nodes.get(to);
            if (target != null) { // check if target node exists
                start.addEdge(target, weight, line);
                if (bidirectional) { // if bidirectional is true, add
                    target.addEdge(start, weight, line);
                }
            }
        }
    }

    public LinkedList<Node> shortestPathDijkstra(String from, String to) {
        // PriorityQueue of Nodes that are sorted by cost to reach the Node
        // this will be handed to the recursive function
        PriorityQueue<Node> nodeQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getCurrentCost() < o2.getCurrentCost() ?
                        -1 :
                        (o1.getCurrentCost() > o2.getCurrentCost() ?
                            1 : 0);
            }
        });
        nodes.forEach((name, node) -> { // all Nodes are initialized to have no precursor and cost to reach Integer.MAX_VALUE
            node.initialize();
        });

        Node start = nodes.get(from);
        LinkedList<Node> path = new LinkedList<Node>(); // shortest path will be saved to this List
        if (start != null) { // check if start Node exists
            start.setCurrentCost(0); // start Node has cost of 0
            Node target = nodes.get(to);
            if (target != null) { // check if target Node exists
                start.dijkstra(target, nodeQueue, path); // start search in first Node
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

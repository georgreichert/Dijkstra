package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Node {
    private final String name;
    private List<Edge> edges = new LinkedList<Edge>();
    private Node shortestPathTo = null;
    private int currentCost = Integer.MAX_VALUE;
    private String lineTo = "";

    public void setLineTo(String lineTo) {
        this.lineTo = lineTo;
    }

    public String getLineTo() {
        return lineTo;
    }

    public Node(String name) {
        this.name = name;
    }

    public void addEdge(Node target, int weight, String line) {
        edges.add(new Edge(target, weight, line));
    }

    public void dijkstra(Node target, PriorityQueue<Node> nodeQueue, List<Node> path) {
        if (this.equals(target)) { // if target Node is found, algorithm stops
            retrievePath(path); // recursively reads shortest path from all precursor Nodes
            return;
        }
        for (Edge edge : edges) { // check if any Nodes can be reached from this Node with less cost than previously known
            Node end = edge.getEnd();
            if (end.getCurrentCost() > this.currentCost + edge.getWeight()) { // if Node can be reached cheaper from here
                nodeQueue.remove(end); // remove it from the PriorityQueue
                end.setCurrentCost(this.currentCost + edge.getWeight()); // overwrite its cost,
                end.setShortestPathTo(this); // its precursor and
                end.setLineTo(edge.getLine()); // the line identifier of the used mode of transportation (U1, U2, ...)
                nodeQueue.add(end); // then add it back into the Queue
            }
        }
        if (nodeQueue.peek().getCurrentCost() < Integer.MAX_VALUE) { // we are not interested in Nodes with infinite current cost
            // remove the Node from the front of the Queue, which is the Node with current cheapest cost to reach
            // continue the search from there
            nodeQueue.poll().dijkstra(target, nodeQueue, path);
        }
    }

    public void retrievePath(List<Node> path) {
        if (this.shortestPathTo != null) { // starting Node has no precursor
            if (path.isEmpty()) { // target Node has to add itself to the path as well
                path.add(this);
            }
            path.add(0, this.shortestPathTo); // push precursor to front of the path List
            shortestPathTo.retrievePath(path); // continue with precursor
        }
    }

    public void setCurrentCost(int currentCost) {
        this.currentCost = currentCost;
    }

    public void setShortestPathTo(Node shortestPathTo) {
        this.shortestPathTo = shortestPathTo;
    }

    public int getCurrentCost() {
        return currentCost;
    }

    public void initialize() {
        this.currentCost = Integer.MAX_VALUE;
        this.shortestPathTo = null;
    }

    public String getName() {
        return name;
    }

    // only used for printing Graph
    public int getEdgeWeight(String targetName) {
        for (Edge edge : edges) {
            if (edge.getEnd().getName().equals(targetName)) {
                return edge.getWeight();
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

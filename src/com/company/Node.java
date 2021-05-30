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
        if (this.equals(target)) {
            retrievePath(path);
            return;
        }
        for (Edge edge : edges) {
            Node end = edge.getEnd();
            if (end.getCurrentCost() > this.currentCost + edge.getWeight()) {
                nodeQueue.remove(end);
                end.setCurrentCost(this.currentCost + edge.getWeight());
                end.setShortestPathTo(this);
                end.setLineTo(edge.getLine());
                nodeQueue.add(end);
            }
        }
        if (nodeQueue.peek().getCurrentCost() < Integer.MAX_VALUE) {
            nodeQueue.poll().dijkstra(target, nodeQueue, path);
        }
    }

    public void retrievePath(List<Node> path) {
        if (this.shortestPathTo != null) {
            if (path.isEmpty()) {
                path.add(this);
            }
            path.add(0, this.shortestPathTo);
            shortestPathTo.retrievePath(path);
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

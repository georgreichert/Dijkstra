package com.company;

public class Edge {
    private Node end;
    private int weight;

    public Edge(Node end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    public Node getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "end: " + this.end.getName();
    }
}

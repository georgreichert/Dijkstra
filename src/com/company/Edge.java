package com.company;

public class Edge {
    private Node end;
    private int weight;
    private String line;

    public String getLine() {
        return line;
    }

    public Edge(Node end, int weight, String line) {
        this.end = end;
        this.weight = weight;
        this.line = line;
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

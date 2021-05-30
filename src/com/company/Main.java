package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();

        try {
            readGraphFromFile(args[0], graph);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        List<Node> path = graph.shortestPathDijkstra(args[1], args[2]);

        try {
            System.out.println("Fahrzeit: " + path.get(path.size() - 1).getCurrentCost() + " Minuten");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Kein Weg gefunden!");
        }
        for (Node node : path) {
            System.out.println(node.getLineTo() + "-> " + node.getName());
        }
    }

    private static void readGraphFromFile(String filepath, Graph graph) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        String nextLine;
        while ((nextLine = br.readLine()) != null) {
            String[] temp = nextLine.split(":");
            String line = temp[0];
            temp = temp[1].split("\"\s");
            String lastStation = "";
            for (String station : temp) {
                String[] split = station.split("\s\"");
                split[1] = split[1].indexOf("\"") >= 0 ? split[1].substring(0, split[1].length() - 1) : split[1];
                graph.addNode(split[1]);
                if (!split[0].isEmpty()) {
                    int weight = Integer.parseInt(split[0]);
                    graph.addEdge(split[1], lastStation, weight, line, true);
                }
                lastStation = split[1];
            }
        }
    }
}

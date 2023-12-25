package com.bomfim.interview;

import java.util.*;

public class DijkstraSearch {

    static class Graph {
        private final Map<Integer, List<Edge>> adjacencyList;

        public Graph(int vertices) {
            this.adjacencyList = new HashMap<>();
            for (int i = 0; i < vertices; i++) {
                adjacencyList.put(i, new ArrayList<>());
            }
        }

        public void addEdge(int source, int destination, int weight) {
            adjacencyList.get(source).add(new Edge(destination, weight));
            adjacencyList.get(destination).add(new Edge(source, weight)); // For undirected graph
        }

        public Map<Integer, Integer> dijkstra(int source) {
            PriorityQueue<NodeDistance> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
            Map<Integer, Integer> distances = new HashMap<>();

            priorityQueue.add(new NodeDistance(source, 0));
            distances.put(source, 0);

            while (!priorityQueue.isEmpty()) {
                NodeDistance currentNode = priorityQueue.poll();

                for (Edge neighbor : adjacencyList.get(currentNode.node)) {
                    int newDistance = currentNode.distance + neighbor.weight;
                    if (newDistance < distances.getOrDefault(neighbor.destination, Integer.MAX_VALUE)) {
                        distances.put(neighbor.destination, newDistance);
                        priorityQueue.add(new NodeDistance(neighbor.destination, newDistance));
                    }
                }
            }

            return distances;
        }

        public List<Integer> shortestPath(int source, int target) {
            Map<Integer, Integer> distances = dijkstra(source);
            List<Integer> path = new ArrayList<>();

            // Reconstructing the path from source to target
            int current = target;
            while (current != source && distances.containsKey(current)) {
                path.add(current);
                current = getParentWithMinDistance(current, distances);
            }
            path.add(source);
            Collections.reverse(path);

            return path;
        }

        private int getParentWithMinDistance(int node, Map<Integer, Integer> distances) {
            int parent = -1;
            int minDistance = Integer.MAX_VALUE;

            for (Edge edge : adjacencyList.get(node)) {
                if (distances.containsKey(edge.destination) && distances.get(edge.destination) + edge.weight < minDistance) {
                    minDistance = distances.get(edge.destination) + edge.weight;
                    parent = edge.destination;
                }
            }

            return parent;
        }

        private static class Edge {
            int destination;
            int weight;

            public Edge(int destination, int weight) {
                this.destination = destination;
                this.weight = weight;
            }
        }

        private static class NodeDistance {
            int node;
            int distance;

            public NodeDistance(int node, int distance) {
                this.node = node;
                this.distance = distance;
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(7);

        graph.addEdge(0, 1,5);
        graph.addEdge(0, 2,3);
        graph.addEdge(1, 3,4);
        graph.addEdge(2, 3,4);
        graph.addEdge(1, 5,12);
        graph.addEdge(3, 5,5);
        graph.addEdge(5, 4,2);
        graph.addEdge(4, 6,3);
        graph.addEdge(5, 6,10);

        int sourceNode = 0;
        int targetNode = 6;
        List<Integer> shortestPath = graph.shortestPath(sourceNode, targetNode);

        System.out.println("Shortest path from node based on weight " + sourceNode + " to node " + targetNode + ": " + shortestPath);
        //Shortest path based on weight from node 0 to node 6: [0, 2, 3, 5, 4, 6]
    }
}

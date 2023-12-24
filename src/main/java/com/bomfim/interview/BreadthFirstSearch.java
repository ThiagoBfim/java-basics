package com.bomfim.interview;

import java.util.*;

public class BreadthFirstSearch {

    static class SocialGraph {
        private final int vertices;
        private final Map<Integer, List<Integer>> adjacencyList;

        public SocialGraph(int vertices) {
            this.vertices = vertices;
            this.adjacencyList = new HashMap<>();
            for (int i = 0; i < vertices; i++) {
                adjacencyList.put(i, new ArrayList<>());
            }
        }

        public void addFriendship(int user1, int user2) {
            adjacencyList.get(user1).add(user2);
            adjacencyList.get(user2).add(user1); // For undirected graph
        }

        public List<Integer> shortestPath(int startNode, int endNode) {
            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[vertices];
            int[] parent = new int[vertices];

            queue.add(startNode);
            visited[startNode] = true;
            parent[startNode] = -1;

            while (!queue.isEmpty()) {
                int currentNode = queue.poll();

                for (int neighbor : adjacencyList.get(currentNode)) {
                    if (!visited[neighbor]) {
                        queue.add(neighbor);
                        visited[neighbor] = true;
                        parent[neighbor] = currentNode;

                        if (neighbor == endNode) {
                            // Reconstruct the path
                            return reconstructPath(parent, endNode);
                        }
                    }
                }
            }

            return Collections.emptyList(); // No path found
        }

        private List<Integer> reconstructPath(int[] parent, int endNode) {
            List<Integer> path = new ArrayList<>();
            int currentNode = endNode;

            while (currentNode != -1) {
                path.add(currentNode);
                currentNode = parent[currentNode];
            }

            Collections.reverse(path);
            return path;
        }
    }

    public static void main(String[] args) {
        SocialGraph socialGraph = new SocialGraph(7);
        socialGraph.addFriendship(0, 1);
        socialGraph.addFriendship(0, 2);
        socialGraph.addFriendship(1, 3);
        socialGraph.addFriendship(1, 5);
        socialGraph.addFriendship(2, 3);
        socialGraph.addFriendship(3, 4);
        socialGraph.addFriendship(4, 5);
        socialGraph.addFriendship(4, 6);
        socialGraph.addFriendship(5, 6);

        int startNode = 0;
        int endNode = 6;
        List<Integer> shortestPath = socialGraph.shortestPath(startNode, endNode);

        System.out.println("Shortest path from node " + startNode + " to node " + endNode + ": " + shortestPath);
        //Shortest path from node 0 to node 6: [0, 1, 5, 6]
    }
}

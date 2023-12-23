package com.bomfim.interview;

import java.util.*;

public class DeepFirstSearch {

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

        public Set<Integer> findFriendGroup(int startUser, boolean[] visited) {
            Set<Integer> friendGroup = new LinkedHashSet<>();
            depthFirstSearch(startUser, visited, friendGroup);
            return friendGroup;
        }

        private void depthFirstSearch(int currentUser, boolean[] visited, Set<Integer> friendGroup) {
            visited[currentUser] = true;
            friendGroup.add(currentUser);

            for (int friend : adjacencyList.get(currentUser)) {
                if (!visited[friend]) {
                    depthFirstSearch(friend, visited, friendGroup);
                }
            }
        }
    }

    public static void main(String[] args) {
        SocialGraph socialGraph = new SocialGraph(8);
        socialGraph.addFriendship(0, 1);
        socialGraph.addFriendship(0, 2);
        socialGraph.addFriendship(1, 3);
        socialGraph.addFriendship(2, 3);
        socialGraph.addFriendship(4, 5);
        socialGraph.addFriendship(5, 6);

        int startUser = 1;
        boolean[] visited = new boolean[socialGraph.vertices];
        Set<Integer> friendGroup = socialGraph.findFriendGroup(startUser, visited);

        System.out.println("Friend Group for User " + startUser + ": " + friendGroup);
    }
}

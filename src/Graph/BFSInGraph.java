package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BFSInGraph {
    /**
     * Breadth-First Search (BFS) Algorithm:
     * Explore graph level by level using a queue.
     * 
     * Algorithm:
     * 1. Start from source node (0), add to queue and mark visited
     * 2. While queue not empty:
     *    - Dequeue node and add to result
     *    - For each unvisited neighbor: mark visited and enqueue
     * 3. Process nodes in order of distance from source
     * 
     * Applications: Shortest path in unweighted graph, level-order traversal
     * 
     * Time: O(V + E), Space: O(V) for queue and visited array
     */

    public List<Integer> bfs(List<List<Integer>> adList, int vertices) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertices];

        // Start BFS from node 0
        queue.offer(0);
        visited[0] = true; // mark starting node as visited

        while (!queue.isEmpty()) {
            // Dequeue front node
            Integer node = queue.poll();
            result.add(node); // add to result

            // Explore all neighbors of current node
            for (Integer neighbor : adList.get(node)) {
                // If neighbor not visited, mark and enqueue
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(4);
        adj.get(4).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(1).add(3);
        adj.get(3).add(1);

        System.out.println(new BFSInGraph().bfs(adj, 5));
    }
}
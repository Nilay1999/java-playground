package Graph;

import java.util.List;

/**
 * Keys and Rooms Algorithm (DFS - Graph Traversal):
 * Determine if you can visit all rooms in a building starting from room 0.
 * 
 * PROBLEM DESCRIPTION:
 * - n rooms numbered 0 to n-1, all initially locked except room 0
 * - Each room contains keys to other rooms
 * - Starting from room 0, determine if you can visit all rooms
 * 
 * ALGORITHM APPROACH:
 * 1. Use DFS to explore rooms starting from room 0
 * 2. Mark each visited room to avoid revisiting
 * 3. For each room, recursively visit all rooms whose keys are found
 * 4. After DFS completes, check if all rooms were visited
 * 5. If all rooms visited, return true; otherwise false
 * 
 * KEY INSIGHTS:
 * - This is a graph connectivity problem (rooms are nodes, keys are edges)
 * - DFS naturally explores all reachable nodes from starting point
 * - Visited array prevents infinite loops and redundant work
 * - If all n rooms are visited, they form one connected component
 * - Can also use BFS as alternative traversal method
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: rooms = [[1], [2], [3], []]
 * 
 * Start at room 0:
 * 1. Visit room 0, mark visited[0] = true
 * 2. Room 0 has key to room 1
 * 3. Visit room 1, mark visited[1] = true
 * 4. Room 1 has key to room 2
 * 5. Visit room 2, mark visited[2] = true
 * 6. Room 2 has key to room 3
 * 7. Visit room 3, mark visited[3] = true
 * 8. Room 3 has no keys
 * 9. Backtrack and finish DFS
 * 10. Check: all 4 rooms visited → return true
 * 
 * Example: rooms = [[1], [3], [3], []]
 * 
 * Start at room 0:
 * 1. Visit room 0, mark visited[0] = true
 * 2. Room 0 has key to room 1
 * 3. Visit room 1, mark visited[1] = true
 * 4. Room 1 has key to room 3
 * 5. Visit room 3, mark visited[3] = true
 * 6. Room 3 has no keys
 * 7. Backtrack and finish DFS
 * 8. Check: only 3 rooms visited (0,1,3), room 2 not visited → return false
 * 
 * Time: O(n + k) where n = rooms, k = total keys
 * Space: O(n) for visited array and recursion stack
 */
public class KeyAndRoom {
    public boolean canVisitAllRooms(List<List<Integer>> roomList) {
        int n = roomList.size();
        boolean[] visited = new boolean[n];
        dfs(roomList, visited, 0);
        for (boolean visit : visited) {
            if (!visit)
                return false;
        }

        return true;
    }

    private void dfs(List<List<Integer>> adjList, boolean[] visited, int node) {
        visited[node] = true;
        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                dfs(adjList, visited, neighbor);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> rooms = List.of(List.of(1), List.of(2), List.of(3), List.of());
        System.out.println(new KeyAndRoom().canVisitAllRooms(rooms));
    }
}

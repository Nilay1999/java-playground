package Graph;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {
    /**
     * Course Schedule Algorithm (Cycle Detection in Directed Graph):
     * Determine if all courses can be finished given prerequisites.
     * 
     * Key insight: If prerequisite graph has a cycle, impossible to finish all courses.
     * 
     * Cycle Detection using DFS:
     * - visited[]: node has been processed completely
     * - inRecursion[]: node is in current DFS path (recursion stack)
     * - If we reach a node already in recursion stack → cycle found
     * 
     * States: WHITE (unvisited) → GRAY (in recursion) → BLACK (visited)
     * 
     * Time: O(V + E), Space: O(V) for recursion and arrays
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build adjacency list for directed graph
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        // Add edges: prerequisite → course
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int pre = prerequisite[1];
            adjList.get(pre).add(course); // pre must be taken before course
        }

        // Track visited nodes and nodes in current recursion path
        boolean[] visited = new boolean[numCourses];
        boolean[] inRecursion = new boolean[numCourses];
        
        // Check each course for cycles
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && hasCycle(adjList, visited, inRecursion, i)) {
                return false; // cycle found, can't finish all courses
            }
        }

        return true; // no cycles, can finish all courses
    }

    // DFS to detect cycle in directed graph
    private boolean hasCycle(List<List<Integer>> adjList, boolean[] visited, boolean[] inRecursion, int node) {
        // If node is in current recursion path, cycle detected
        if (inRecursion[node]) return true;
        
        // If node already processed, no cycle from this node
        if (visited[node]) return false;

        // Mark node as visited and add to recursion path
        visited[node] = true;
        inRecursion[node] = true; // GRAY state (visiting)
        
        // Check all neighbors for cycles
        for (int neighbor : adjList.get(node)) {
            if (hasCycle(adjList, visited, inRecursion, neighbor)) {
                return true; // cycle found in neighbor's path
            }
        }
        
        // Remove from recursion path (backtrack)
        inRecursion[node] = false; // BLACK state (visited)
        return false; // no cycle found
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{0, 1}, {1, 0}};
        System.out.println(new CourseSchedule().canFinish(numCourses, prerequisites));
    }
}

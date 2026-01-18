package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Course Schedule II Algorithm (Topological Sort with Cycle Detection):
 * Find valid order to take all courses given prerequisites. Return empty if impossible.
 * 
 * GRAPH REPRESENTATION:
 * - Nodes: courses (0 to numCourses-1)
 * - Directed edges: prerequisite → course
 * - Goal: Find topological ordering (if no cycles exist)
 * 
 * DFS-BASED TOPOLOGICAL SORT:
 * 1. Build adjacency list from prerequisites
 * 2. Use DFS with 3-color approach for cycle detection:
 *    - White (0): unvisited
 *    - Gray (1): currently being processed (in recursion stack)
 *    - Black (2): completely processed
 * 3. If we encounter gray node during DFS: cycle detected
 * 4. Add nodes to stack in post-order (after processing all neighbors)
 * 5. Pop stack to get topological order
 * 
 * CYCLE DETECTION: If during DFS we reach a node that's currently being processed
 * (status = 1), we found a back edge indicating a cycle.
 * 
 * Example: courses=4, prerequisites=[[1,0],[2,0],[3,1],[3,2]]
 * Graph: 0→1→3, 0→2→3
 * Topological order: [0,1,2,3] or [0,2,1,3]
 * 
 * Time: O(V + E), Space: O(V + E)
 */
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int pre = prerequisite[1];
            adjList.get(pre).add(course);
        }

        boolean[] visited = new boolean[numCourses];
        int[] status = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (!dfs(stack, visited, adjList, status, i)) {
                    return new int[] {};
                }
            }
        }

        int[] res = new int[numCourses];
        int idx = 0;
        while (!stack.isEmpty()) {
            res[idx] = stack.pop();
            idx++;
        }

        return res;
    }

    private boolean dfs(Stack<Integer> stack, boolean[] visited, List<List<Integer>> adjList, int[] status, int node) {
        visited[node] = true;
        status[node] = 1;

        for (int i : adjList.get(node)) {
            if (status[i] == 1) {
                return false;
            }
            if (!visited[i] && !dfs(stack, visited, adjList, status, i)) {
                return false;
            }
        }
        status[node] = 2;

        stack.push(node);
        return true;
    }

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };

        int[] res = new CourseScheduleII().findOrder(numCourses, prerequisites);
        for (int i : res) {
            System.out.println(i);
        }
    }
}

package Graph;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int pre = prerequisite[1];
            adjList.get(pre).add(course);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] inRecursion = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && hasCycle(adjList, visited, inRecursion, i)) {
                return false;
            }
        }

        return true;
    }

    private boolean hasCycle(List<List<Integer>> adjList, boolean[] visited, boolean[] inRecursion, int node) {
        if (inRecursion[node]) return true;
        if (visited[node]) return false;

        visited[node] = true;
        inRecursion[node] = true; // mark as visiting
        for (int neighbor : adjList.get(node)) {
            if (hasCycle(adjList, visited, inRecursion, neighbor)) {
                return true;
            }
        }
        inRecursion[node] = false; // mark as visited
        return false;
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{0, 1}, {1, 0}};
        System.out.println(new CourseSchedule().canFinish(numCourses, prerequisites));
    }
}

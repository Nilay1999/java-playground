package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int pre = prerequisite[1];
            adjList.get(pre).add(course);
        }

        List<Integer> result = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[numCourses];
        queue.offer(0);

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            result.add(node);
            for (Integer level : adjList.get(node)) {
                if (!visited[level]) {
                    visited[level] = true;
                    queue.add(level);
                }

            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
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

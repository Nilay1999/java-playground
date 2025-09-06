package Graph;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < prerequisites.length; i++) {
            int u = prerequisites[i][0];
            int v = prerequisites[i][1];

            if (adjList.get(u) != null) {
                adjList.get(u)
                        .add(v);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = new int[2][2];
        System.out.println(new CourseSchedule().canFinish(numCourses, prerequisites));
    }
}

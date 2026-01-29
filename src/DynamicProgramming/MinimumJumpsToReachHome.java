package DynamicProgramming;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinimumJumpsToReachHome {

    public int minimumJumps(int[] forbidden, int a, int b, int x) {

        Set<Integer> forbid = new HashSet<>();
        int maxForbidden = 0;

        for (int f : forbidden) {
            forbid.add(f);
            maxForbidden = Math.max(maxForbidden, f);
        }

        int MAX = maxForbidden + a + b + x;

        boolean[][] visited = new boolean[MAX + 1][2];

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { 0, 0 });
        visited[0][0] = true;

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int pos = cur[0];
                int lastBack = cur[1];

                if (pos == x)
                    return steps;

                int forward = pos + a;
                if (forward <= MAX && !forbid.contains(forward) && !visited[forward][0]) {
                    visited[forward][0] = true;
                    q.offer(new int[] { forward, 0 });
                }

                if (lastBack == 0) {
                    int backward = pos - b;
                    if (backward >= 0 && !forbid.contains(backward) && !visited[backward][1]) {
                        visited[backward][1] = true;
                        q.offer(new int[] { backward, 1 });
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] forbidden = { 14, 4, 18, 1, 15 };
        int a = 3, b = 15, x = 9;

        System.out.println(new MinimumJumpsToReachHome().minimumJumps(forbidden, a, b, x));
    }
}

package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class ReachableNodesWithRestrictions {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<List<Integer>> adj = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        boolean[] visited = new boolean[n];
        for (int i : restricted) {
            set.add(i);
        }
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        queue.offer(0);
        visited[0] = true;
        int ans = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            ans++;
            for (int i : adj.get(current)) {
                if (!set.contains(i) && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
        return ans;
    }
}

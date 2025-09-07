package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BFSInGraph {

    public List<Integer> bfs(List<List<Integer>> adList, int vertices) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertices];

        queue.offer(0);

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            result.add(node);

            for (Integer level : adList.get(node)) {
                if (!visited[level]) {
                    visited[level] = true;
                    queue.add(level);
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
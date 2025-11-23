package Graph;

import java.util.List;

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

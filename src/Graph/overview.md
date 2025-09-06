# Graph Data Structure in Java

Graphs are one of the most important data structures in computer science. They are widely used in real-world applications such as social networks, maps, routing algorithms, recommendation systems, and many others.

---

## 1. What is a Graph?
A **Graph** is a collection of **nodes (vertices)** and **edges** that connect pairs of nodes.

- **Vertex (V):** A node in the graph.
- **Edge (E):** A connection (directed or undirected) between two vertices.

### Types of Graphs:
1. **Directed Graph (Digraph):** Edges have a direction.
2. **Undirected Graph:** Edges have no direction.
3. **Weighted Graph:** Each edge has a weight or cost.
4. **Unweighted Graph:** All edges are equal.
5. **Cyclic Graph:** Contains at least one cycle.
6. **Acyclic Graph:** No cycles.
7. **Connected Graph:** There is a path between every pair of vertices.
8. **Disconnected Graph:** Some vertices are not connected.

---

## 2. Representation of Graphs
Graphs can be represented in two common ways:

### (a) Adjacency Matrix
A 2D array where `matrix[i][j]` is 1 (or weight) if there is an edge from `i` to `j`.

```java
class GraphMatrix {
    private int[][] adjMatrix;
    private int vertices;

    public GraphMatrix(int vertices) {
        this.vertices = vertices;
        adjMatrix = new int[vertices][vertices];
    }

    public void addEdge(int src, int dest) {
        adjMatrix[src][dest] = 1;
        adjMatrix[dest][src] = 1; // For undirected graph
    }

    public void printMatrix() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
```

### (b) Adjacency List
Each vertex has a list of its neighbors.

```java
import java.util.*;

class GraphList {
    private List<List<Integer>> adjList;

    public GraphList(int vertices) {
        adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest) {
        adjList.get(src).add(dest);
        adjList.get(dest).add(src); // For undirected graph
    }

    public void printGraph() {
        for (int i = 0; i < adjList.size(); i++) {
            System.out.print(i + " -> ");
            for (Integer neighbor : adjList.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}
```

---

## 3. Graph Traversal Algorithms

### (a) Depth-First Search (DFS)
Recursive approach that explores as far as possible before backtracking.

```java
class DFSGraph {
    private List<List<Integer>> adjList;
    private boolean[] visited;

    public DFSGraph(int vertices) {
        adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
        visited = new boolean[vertices];
    }

    public void addEdge(int src, int dest) {
        adjList.get(src).add(dest);
        adjList.get(dest).add(src);
    }

    public void dfs(int vertex) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (int neighbor : adjList.get(vertex)) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}
```

### (b) Breadth-First Search (BFS)
Uses a queue to explore neighbors level by level.

```java
import java.util.*;

class BFSGraph {
    private List<List<Integer>> adjList;

    public BFSGraph(int vertices) {
        adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest) {
        adjList.get(src).add(dest);
        adjList.get(dest).add(src);
    }

    public void bfs(int start) {
        boolean[] visited = new boolean[adjList.size()];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");

            for (int neighbor : adjList.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}
```

---

## 4. Applications of Graphs
1. **Social Networks:** Represent users and their connections.
2. **Web Crawlers:** Traverse links between web pages.
3. **Maps and Routing:** Find shortest paths.
4. **Recommendation Systems:** Suggest friends/products.
5. **Dependency Graphs:** Task scheduling, build systems.

---

## 5. Practice Problems
1. Detect cycle in a graph (DFS/Union-Find).
2. Find shortest path (BFS for unweighted, Dijkstra for weighted).
3. Topological sorting (Kahn’s algorithm).
4. Connected components in an undirected graph.
5. Minimum spanning tree (Kruskal/Prim).

---

## 6. Conclusion
- Graphs can be implemented using adjacency list (space-efficient) or adjacency matrix (fast edge lookup).
- Traversals like DFS and BFS are fundamental to solving many problems.
- Understanding graph theory is key to cracking coding interviews and solving real-world problems.


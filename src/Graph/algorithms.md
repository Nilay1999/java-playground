# Graph Problem Solving Approaches & Algorithms

Graphs are widely used in competitive programming and software development. Solving graph problems effectively requires knowing common algorithms and techniques.

---

## 1. Problem-Solving Approach for Graphs

When solving a graph problem, follow this framework:

1. **Understand the Problem**
   - Is the graph directed or undirected?
   - Is it weighted or unweighted?
   - Can it contain cycles?
   - What is the size of input (vertices/edges)?

2. **Choose Representation**
   - **Adjacency List** → Space-efficient, preferred in most cases.
   - **Adjacency Matrix** → Useful for dense graphs.

3. **Choose Algorithm Type**
   - Traversal (DFS/BFS)
   - Shortest Path (Dijkstra/Bellman-Ford/Floyd-Warshall)
   - Minimum Spanning Tree (Prim/Kruskal)
   - Cycle Detection (DFS/Union-Find)
   - Topological Sort (DFS/Kahn’s Algorithm)

4. **Analyze Complexity**
   - Ensure algorithm fits time/memory limits.

---

## 2. Core Graph Techniques & Algorithms

### (a) Traversal
- **DFS (Depth First Search)** → Explore deeply before backtracking.
- **BFS (Breadth First Search)** → Explore neighbors level by level.
- **Applications:** Connected components, bipartite check, path existence.

---

### (b) Shortest Path

1. **Unweighted Graph:**
   - **BFS** → Finds shortest path in O(V + E).

2. **Weighted Graphs (non-negative weights):**
   - **Dijkstra’s Algorithm**
   ```java
   class Dijkstra {
       static class Edge {
           int to, weight;
           Edge(int t, int w) { to = t; weight = w; }
       }

       public int[] shortestPath(List<List<Edge>> graph, int src) {
           int n = graph.size();
           int[] dist = new int[n];
           Arrays.fill(dist, Integer.MAX_VALUE);
           dist[src] = 0;

           PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
           pq.offer(new int[]{src, 0});

           while (!pq.isEmpty()) {
               int[] curr = pq.poll();
               int u = curr[0];
               int d = curr[1];
               if (d > dist[u]) continue;

               for (Edge e : graph.get(u)) {
                   if (dist[u] + e.weight < dist[e.to]) {
                       dist[e.to] = dist[u] + e.weight;
                       pq.offer(new int[]{e.to, dist[e.to]});
                   }
               }
           }
           return dist;
       }
   }
   ```

3. **Graphs with negative weights:**
   - **Bellman-Ford Algorithm** → O(VE), detects negative cycles.

4. **All-pairs shortest paths:**
   - **Floyd-Warshall Algorithm** → O(V³).

---

### (c) Minimum Spanning Tree (MST)

1. **Prim’s Algorithm** → Greedy, expand MST by picking min edge.
2. **Kruskal’s Algorithm** → Sort edges and use Union-Find.

---

### (d) Cycle Detection

- **Undirected Graph:** DFS or Union-Find.
- **Directed Graph:** DFS with recursion stack.

---

### (e) Topological Sort (Directed Acyclic Graphs - DAGs)

1. **DFS-based approach** → Post-order traversal.
2. **Kahn’s Algorithm** → Use in-degree with BFS.

---

### (f) Strongly Connected Components (SCC)

1. **Kosaraju’s Algorithm** (DFS twice).
2. **Tarjan’s Algorithm** (low-link values).

---

### (g) Advanced Problems

1. **Bipartite Graph Check** → BFS/DFS with coloring.
2. **Disjoint Set Union (DSU/Union-Find)** → Useful for Kruskal’s MST, cycle detection.
3. **Maximum Flow / Minimum Cut** → Ford-Fulkerson, Edmonds-Karp.
4. **Bridges and Articulation Points** → DFS with discovery times & low values.

---

## 3. Common Problem Types & Matching Techniques

| Problem Type | Technique/Algorithm |
|--------------|----------------------|
| Path existence | DFS / BFS |
| Shortest path (unweighted) | BFS |
| Shortest path (weighted, non-negative) | Dijkstra |
| Shortest path (with negatives) | Bellman-Ford |
| All-pairs shortest path | Floyd-Warshall |
| Minimum Spanning Tree | Prim / Kruskal |
| Cycle detection (undirected) | DFS / Union-Find |
| Cycle detection (directed) | DFS recursion stack |
| Topological ordering | Kahn’s / DFS |
| Strongly connected components | Kosaraju / Tarjan |
| Network flow | Ford-Fulkerson / Edmonds-Karp |

---

## 4. Tips for Graph Problems

- Always confirm if graph is **0-indexed** or **1-indexed**.
- Watch out for **self-loops** and **parallel edges**.
- Choose adjacency list for large sparse graphs.
- Use priority queue (heap) for Dijkstra.
- Use DSU (Union-Find) for connectivity & cycle problems.
- Practice implementing both DFS and BFS until fluent.

---

## 5. Practice Problems

1. Detect cycle in directed/undirected graph.
2. Find shortest path using Dijkstra.
3. Find MST using Kruskal.
4. Topological sort of a DAG.
5. Number of connected components.
6. Bridges and articulation points.
7. Maximum flow problem.

---

## 6. Conclusion

Graph problems require mapping the problem statement to the correct algorithm. Mastery comes from recognizing patterns and practicing different problem types. Focus on BFS, DFS, Dijkstra, Union-Find, and Topological Sort first, then move on to advanced algorithms like SCC and Network Flow.


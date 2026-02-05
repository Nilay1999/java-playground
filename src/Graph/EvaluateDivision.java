package Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision {
    record Pair(String node, double value) {
    }

    private void dfs(
            Map<String, List<Pair>> adj,
            String src,
            String dst,
            Set<String> visited,
            double product,
            double[] ans) {
        if (visited.contains(src))
            return;

        visited.add(src);

        if (src.equals(dst)) {
            ans[0] = product;
            return;
        }

        for (Pair p : adj.getOrDefault(src, Collections.emptyList())) {
            dfs(adj, p.node(), dst, visited, product * p.value(), ans);
        }
    }

    public double[] calcEquation(
            List<List<String>> equations,
            double[] values,
            List<List<String>> queries) {

        Map<String, List<Pair>> adj = new HashMap<>();

        // Build graph
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double val = values[i];

            adj.computeIfAbsent(u, k -> new ArrayList<>())
                    .add(new Pair(v, val));

            adj.computeIfAbsent(v, k -> new ArrayList<>())
                    .add(new Pair(u, 1.0 / val));
        }

        double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String dst = queries.get(i).get(1);

            double[] ans = new double[] { -1.0 };

            if (adj.containsKey(src)) {
                Set<String> visited = new HashSet<>();
                dfs(adj, src, dst, visited, 1.0, ans);
            }

            result[i] = ans[0];
        }

        return result;
    }
}

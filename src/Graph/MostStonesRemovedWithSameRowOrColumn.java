package Graph;

class MostStonesRemovedWithSameRowOrColumn {
    int[] parent;
    int[] rank;

    public void unionSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int a, int b) {
        int findA = find(a);
        int findB = find(b);

        if (findA == findB) {
            parent[findA] = findB;
            findB++;
        } else if (rank[findA] > rank[findB]) {
            parent[findB] = findA;
        } else {
            parent[findA] = findB;
        }
    }

    public int removeStones(int[][] stones) {
        int n = stones.length;
        unionSet(n);
        for (int i = 0; i < n; i++) {
            int row = stones[i][0];
            int col = stones[i][1];

            int parent = find(i);
            for (int j = i + 1; j < n; j++) {
                int row2 = stones[j][0];
                int col2 = stones[j][1];

                if (row == row2 || col == col2) {
                    union(parent, j);
                }
            }
        }
        int group = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                group++;
            }
        }

        return n - group;
    }
}
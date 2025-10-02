package Graph.unionfind;

public class UnionFindPathCompression {
    private int[] parent;

    public UnionFindPathCompression(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }

    public static void main(String[] args) {
        int size = 5;
        UnionFindPathCompression ufc = new UnionFindPathCompression(size);
        ufc.union(1, 2);
        ufc.union(3, 4);
        boolean inSameSet = ufc.find(1) == ufc.find(2);
        System.out.println("Are 1 and 2 in the same set? " + inSameSet);
    }
}

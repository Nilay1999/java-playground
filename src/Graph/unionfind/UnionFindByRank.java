package Graph.unionfind;

import java.util.HashMap;
import java.util.Map;

public class UnionFindByRank {
    private final Map<Integer, Node> hash = new HashMap<>();

    class Node {
        Node parent;
        int rank;
        int value;
    }

    public void makeSet(int data) {
        Node node = new Node();
        node.value = data;
        node.parent = node;
        node.rank = 0;
        hash.put(data, node);
    }

    public int find(int i) {
        return findNode(hash.get(i)).value;
    }

    public Node findNode(Node node) {
        if (node.parent != node) {
            node.parent = findNode(node.parent);
        }
        return node.parent;
    }

    public boolean union(int a, int b) {
        Node parent1 = findNode(hash.get(a));
        Node parent2 = findNode(hash.get(b));

        if (parent1 == parent2)
            return false;

        if (parent1.rank < parent2.rank) {
            parent1.parent = parent2;
        } else if (parent1.rank > parent2.rank) {
            parent2.parent = parent1;
        } else {
            parent2.parent = parent1;
            parent1.rank++;
        }

        return true;
    }

    public static void main(String[] args) {
        UnionFindByRank ds = new UnionFindByRank();
        for (int i = 1; i < 8; i++) {
            ds.makeSet(i);
        }
        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        ds.union(3, 7);

        System.out.println(ds.find(1));
        System.out.println(ds.find(2));
        System.out.println(ds.find(3));
        System.out.println(ds.find(4));
        System.out.println(ds.find(5));
        System.out.println(ds.find(6));
        System.out.println(ds.find(7));
    }
}

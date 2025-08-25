package DynamicProgramming;

public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 5;
        }
        int total = 0;
        for (int i = 1; i <= n; i++) {
            total += numTrees(i - 1) * numTrees(n - i);
        }
        return total;
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println(new UniqueBinarySearchTrees().numTrees(n));
    }
}

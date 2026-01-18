package DynamicProgramming;

/**
 * Unique Binary Search Trees Algorithm (Catalan Numbers):
 * Count number of structurally unique BSTs with n nodes.
 * 
 * KEY INSIGHT: For n nodes, each node can be root
 * - If node i is root: left subtree has (i-1) nodes, right has (n-i) nodes
 * - Total = sum of (left_trees * right_trees) for all possible roots
 * 
 * CATALAN NUMBER FORMULA:
 * C(n) = sum(C(i-1) * C(n-i)) for i from 1 to n
 * C(0) = 1, C(1) = 1
 * 
 * ALGORITHM (Recursive with Memoization):
 * 1. Base cases: n=0 or n=1 → 1 tree
 * 2. For each possible root i (1 to n):
 *    - Left subtree: numTrees(i-1)
 *    - Right subtree: numTrees(n-i)
 *    - Contribution: left * right
 * 3. Sum all contributions
 * 
 * EXAMPLE: n=3
 * 
 * Root 1: left=0 nodes, right=2 nodes
 *   Left trees: 1, Right trees: 2
 *   Contribution: 1*2 = 2
 * 
 * Root 2: left=1 node, right=1 node
 *   Left trees: 1, Right trees: 1
 *   Contribution: 1*1 = 1
 * 
 * Root 3: left=2 nodes, right=0 nodes
 *   Left trees: 2, Right trees: 1
 *   Contribution: 2*1 = 2
 * 
 * Total: 2+1+2 = 5
 * 
 * The 5 unique BSTs with nodes [1,2,3]:
 *     1         1         2         3       3
 *      \         \       / \       /       /
 *       2         3     1   3     1       2
 *        \       /           \     \     /
 *         3     2             2     2   1
 * 
 * CATALAN SEQUENCE: 1, 1, 2, 5, 14, 42, 132, ...
 * 
 * Time: O(n²) with memoization, Space: O(n)
 */
public class UniqueBinarySearchTrees {
    /**
     * Naive recursive approach - recalculates same subproblems multiple times
     * Time: O(4^n / n^1.5) - exponential, Space: O(n) recursion stack
     */
    public int numTrees(int n) {
        // Base cases: 0 or 1 nodes → exactly 1 tree (empty or single node)
        if (n == 0 || n == 1) {
            return 1;
        }
        // Base case: 2 nodes → 2 unique BSTs
        if (n == 2) {
            return 2;
        }
        // Base case: 3 nodes → 5 unique BSTs (Catalan number C(3))
        if (n == 3) {
            return 5;
        }
        
        // For n nodes, try each node i as root
        int total = 0;
        for (int i = 1; i <= n; i++) {
            // Left subtree has (i-1) nodes, right subtree has (n-i) nodes
            // Multiply number of left trees by number of right trees
            // This gives all combinations with node i as root
            total += numTrees(i - 1) * numTrees(n - i);
        }
        return total;
    }

    /**
     * Optimized approach with memoization - stores computed results
     * Time: O(n²), Space: O(n)
     */
    public int memoization(Integer[] memo, int n) {
        // Base case: 0 or 1 nodes → 1 tree
        if (n <= 1) return 1;
        
        // Return cached result if already computed
        if (memo[n] != null) return memo[n];
        
        // Try each node i (1 to n) as root
        int total = 0;
        for(int i = 1; i <= n; i++) {
            // Left subtree: nodes 1 to (i-1) → count = memoization(memo, i-1)
            // Right subtree: nodes (i+1) to n → count = memoization(memo, n-i)
            // Multiply to get all valid BST combinations with i as root
            total += memoization(memo, i - 1) * memoization(memo, n - i);
        }
        
        // Store result in memo array before returning
        memo[n] = total;
        return total;
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println(new UniqueBinarySearchTrees().numTrees(n));
    }
}

package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate All Unique Binary Search Trees (Catalan Trees):
 * Build all structurally unique BSTs with values 1 to n.
 * 
 * KEY INSIGHT: For range [start, end], try each value i as root
 * - Left subtree: all BSTs from [start, i-1]
 * - Right subtree: all BSTs from [i+1, end]
 * - Combine each left tree with each right tree
 * 
 * ALGORITHM (Recursive Backtracking):
 * 1. Base case: start > end → return [null] (empty tree)
 * 2. Base case: start == end → return single node tree
 * 3. For each i in [start, end]:
 *    - Generate all left subtrees: dfs(start, i-1)
 *    - Generate all right subtrees: dfs(i+1, end)
 *    - Combine each left with each right, using i as root
 * 4. Return all generated trees
 * 
 * EXAMPLE: n=3, generate all BSTs with values [1,2,3]
 * 
 * When i=1 (root=1):
 *   Left: [] (empty), Right: all BSTs from [2,3]
 *   Result: 1 with right subtrees
 * 
 * When i=2 (root=2):
 *   Left: [1], Right: [3]
 *   Result: 2 with 1 on left, 3 on right
 * 
 * When i=3 (root=3):
 *   Left: all BSTs from [1,2], Right: [] (empty)
 *   Result: 3 with left subtrees
 * 
 * Time: O(Catalan(n) * n) - generates Catalan(n) trees, each takes O(n) to build
 * Space: O(Catalan(n)) - stores all generated trees
 */
public class UniqueBinarySearchTreesII {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Entry point: generate all unique BSTs with values 1 to n
     */
    public List<TreeNode> generateTrees(int n) {
        // Edge case: n=0 means no nodes, return empty list
        if (n == 0) {
            return new ArrayList<>();
        }
        // Start DFS with range [1, n]
        return dfs(1, n);
    }

    /**
     * DFS: Generate all unique BSTs for values in range [start, end]
     * 
     * @param start: beginning of value range (inclusive)
     * @param end: end of value range (inclusive)
     * @return: list of all unique BST roots for this range
     */
    public List<TreeNode> dfs(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        
        // Base case: invalid range (start > end) → empty tree
        // Add null to represent empty subtree (important for combining trees)
        if (start > end) {
            list.add(null);
            return list;
        }

        // Base case: single node range → create single node tree
        if (start == end) {
            TreeNode root = new TreeNode(start);
            list.add(root);
            return list;
        }

        // Recursive case: try each value i as root
        for (int i = start; i <= end; i++) {
            // Generate all possible left subtrees using values [start, i-1]
            List<TreeNode> left = dfs(start, i - 1);
            
            // Generate all possible right subtrees using values [i+1, end]
            List<TreeNode> right = dfs(i + 1, end);

            // Combine each left subtree with each right subtree
            // This creates all unique BSTs with i as root
            for (TreeNode lf : left) {
                for (TreeNode rf : right) {
                    // Create new root node with value i
                    TreeNode root = new TreeNode(i);
                    // Attach left subtree
                    root.left = lf;
                    // Attach right subtree
                    root.right = rf;
                    // Add this complete tree to results
                    list.add(root);
                }
            }
        }

        return list;
    }
}

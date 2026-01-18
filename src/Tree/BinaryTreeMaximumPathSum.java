package Tree;

import Tree.BinaryTreeBuilder.TreeNode;

/**
 * Binary Tree Maximum Path Sum Algorithm:
 * Find the maximum sum of any path in binary tree (path = sequence of connected nodes).
 * 
 * KEY INSIGHTS:
 * 1. Path can start and end at any nodes (not necessarily root to leaf)
 * 2. For each node, consider it as the "peak" of a path
 * 3. Path through a node = left_max + node.val + right_max
 * 4. But return value should be single branch (not both branches)
 * 
 * DFS ALGORITHM:
 * 1. For each node, calculate max path sum ending at that node
 * 2. Consider two cases:
 *    - Path through current node (left + node + right) → update global max
 *    - Path ending at current node (max(left, right) + node) → return to parent
 * 3. Use Math.max(0, childSum) to ignore negative paths
 * 
 * Example: Tree [1,2,3]
 *     1
 *    / \
 *   2   3
 * 
 * Paths: [1], [2], [3], [2,1], [1,3], [2,1,3]
 * Maximum: 2+1+3 = 6
 * 
 * Time: O(n), Space: O(h) where h is height
 */
public class BinaryTreeMaximumPathSum {
    int answer = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return answer;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftMax = Math.max(0, dfs(root.left));
        int rightMax = Math.max(0, dfs(root.right));
        answer = Math.max(leftMax + rightMax + root.val, answer);
        return Math.max(leftMax, rightMax) + root.val;
    }

    public static void main(String[] args) {
        Integer[] values = {3, 9, 10, -8, -20, null, null, -15, 7, 30};
        BinaryTreeBuilder.TreeNode tree = BinaryTreeBuilder.buildTree(values);
        System.out.println(new BinaryTreeMaximumPathSum().maxPathSum(tree));
    }
}

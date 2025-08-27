package Tree;

import Tree.BinaryTreeBuilder.TreeNode;

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

package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class CreateBinarySearchTrees {

    public static class TreeNode {
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

    public List<TreeNode> recursion(int start, int end) {
        List<TreeNode> result = new ArrayList<>();

        if (start > end) {
            result.add(null);
            return result;
        }

        if (start == end) {
            TreeNode root = new TreeNode(start);
            result.add(root);
            return result;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> left = recursion(start, i - 1);
            List<TreeNode> right = recursion(i, end);

            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    result.add(root);
                }
            }
        }
        return result;
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return recursion(1, n);
    }

    public static void main(String[] args) {
        int n = 5;
        CreateBinarySearchTrees bst = new CreateBinarySearchTrees();
        bst.generateTrees(n);
    }
}

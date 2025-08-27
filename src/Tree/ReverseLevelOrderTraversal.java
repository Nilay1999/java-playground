package Tree;

import Tree.BinaryTreeBuilder.TreeNode;

import java.util.*;

public class ReverseLevelOrderTraversal {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                TreeNode levelNode = queue.remove();

                level.add(levelNode.val);
                if (levelNode.left != null) {
                    queue.add(levelNode.left);
                }
                if (levelNode.right != null) {
                    queue.add(levelNode.right);
                }
            }
            result.add(level);
        }

        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        Integer[] values = {3, 9, 10, 8, 20, null, null, 15, 7, 30, 56};
        BinaryTreeBuilder.TreeNode tree = BinaryTreeBuilder.buildTree(values);
        System.out.println(new ReverseLevelOrderTraversal().levelOrderBottom(tree));
    }
}

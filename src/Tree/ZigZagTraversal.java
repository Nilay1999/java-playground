package Tree;

import java.util.*;

import Tree.BinaryTreeBuilder.TreeNode;

public class ZigZagTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<List<Integer>> res = new ArrayList<>();
        int direction = 1;
        while (!queue.isEmpty()) {
            int len = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                TreeNode curr = queue.remove();
                level.add(curr.val);
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            if (direction == 1) {
                res.add(level);
                direction = 0;
            } else {
                Collections.reverse(level);
                res.add(level);
                direction = 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Integer[] values = {3, 9, 10, 8, 20, null, null, 15, 7, 30, 56};
        TreeNode tree = BinaryTreeBuilder.buildTree(values);
        System.out.println(new ZigZagTraversal().zigzagLevelOrder(tree));
    }
}

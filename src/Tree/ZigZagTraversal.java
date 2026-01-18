package Tree;

import java.util.*;

import Tree.BinaryTreeBuilder.TreeNode;

/**
 * ZigZag Level Order Traversal Algorithm (BFS with Direction Toggle):
 * Traverse binary tree level by level, alternating direction (left-to-right, right-to-left).
 * 
 * ALGORITHM:
 * 1. Perform standard BFS level-order traversal
 * 2. For each level, toggle direction flag
 * 3. If direction = left-to-right: add level as-is
 * 4. If direction = right-to-left: reverse level before adding
 * 
 * LEVEL-ORDER TRAVERSAL:
 * - Use queue to process nodes level by level
 * - Track level size to know when level ends
 * - Add children to queue for next level
 * 
 * DIRECTION TOGGLE:
 * - Start with direction = 1 (left-to-right)
 * - After each level, toggle direction (1 → 0 or 0 → 1)
 * - Reverse level list when direction = 0 (right-to-left)
 * 
 * Example: Tree [3,9,10,8,20,null,null,15,7,30,56]
 *       3
 *      / \
 *     9   10
 *    /     \
 *   8      20
 *  / \    /  \
 * 15  7  30  56
 * 
 * Level 0 (L→R): [3]
 * Level 1 (R→L): [10, 9] (reversed from [9,10])
 * Level 2 (L→R): [8, 20]
 * Level 3 (R→L): [56, 30, 7, 15] (reversed from [15,7,30,56])
 * 
 * Time: O(n), Space: O(w) where w is maximum width
 */
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

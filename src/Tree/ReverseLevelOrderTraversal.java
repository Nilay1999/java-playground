package Tree;

import Tree.BinaryTreeBuilder.TreeNode;

import java.util.*;

/**
 * Reverse Level Order Traversal Algorithm (BFS):
 * Traverse binary tree level by level from bottom to top.
 * 
 * ALGORITHM:
 * 1. Perform standard level-order traversal (BFS) using queue
 * 2. Process nodes level by level, adding each level to result
 * 3. Reverse the entire result list at the end
 * 
 * LEVEL-ORDER TRAVERSAL:
 * - Start with root in queue
 * - For each level: process all nodes currently in queue
 * - Add children of current level to queue for next level
 * - Track level size to know when level ends
 * 
 * REVERSAL: Simply reverse the list of levels after BFS completes
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
 * Level-order: [[3], [9,10], [8,20], [15,7,30,56]]
 * Reversed: [[15,7,30,56], [8,20], [9,10], [3]]
 * 
 * Time: O(n), Space: O(w) where w is maximum width
 */
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

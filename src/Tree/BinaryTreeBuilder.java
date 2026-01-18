package Tree;

/**
 * Binary Tree Builder from Array (Level-Order Construction):
 * Build binary tree from array representation using BFS approach.
 * 
 * ARRAY REPRESENTATION:
 * - Index 0: root
 * - For node at index i:
 *   - Left child at index 2*i + 1
 *   - Right child at index 2*i + 2
 * - null values represent missing nodes
 * 
 * CONSTRUCTION ALGORITHM (BFS):
 * 1. Create root from first non-null element
 * 2. Use queue to process nodes level by level
 * 3. For each node, assign left and right children from array
 * 4. Add non-null children to queue for further processing
 * 5. Continue until all array elements processed
 * 
 * Example: [1,2,3,null,4,5,6]
 * Level 0: 1
 * Level 1: 2, 3
 * Level 2: null, 4, 5, 6
 * 
 * Tree structure:
 *       1
 *      / \
 *     2   3
 *      \ / \
 *      4 5  6
 * 
 * Time: O(n), Space: O(w) where w is maximum width of tree
 */
public class BinaryTreeBuilder {
    static class TreeNode {
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

    public static TreeNode buildTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        int i = 1;

        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.poll();

            if (i < values.length && values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.offer(current.left);
            }
            i++;

            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }
}

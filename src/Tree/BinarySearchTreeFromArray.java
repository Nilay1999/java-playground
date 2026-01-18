package Tree;

/**
 * Binary Search Tree Construction from Array:
 * Build BST by inserting array elements one by one.
 * 
 * BST PROPERTY: For any node:
 * - All left subtree values < node.val
 * - All right subtree values > node.val
 * - Both subtrees are also BSTs
 * 
 * INSERTION ALGORITHM (Recursive):
 * 1. If tree is empty, create new node as root
 * 2. If value < current node: insert in left subtree
 * 3. If value > current node: insert in right subtree
 * 4. Return updated root
 * 
 * TRAVERSAL - IN-ORDER:
 * Visit left → process node → visit right
 * For BST, in-order gives sorted sequence
 * 
 * Example: Insert [1,2,3,4,5,6,7]
 * Creates right-skewed tree (worst case):
 *     1
 *      \
 *       2
 *        \
 *         3...
 * 
 * PERFORMANCE:
 * - Best/Average: O(log n) per insertion
 * - Worst (skewed): O(n) per insertion
 * - Space: O(h) where h is height
 * 
 * NOTE: For balanced BST from sorted array, use divide-and-conquer approach
 */
public class BinarySearchTreeFromArray {
    TreeNode root;

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            left = right = null;
        }
    }

    public void insert(int val) {
        root = insertTreeNodeRecursively(root, val);
    }

    public TreeNode insertTreeNodeRecursively(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        } else if (val < root.val) {
            root.left = insertTreeNodeRecursively(root.left, val);
        } else {
            root.right = insertTreeNodeRecursively(root.right, val);
        }
        return root;
    }

    public void inOrderTraverse(TreeNode root) {
        if (root != null) {
            inOrderTraverse(root.left);
            System.out.println(root.val);
            inOrderTraverse(root.right);
        }
    }

    public BinarySearchTreeFromArray() {
        root = null;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        BinarySearchTreeFromArray bst = new BinarySearchTreeFromArray();
        for (int i : array) {
            bst.insert(i);
        }
        bst.inOrderTraverse(bst.root);
    }
}

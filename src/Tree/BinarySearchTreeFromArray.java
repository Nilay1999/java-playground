package Tree;

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

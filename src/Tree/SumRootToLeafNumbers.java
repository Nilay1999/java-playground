package Tree;

import java.util.ArrayList;
import java.util.List;

import Tree.BinaryTreeBuilder.TreeNode;

/**
 * Sum Root to Leaf Numbers Algorithm (DFS):
 * Calculate sum of all numbers formed by root-to-leaf paths.
 * Each path represents a number (e.g., path 1→2→3 = 123).
 * 
 * DFS APPROACH:
 * 1. Traverse from root to each leaf, building number as string
 * 2. At each node, append its digit to current number
 * 3. When reaching leaf (both children null), add number to list
 * 4. Sum all collected numbers
 * 
 * ALGORITHM:
 * 1. Start DFS with empty string from root
 * 2. For each node: append its value to current number string
 * 3. Recursively visit left and right children
 * 4. When node is null: convert accumulated string to integer and add to list
 * 5. Sum all values in list
 * 
 * KEY INSIGHT: Build number as string during traversal, convert to int at leaf
 * 
 * Example: Tree [1,2,3]
 *     1
 *    / \
 *   2   3
 * 
 * Paths: 1→2 (12), 1→3 (13)
 * Sum: 12 + 13 = 25
 * 
 * OPTIMIZATION: Can calculate sum directly without storing all numbers
 * sum = sum * 10 + node.val at each step
 * 
 * Time: O(n), Space: O(h) for recursion stack
 */
public class SumRootToLeafNumbers {
    public List<Integer> list;

    public void dfs(String num, TreeNode root) {
        if (root != null) {
            num += (String.valueOf(Character.forDigit(root.val, 10)));
            dfs(num, root.left);
            dfs(num, root.right);
        }
        list.add(Integer.valueOf(num));
    }

    public int sumNumbers(TreeNode root) {
        list = new ArrayList<>();
        dfs("", root);
        int ans = 0;
        for (int i : list) {
            ans += i;
        }
        return ans;
    }
}

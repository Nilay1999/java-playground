package Tree;

import Tree.BinaryTreeBuilder.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * House Robber III Algorithm (Tree DP with Memoization):
 * Rob houses arranged in binary tree. Cannot rob directly connected houses.
 * 
 * PROBLEM: For each node, decide whether to rob it or not.
 * - If rob current node: cannot rob its children, but can rob grandchildren
 * - If skip current node: can rob its children
 * 
 * RECURSIVE APPROACH WITH MEMOIZATION:
 * For each node, calculate:
 * 1. Rob current: node.val + sum(grandchildren)
 * 2. Skip current: sum(children)
 * 3. Return max of both options
 * 
 * MEMOIZATION: Store results to avoid recalculating same subtrees
 * 
 * Example: Tree [3,2,3,null,3,null,1]
 *       3
 *      / \
 *     2   3
 *      \   \
 *       3   1
 * 
 * Optimal: Rob nodes 3,3,1 = 7 (skip root 3, rob children's children)
 * 
 * ALTERNATIVE: Return pair [rob_current, skip_current] for each node
 * 
 * Time: O(n), Space: O(n) for memoization + O(h) for recursion
 */
public class HouseRobberIII {
    public int rob(TreeNode root) {
        return dfs(root, new HashMap<>());
    }

    private int dfs(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)) return map.get(root);
        int answer = 0;
        if (root.left != null) {
            answer += dfs(root.left.left, map) + dfs(root.left.right, map);
        }
        if (root.right != null) {
            answer += dfs(root.right.left, map) + dfs(root.right.right, map);
        }
        answer = Math.max(root.val + answer, dfs(root.left, map) + dfs(root.right, map));
        map.put(root, answer);
        return answer;
    }

    public static void main(String[] args) {
        Integer[] arr = {3, 2, 3, null, 3, null, 1};
        TreeNode root = BinaryTreeBuilder.buildTree(arr);
        System.out.println(new HouseRobberIII().rob(root));
    }
}

package Tree;

import Tree.BinaryTreeBuilder.TreeNode;

import java.util.HashMap;
import java.util.Map;

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

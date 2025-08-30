package Tree;

import java.util.ArrayList;
import java.util.List;

import Tree.BinaryTreeBuilder.TreeNode;

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

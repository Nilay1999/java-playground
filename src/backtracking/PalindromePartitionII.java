package backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitionII {
    private static List<List<String>> result;

    private static boolean isPalindrome(String st, int l, int r) {
        while (l < r) {
            if (st.charAt(l) != st.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    private static void recursion(int idx, List<String> temp, String str) {
        System.out.println(idx);
        if (idx == str.length()) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = idx; i < str.length(); i++) {
            if (isPalindrome(str, idx, i)) {
                temp.add(str.substring(idx, i + 1));
                recursion(i + 1, temp, str);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static List<List<String>> partition(String s) {
        List<String> arr = new ArrayList<>();
        result = new ArrayList<>();
        recursion(0, arr, s);
        return result;
    }

    public static void main(String[] arg) {
        String s = "aab";
        List<List<String>> ans = partition(s);
        System.out.println(ans);
    }
}

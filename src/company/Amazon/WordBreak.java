package company.Amazon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreak {
    private Map<String, Boolean> dp;

    public boolean wordBreak(String s, List<String> wordDict) {
        dp = new HashMap<>();
        return dfs(s, new HashSet<>(wordDict));
    }

    public boolean dfs(String s, Set<String> wordDict) {
        if (s.length() == 0) return true;

        if (dp.containsKey(s)) return true;

        for (int i=1; i<s.length(); i++) {
            String substr = s.substring(0, i+1);
            if (wordDict.contains(substr)) {
                if (dfs(s.substring(i+1), wordDict)) {
                    dp.put(s, true);
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = List.of("leet","code");

        System.out.println(new WordBreak().wordBreak(s, wordDict));
    }
}

package company.Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hash = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);

            if (hash.containsKey(sorted)) {
                List<String> key = hash.get(sorted);
                key.add(s);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                hash.put(sorted, list);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : hash.entrySet()) {
            ans.add(entry.getValue());
        }

        return ans;
    }

    public static void main(String[] args) {
        String[] arr = { "eat", "tea", "tan", "ate", "nat", "bat" };
        System.out.println(new GroupAnagrams().groupAnagrams(arr));
    }
}

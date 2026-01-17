package company.Amazon;

import java.util.Arrays;

public class LongestHappyPrefix {
    public String longestPrefix(String s) {
        int n = s.length();
        String[] prefix = new String[n];
        String[] suffix = new String[n];

        for (int i = 1; i < n; i++) {
            prefix[i] = s.substring(0, i);
        }

        for (int i = 1; i < n; i++) {
            suffix[i] = s.substring(i);
        }

        System.out.println(Arrays.toString(suffix));
        System.out.println(Arrays.toString(prefix));

        return "";
    }

    public static void main(String[] args) {
        System.out.println(new LongestHappyPrefix().longestPrefix("level"));
    }
}

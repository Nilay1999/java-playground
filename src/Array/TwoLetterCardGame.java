package Array;

import java.util.Arrays;
import java.util.Comparator;

public class TwoLetterCardGame {
    /**
     * Two Letter Card Game Scoring Algorithm:
     * 1. Sort cards by first character for easier processing
     * 2. Check consecutive pairs that both contain character x
     * 3. Score +1 if cards differ in exactly one position (XOR logic)
     * 4. Skip both cards when scoring to avoid reuse
     * 
     * Example: cards=["aa","ab","ba","ac"], x='a'
     * After sort: ["aa","ab","ac","ba"]
     * Pairs: (aa,ab) - differ in pos 1 only → +1 point
     * 
     * Time: O(n log n) for sorting, Space: O(1)
     */
    public int score(String[] cards, char x) {
        Arrays.sort(cards, Comparator.comparing(s -> s.charAt(0)));
        int n = cards.length;
        int ans = 0;
        int i = 0;
        while (i < n - 1) {
            if (cards[i].contains(String.valueOf(x)) && cards[i + 1].contains(String.valueOf(x))) {
                boolean diff1 = cards[i].charAt(0) == cards[i + 1].charAt(0);
                boolean diff2 = cards[i].charAt(1) == cards[i + 1].charAt(1);

                if (diff1 ^ diff2) {
                    ans += 1;
                    i += 1;
                }
            }
            i++;
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] cards = { "aa", "ab", "ba", "ac" };
        char x = 'a';
        System.out.println(new TwoLetterCardGame().score(cards, x));
    }
}

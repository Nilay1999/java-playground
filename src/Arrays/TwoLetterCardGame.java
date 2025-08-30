package Arrays;

import java.util.Arrays;
import java.util.Comparator;

public class TwoLetterCardGame {
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
        String[] cards = {"aa", "ab", "ba", "ac"};
        char x = 'a';
        System.out.println(new TwoLetterCardGame().score(cards, x));
    }
}

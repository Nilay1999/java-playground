package company.Amazon;

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int size = s.length();
        int[] frq = new int[26];
        int max = 0;
        int left = 0;
        int result = 0;
        for (int i = 0; i < size; i++) {
            int characterKey = s.charAt(i) - 'A';
            frq[characterKey]++;
            max = Math.max(frq[characterKey], max);

            // This is Window decrease formula, when Substring length is having more
            // characters to be replaced,
            // then we shrink the window
            // i.e AABABBA
            // l->0 r->4 (r - left + 1) - maxFrq (A:3) > k -> (5-3 > 1 True) -> increase
            // left to decrease window size
            while (((i - left + 1) - max) > k) {
                frq[s.charAt(left) - 'A']--;
                left++;
            }

            result = Math.max(result, i - left + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;

        System.out.println(new LongestRepeatingCharacterReplacement().characterReplacement(s, k));
    }
}

/**
 * Break Palindrome Algorithm (Greedy):
 * Replace exactly one character to make lexicographically smallest non-palindrome.
 * 
 * GREEDY STRATEGY:
 * 1. To get lexicographically smallest: replace leftmost non-'a' with 'a'
 * 2. If all characters are 'a': change last character to 'b'
 * 3. Only check first half (due to palindrome symmetry)
 * 
 * ALGORITHM:
 * 1. Iterate through first half of string (0 to n/2-1)
 * 2. Find first character that's not 'a'
 * 3. Replace it with 'a' and return
 * 4. If no such character found (all 'a's), replace last char with 'b'
 * 
 * WHY THIS WORKS:
 * - Changing first half automatically breaks palindrome property
 * - 'a' is lexicographically smallest, so we prefer it
 * - If string is "aaa...a", only option is to change last char
 * 
 * Example: "abccba"
 * First non-'a' at index 1 ('b') → change to 'a' → "aaccba"
 * 
 * Example: "aaaa"  
 * All 'a's → change last to 'b' → "aaab"
 * 
 * Time: O(n), Space: O(n) for char array
 */
public class BreakPalindrome {
    public static void main(String[] args) {
        String palindrome = "abccba";
        System.out.println(new BreakPalindrome().breakPalindrome(palindrome));
    }

    public String breakPalindrome(String str) {
        int n = str.length();
        char[] character = str.toCharArray();

        for (int i = 0; i < n / 2; i++) {
            if (character[i] != 'a') {
                character[i] = 'a';
                break;
            }
        }

        if (character[n - 1] == 'a') {
            character[n - 1] = 'b';
        }

        return new String(character);
    }
}

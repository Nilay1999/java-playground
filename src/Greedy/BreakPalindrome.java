package Greedy;

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

package Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Restore IP Addresses Algorithm (Backtracking):
 * Generate all valid IP addresses from a string of digits.
 * 
 * PROBLEM CONSTRAINTS:
 * - IP address has exactly 4 parts separated by dots
 * - Each part is 0-255
 * - No leading zeros (except "0" itself)
 * - Use all characters in string
 * 
 * BACKTRACKING STRATEGY:
 * 1. Try partitioning string into 4 parts
 * 2. Each part can be 1-3 digits long
 * 3. Validate each part: 0-255, no leading zeros
 * 4. Base case: 4 parts formed AND all characters used
 * 5. Backtrack if invalid or can't form 4 parts
 * 
 * VALIDATION:
 * - Leading zero: "01" is invalid, "0" is valid
 * - Range: must be 0-255
 * 
 * Example: "25525511135" → ["255.255.11.135", "255.255.111.35"]
 * 
 * Time: O(3^4) = O(81) - at most 3 choices per part, 4 parts
 * Space: O(4) for recursion depth
 */
class RestoreIpAddress {
    private static List<String> res;

    // Helper: Check if substring is valid IP part
    private static boolean isValidIP(String subStr) {
        // No leading zeros (except "0" itself)
        if (subStr.length() > 1 && subStr.charAt(0) == '0') {
            return false;
        }
        // Check range 0-255
        int num = Integer.parseInt(subStr);
        return num >= 0 && num <= 255;
    }

    private static void backtrack(String ip, List<String> temp, int idx) {
        // Base case: formed 4 parts and used all characters
        if (temp.size() == 4 && idx == ip.length()) {
            res.add(String.join(".", temp)); // join with dots
            return;
        }

        // Try each possible part length (1-3 digits)
        for (int i = 1; i <= 3; i++) {
            // Check if we have enough characters left
            if (idx + i > ip.length()) break;
            
            // Extract substring for current part
            String sub = ip.substring(idx, idx + i);
            
            // If valid IP part, include it
            if (isValidIP(sub)) {
                temp.add(sub);
                // Recurse for next part
                backtrack(ip, temp, idx + i);
                // Backtrack: remove last part
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static void restoreIpAddresses(String str) {
        // Early termination: IP can't be longer than 12 digits (255.255.255.255)
        if (str.length() > 12) {
            return;
        }
        res = new ArrayList<>();
        backtrack(str, new ArrayList<>(), 0);
    }

    public static void main(String[] args) {
        String ipString = "25525511135";
        RestoreIpAddress.restoreIpAddresses(ipString);
    }
}

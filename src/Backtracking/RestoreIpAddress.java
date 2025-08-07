package Backtracking;

import java.util.ArrayList;
import java.util.List;

class RestoreIpAddress {
    private static List<String> res;

    private static boolean isValidIP(String subStr) {
        if (subStr.length() > 1 && subStr.charAt(0) == '0') {
            return false;
        }
        int num = Integer.parseInt(subStr);
        return num >= 0 && num <= 255;
    }

    private static void backtrack(String ip, List<String> temp, int idx) {
        if (temp.size() == 4 && idx == ip.length()) {
            res.add(String.join(".", temp));
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (idx + i > ip.length()) break;
            String sub = ip.substring(idx, idx + i);
            if (isValidIP(sub)) {
                temp.add(sub);
                backtrack(ip, temp, idx + i);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static void restoreIpAddresses(String str) {
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

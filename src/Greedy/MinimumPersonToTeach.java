package Greedy;

import java.util.HashSet;
import java.util.Set;

public class MinimumPersonToTeach {
    public static void main(String[] args) {
        int n = 3;
        int[][] languages = {{2}, {1, 3}, {1, 2}, {3}};
        int[][] friendships = {{1, 4}, {1, 2}, {3, 4}, {2, 3}};

        System.out.println(new MinimumPersonToTeach().minimumTeachings(n, languages, friendships));
    }

    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Set<Integer> sadUser = new HashSet<>();

        for (int[] group : friendships) {
            int f1 = group[0] - 1;
            int f2 = group[1] - 1;
            Set<Integer> set = new HashSet<>();

            for (int i = 0; i < languages[f1].length; i++) {
                set.add(languages[f1][i]);
            }
            boolean canTalk = false;
            for (int i = 0; i < languages[f2].length; i++) {
                if (set.contains(languages[f2][i])) {
                    canTalk = true;
                    break;
                }
            }

            if (!canTalk) {
                sadUser.add(group[0]);
                sadUser.add(group[1]);
            }
        }

        int[] langs = new int[n + 1];
        int max = 0;
        for (int u : sadUser) {
            for (int l : languages[u - 1]) {
                langs[l]++;
                max = Math.max(max, langs[l]);
            }
        }

        return sadUser.size() - max;
    }
}

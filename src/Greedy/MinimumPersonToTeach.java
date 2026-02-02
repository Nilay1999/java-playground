package Greedy;

/**
 * Minimum Person To Teach Algorithm (Greedy with Set):
 * Find minimum number of people to teach a language so all friends can communicate.
 * 
 * PROBLEM DESCRIPTION:
 * - n people, each knows some languages
 * - Friendships exist between pairs of people
 * - Two friends can communicate if they share a common language
 * - Find minimum people to teach a language to make all friends able to communicate
 * 
 * ALGORITHM APPROACH:
 * 1. First pass: identify "sad users" (friends who can't communicate)
 * 2. For each friendship, check if they share a language
 * 3. If not, add both to sad users set
 * 4. Second pass: count language frequencies among sad users
 * 5. Return: sad_users_count - max_language_frequency
 * 
 * KEY INSIGHTS:
 * - Only need to teach sad users (those with no common language with friends)
 * - Teaching one language to max_frequency sad users connects all of them
 * - Greedy: pick language known by most sad users
 * - If we teach that language to remaining sad users, all can communicate
 * - Formula: min_teach = sad_users - max_frequency
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: n=3, languages=[[2],[1,3],[1,2],[3]], friendships=[[1,4],[1,2],[3,4],[2,3]]
 * (1-indexed: person 1 knows lang 2, person 2 knows langs 1,3, etc.)
 * 
 * Check friendships:
 * [1,4]: person 1 knows {2}, person 4 knows {3} → no common → sad: {1,4}
 * [1,2]: person 1 knows {2}, person 2 knows {1,3} → no common → sad: {1,2,4}
 * [3,4]: person 3 knows {1,2}, person 4 knows {3} → no common → sad: {1,2,3,4}
 * [2,3]: person 2 knows {1,3}, person 3 knows {1,2} → common: 1 → no change
 * 
 * Sad users: {1,2,3,4}
 * Language frequencies among sad users:
 * Lang 1: persons 2,3 → count = 2
 * Lang 2: persons 1,3 → count = 2
 * Lang 3: persons 2,4 → count = 2
 * 
 * Max frequency = 2
 * Min teach = 4 - 2 = 2
 * 
 * Time: O(f * l) where f = friendships, l = avg languages per person
 * Space: O(n * l) for language sets
 */
import java.util.HashSet;
import java.util.Set;

public class MinimumPersonToTeach {
    public static void main(String[] args) {
        int n = 3;
        int[][] languages = { { 2 }, { 1, 3 }, { 1, 2 }, { 3 } };
        int[][] friendships = { { 1, 4 }, { 1, 2 }, { 3, 4 }, { 2, 3 } };

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

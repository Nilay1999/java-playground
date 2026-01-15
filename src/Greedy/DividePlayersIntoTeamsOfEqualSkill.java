import java.util.Arrays;

public class DividePlayersIntoTeamsOfEqualSkill {
    public static void main(String[] args) {
        int[] skill = { 1, 1, 2, 3 };
        // 1 2 3 3 4 5
        System.out.println(new DividePlayersIntoTeamsOfEqualSkill().dividePlayers(skill));
    }

    public long dividePlayers(int[] skill) {
        int n = skill.length;
        if (n == 2) {
            return (long) skill[0] * skill[1];
        }
        Arrays.sort(skill);

        int left = 0;
        int right = n - 1;

        long skillSum = 0;
        int lastPairSum = skill[0] + skill[n - 1];
        while (left < right) {
            int power = skill[left] + skill[right];
            long product = (long) skill[left] * skill[right];
            if (lastPairSum != power) {
                return -1;
            }
            skillSum += product;
            left++;
            right--;
        }

        return skillSum;
    }
}

package company.Amazon;

import java.util.Arrays;

public class Candy {
    public int candy(int[] ratings) {
        int size = ratings.length;
        int[] candy = new int[size];
        Arrays.fill(candy, 1);

        for (int i = 1; i < size; i++) {
            if (ratings[i - 1] < ratings[i]) {
                candy[i] = candy[i - 1] + 1;
            }
        }

        for (int i = size - 2; i >= 0; i--) {
            if (ratings[i + 1] < ratings[i]) {
                candy[i] = Math.max(candy[i + 1] + 1, candy[i]);
            }
        }

        int ans = 0;
        for (int i : candy) {
            ans += i;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Candy().candy(new int[] { 1, 2, 2 }));
    }
}

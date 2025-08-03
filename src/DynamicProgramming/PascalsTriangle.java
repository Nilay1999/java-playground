package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> temp = new ArrayList<>();

            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    temp.add(1);
                } else {
                    int val = res.get(i - 1).get(j) + res.get(i - 1).get(j - 1);
                    temp.add(val);
                }
            }

            res.add(temp);
        }

        return res;
    }

    public static void main(String[] args) {
        int numRows = 5;
        List<List<Integer>> triangle = generate(numRows);

        for (List<Integer> row : triangle) {
            System.out.println(row);
        }
    }
}

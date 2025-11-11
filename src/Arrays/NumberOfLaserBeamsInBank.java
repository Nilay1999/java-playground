package Arrays;

import java.util.ArrayList;
import java.util.List;

public class NumberOfLaserBeamsInBank {
    public int numberOfBeams(String[] bank) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < bank.length; i++) {
            char[] line = bank[i].toCharArray();
            int laser = 0;
            for (char c : line) {
                if (c == '1')
                    laser++;
            }
            if (laser > 0)
                list.add(laser);
        }
        int total = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            if (i + 1 < list.size())
                total += list.get(i) * list.get(i + 1);
        }

        return total;
    }

    public static void main(String[] args) {
        String[] back = { "011001", "001000", "010100", "001001" };
        System.out.println(new NumberOfLaserBeamsInBank().numberOfBeams(back));
    }
}

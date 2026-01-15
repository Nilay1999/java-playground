package Stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ExclusiveTimeOfFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        int prev = 0;
        for (String log : logs) {
            String[] parts = log.split(":");
            int id = Integer.parseInt(parts[0]);
            int time = Integer.parseInt(parts[2]);
            String type = parts[1];

            if (type.equals("start")) {
                if (!stack.isEmpty()) {
                    result[stack.peek()] += time - prev;
                }
                stack.push(id);
                prev = time;
            } else {
                result[stack.pop()] += time - prev + 1;
                prev = time + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] ans = new ExclusiveTimeOfFunctions().exclusiveTime(2,
                List.of("0:start:0", "1:start:2", "1:end:5", "0:end:6"));
        System.out.println(Arrays.toString(ans));
    }
}

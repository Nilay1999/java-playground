package Stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Exclusive Time of Functions Algorithm (Stack - Function Call Tracking):
 * Calculate exclusive execution time of each function from logs.
 * 
 * PROBLEM DESCRIPTION:
 * - n functions with start/end timestamps in logs
 * - Exclusive time = time spent in function (not in called functions)
 * - Logs format: "id:type:timestamp" where type is "start" or "end"
 * - Calculate exclusive time for each function
 * 
 * ALGORITHM APPROACH:
 * 1. Use stack to track function call stack
 * 2. For each log entry:
 *    - If "start": add accumulated time to current function, push new function
 *    - If "end": add execution time to function, pop from stack
 * 3. Track previous timestamp to calculate time intervals
 * 4. When function starts, credit accumulated time to previous function
 * 5. When function ends, credit execution time to current function
 * 
 * KEY INSIGHTS:
 * - Stack maintains call stack (nested function calls)
 * - When new function starts, previous function pauses
 * - Time between events belongs to currently executing function
 * - Exclusive time = total time - time in called functions
 * - prev variable tracks last timestamp for interval calculation
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: n=2, logs=["0:start:0","1:start:2","1:end:5","0:end:6"]
 * 
 * Timeline:
 * 0:start:0 - Function 0 starts at time 0
 * 1:start:2 - Function 1 starts at time 2 (Function 0 pauses)
 * 1:end:5   - Function 1 ends at time 5 (Function 0 resumes)
 * 0:end:6   - Function 0 ends at time 6
 * 
 * Processing:
 * 1. "0:start:0": stack empty, push 0, prev=0
 * 2. "1:start:2": 
 *    - result[0] += 2-0 = 2 (function 0 ran for 2 units)
 *    - push 1, prev=2
 * 3. "1:end:5":
 *    - result[1] += 5-2+1 = 4 (function 1 ran for 4 units: 2,3,4,5)
 *    - pop 1, prev=6
 * 4. "0:end:6":
 *    - result[0] += 6-6+1 = 1 (function 0 ran for 1 unit: 6)
 *    - pop 0
 * 
 * Result: [3, 4]
 * Function 0: 2 (0-2) + 1 (5-6) = 3
 * Function 1: 4 (2-5) = 4
 * 
 * KEY DETAIL: End time is inclusive, so add 1 to duration
 * 
 * Time: O(n) where n = number of logs
 * Space: O(n) for stack in worst case
 */
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

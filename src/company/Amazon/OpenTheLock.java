package company.Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class OpenTheLock {
    public int openLock(String[] deadends, String target) {
        Set<String> ends = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();

        if (ends.contains(target) || ends.contains("0000")) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        visited.add("0000");

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (current.equals(target)) {
                    return steps;
                }

                for (String next : getNextStates(current)) {
                    if (!ends.contains(next) && !visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    private List<String> getNextStates(String state) {
        List<String> list = new ArrayList<>();
        char[] chars = state.toCharArray();

        for (int i = 0; i < 4; i++) {
            char org = chars[i];

            // move forward
            chars[i] = org == '9' ? '0' : (char) (org + 1);
            list.add(new String(chars));

            // move backward
            chars[i] = org == '0' ? '9' : (char) (org - 1);
            list.add(new String(chars));

            // restore
            chars[i] = org;
        }

        return list;
    }
}
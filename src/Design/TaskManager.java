package Design;

import java.util.*;

class TaskManager {
    private Map<Integer, int[]> taskMap;
    private PriorityQueue<int[]> maxHeap;

    public TaskManager(List<List<Integer>> tasks) {
        taskMap = new HashMap<>();
        maxHeap = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0];
            } else {
                return b[1] - a[1];
            }
        });

        for (List<Integer> task : tasks) {
            int userId = task.get(0);
            int taskId = task.get(1);
            int priority = task.get(2);
            add(userId, taskId, priority);
        }
    }

    public void add(int userId, int taskId, int priority) {
        taskMap.put(taskId, new int[] { userId, priority });
        maxHeap.offer(new int[] { priority, taskId });
    }

    public void edit(int taskId, int newPriority) {
        if (taskMap.containsKey(taskId)) {
            int[] taskInfo = taskMap.get(taskId);
            int userId = taskInfo[0];
            taskMap.put(taskId, new int[] { userId, newPriority });
            maxHeap.offer(new int[] { newPriority, taskId });
        }
    }

    public void rmv(int taskId) {
        taskMap.remove(taskId);
    }

    public int execTop() {
        while (!maxHeap.isEmpty()) {
            int[] task = maxHeap.poll();
            int priority = task[0];
            int taskId = task[1];
            if (taskMap.containsKey(taskId)) {
                int[] taskInfo = taskMap.get(taskId);
                if (taskInfo[1] == priority) {
                    taskMap.remove(taskId);
                    return taskInfo[0];
                }
            }
        }
        return -1;
    }
}
package Design;

import java.util.*;

/**
 * Task Manager System Design:
 * Manage tasks with priorities, support add/edit/remove/execute operations.
 * 
 * DATA STRUCTURES:
 * 1. taskMap: Map<taskId, [userId, priority]>
 *    - Track current state of each task
 * 2. maxHeap: PriorityQueue<[priority, taskId]>
 *    - Max heap by priority (then by taskId)
 *    - Allows lazy deletion
 * 
 * ALGORITHM:
 * 
 * add(userId, taskId, priority):
 * - Store task info in taskMap
 * - Add to max heap
 * 
 * edit(taskId, newPriority):
 * - Update priority in taskMap
 * - Add new entry to heap (don't remove old)
 * - Lazy deletion: old entries become invalid
 * 
 * rmv(taskId):
 * - Remove from taskMap
 * - Don't remove from heap (lazy deletion)
 * 
 * execTop():
 * - Poll from heap until finding valid task
 * - Valid = task exists in taskMap with matching priority
 * - Remove from taskMap and return userId
 * - Skip invalid entries (removed or priority changed)
 * 
 * LAZY DELETION OPTIMIZATION:
 * - Don't immediately remove from heap
 * - When executing, validate against taskMap
 * - Reduces deletion overhead from O(n) to O(1)
 * 
 * COMPARATOR:
 * - Primary: higher priority first (descending)
 * - Secondary: higher taskId first (descending)
 * 
 * Example:
 * add(1, 1, 5): taskMap={1:[1,5]}, heap=[[5,1]]
 * add(2, 2, 4): taskMap={1:[1,5], 2:[2,4]}, heap=[[5,1],[4,2]]
 * edit(1, 3): taskMap={1:[1,3], 2:[2,4]}, heap=[[5,1],[4,2],[3,1]]
 * execTop(): poll [5,1], invalid (priority 5≠3), poll [4,2], valid
 *            return 2, taskMap={1:[1,3]}
 * 
 * Time: O(log n) add/edit/execTop, O(1) rmv
 * Space: O(n)
 */
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
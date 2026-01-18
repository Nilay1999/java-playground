package LinkedList;

/**
 * Reverse Nodes in K-Group Algorithm:
 * Reverse every k consecutive nodes in linked list. If remaining nodes < k, leave as-is.
 * 
 * RECURSIVE APPROACH:
 * 1. Check if there are at least k nodes remaining
 * 2. If yes: reverse first k nodes, recursively process rest
 * 3. If no: return head as-is (base case)
 * 
 * ALGORITHM STEPS:
 * 1. Move pointer k steps to find the boundary
 * 2. If boundary found, reverse nodes from head to boundary
 * 3. Recursively reverse remaining list starting from boundary
 * 4. Connect reversed part with recursively processed part
 * 
 * REVERSE PROCESS:
 * - Use standard iterative reversal for k nodes
 * - Original head becomes tail after reversal
 * - Connect tail to result of recursive call
 * 
 * Example: 1→2→3→4→5, k=3
 * Step 1: Reverse 1→2→3 to get 3→2→1
 * Step 2: Recursively process 4→5 (< k nodes, return as-is)
 * Step 3: Connect: 3→2→1→4→5
 * 
 * Time: O(n), Space: O(n/k) for recursion stack
 */
public class ReverseNodesInKGroup {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseList(ListNode start, ListNode end) {
        ListNode prev = null;

        while (start != end) {
            ListNode temp = start.next;
            start.next = prev;
            prev = start;
            start = temp;
        }

        return prev;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode ptr = head;
        for (int i = 0; i < k; i++) {
            if (ptr == null)
                return null;
            ptr = ptr.next;
        }

        ListNode reversed = reverseList(head, ptr);
        head.next = reverseKGroup(ptr, k);

        return reversed;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int k = 3;

        System.out.println(new ReverseNodesInKGroup().reverseKGroup(head, k));
    }
}

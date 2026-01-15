package company.Amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU Cache - Least Recently Used Cache
 * 
 * Problem: Design a data structure that follows LRU eviction policy.
 * - get(key): Return value if key exists, else -1. Mark as recently used.
 * - put(key, value): Insert/update key-value. Evict LRU item if capacity exceeded.
 * 
 * Both operations must be O(1) time complexity.
 * 
 * Solution: HashMap + Doubly Linked List
 * - HashMap: O(1) key lookup
 * - Doubly Linked List: O(1) insert/remove, maintains usage order
 * 
 * Visual Structure (Circular Doubly Linked List):
 * 
 *     ┌─────────────────────────────────────────────────────┐
 *     │                                                     │
 *     ▼                                                     │
 *  [OLDEST] ←→ [LRU node] ←→ [node] ←→ ... ←→ [MRU node] ←→ [LATEST]
 *  (dummy)      ↑                                ↑          (dummy)
 *               │                                │
 *           Evict first                    Insert here
 *           when full                      (most recent)
 * 
 * - OLDEST.next = Least Recently Used (first to evict)
 * - LATEST.prev = Most Recently Used (just accessed/inserted)
 * - Dummy nodes simplify edge cases (no null checks needed)
 */

public class LruCache {
    
    // Node for doubly linked list
    class Node {
        int key;   // Need key to remove from HashMap when evicting
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = this.next = null;
        }
    }

    private int cap;                    // Maximum capacity
    private Map<Integer, Node> cache;   // HashMap: key -> Node (O(1) lookup)
    private Node latest;                // Dummy tail - MRU side
    private Node oldest;                // Dummy head - LRU side

    /**
     * Initialize:
     * 
     *   [OLDEST] ←→ [LATEST]
     *   (empty cache, just dummy nodes connected)
     */
    public LruCache(int capacity) {
        this.cap = capacity;
        this.cache = new HashMap<>();
        this.latest = this.oldest = new Node(0, 0);
        this.latest.next = this.oldest;
        this.oldest.prev = this.latest;
    }

    /**
     * Get value by key:
     * 1. If exists: move to MRU position (remove + insert), return value
     * 2. If not exists: return -1
     * 
     * Example: get(2) when list is [OLDEST] ←→ [1] ←→ [2] ←→ [3] ←→ [LATEST]
     * Result:                       [OLDEST] ←→ [1] ←→ [3] ←→ [2] ←→ [LATEST]
     *                                                         ↑
     *                                                   moved to MRU
     */
    public int get(int key) {
        if (this.cache.containsKey(key)) {
            Node item = this.cache.get(key);
            remove(item);   // Remove from current position
            insert(item);   // Re-insert at MRU position (before LATEST)
            return item.val;
        }
        return -1;
    }

    /**
     * Put key-value:
     * 1. If key exists: remove old node
     * 2. Create new node, add to cache and list
     * 3. If over capacity: evict LRU (OLDEST.next)
     * 
     * Example: put(4,4) with cap=3, list is [OLDEST] ←→ [1] ←→ [2] ←→ [3] ←→ [LATEST]
     * Step 1: Insert 4           [OLDEST] ←→ [1] ←→ [2] ←→ [3] ←→ [4] ←→ [LATEST]
     * Step 2: Evict LRU (1)      [OLDEST] ←→ [2] ←→ [3] ←→ [4] ←→ [LATEST]
     */
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key)); 
        }
        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        insert(newNode);             

        if (cache.size() > cap) {
            Node lru = oldest.next; 
            remove(lru);
            cache.remove(lru.key); 
        }
    }

    /**
     * Remove node from list (O(1)):
     * 
     * Before: [A] ←→ [node] ←→ [B]
     * After:  [A] ←→ [B]
     */
    public void remove(Node node) {
        Node next = node.next;
        Node prev = node.prev;

        prev.next = next;
        next.prev = prev;
    }

    /**
     * Insert node at MRU position (before LATEST dummy) (O(1)):
     * 
     * Before: [prev] ←→ [LATEST]
     * After:  [prev] ←→ [node] ←→ [LATEST]
     *                     ↑
     *               new MRU position
     */
    public void insert(Node node) {
        Node prev = latest.prev;
        Node next = latest;

        prev.next = next.prev = node;
        node.next = next;
        node.prev = prev;
    }
}

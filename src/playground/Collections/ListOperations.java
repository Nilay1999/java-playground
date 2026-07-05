package playground.Collections;

import java.util.*;

public class ListOperations {

    public static void main(String[] args) {
        testListOperations();
    }

    public static <T> List<T> removeDuplicates(List<T> list) {
        Set<T> set = new HashSet<>(list);
        return new ArrayList<T>(set);
    }

    public static <T> List<T> findIntersection(List<T> list1, List<T> list2) {
        List<T> commonElementList = new ArrayList<>(list1);
        commonElementList.retainAll(list2);
        return commonElementList;
    }

    public static <T> List<List<T>> partition(List<T> list, int size) {
        int listSize = list.size();
        List<List<T>> chunks = new ArrayList<>();

        for (int i = 0; i < listSize; i += size) {
            int idx = Math.min(i + size, listSize);
            chunks.add(new ArrayList<>(list.subList(i, idx)));
        }

        return chunks;
    }

    public static <T> List<T> rotateRight(List<T> list, int k) {
        Collections.rotate(list, k);
        return list;
    }

    private static void testListOperations() {
        System.out.println("=== List Operations Test ===");

        // Test 1: Remove duplicates
        List<Integer> withDuplicates = Arrays.asList(1, 2, 3, 2, 4, 1, 5);
        List<Integer> unique = removeDuplicates(withDuplicates);
        System.out.println("Remove duplicates: " + withDuplicates + " -> " + unique);
        // Expected: [1, 2, 3, 4, 5]

        // Test 2: Intersection
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);
        List<Integer> intersection = findIntersection(list1, list2);
        System.out.println("Intersection: " + list1 + " ∩ " + list2 + " = " + intersection);
        // Expected: [3, 4, 5]

        // Test 3: Partition
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<List<Integer>> partitions = partition(numbers, 3);
        System.out.println("Partition by 3: " + partitions);
        // Expected: [[1, 2, 3], [4, 5, 6], [7, 8, 9]]

        // Test 4: Rotate
        List<Integer> toRotate = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> rotated = rotateRight(toRotate, 2);
        System.out.println("Rotate right by 2: " + Arrays.asList(1, 2, 3, 4, 5) + " -> " + rotated);
        // Expected: [4, 5, 1, 2, 3]

        System.out.println("All tests completed!\n");
    }
}

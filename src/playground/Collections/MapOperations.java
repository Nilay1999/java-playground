package playground.Collections;

import java.util.*;

public class MapOperations {

    static class Person {
        String name;
        String department;
        int age;

        public Person(String name, String department, int age) {
            this.name = name;
            this.department = department;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

    public static void main(String[] args) {
        testMapOperations();
    }

    // TODO: Implement - Merge two maps, summing values for duplicate keys
    public static Map<String, Integer> mergeMaps(Map<String, Integer> map1,
            Map<String, Integer> map2) {
        return null;
    }

    // TODO: Implement - Invert map (values become keys, keys become values)
    // Assume values are unique
    public static <K, V> Map<V, K> invertMap(Map<K, V> map) {
        return null;
    }

    // TODO: Implement - Group persons by department
    public static Map<String, List<Person>> groupByDepartment(List<Person> persons) {
        return null;
    }

    // TODO: Implement - Find key with maximum value
    public static <K> K findMaxValueKey(Map<K, Integer> map) {
        return null;
    }

    private static void testMapOperations() {
        System.out.println("=== Map Operations Test ===");

        // Test 1: Merge maps
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("a", 1);
        map1.put("b", 2);
        map1.put("c", 3);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("b", 3);
        map2.put("c", 4);
        map2.put("d", 5);

        Map<String, Integer> merged = mergeMaps(map1, map2);
        System.out.println("Merged maps: " + merged);
        // Expected: {a=1, b=5, c=7, d=5}

        // Test 2: Invert map
        Map<String, Integer> original = Map.of("one", 1, "two", 2, "three", 3);
        Map<Integer, String> inverted = invertMap(original);
        System.out.println("Inverted map: " + inverted);
        // Expected: {1=one, 2=two, 3=three}

        // Test 3: Group by department
        List<Person> persons = Arrays.asList(
                new Person("Alice", "Engineering", 30),
                new Person("Bob", "Sales", 25),
                new Person("Charlie", "Engineering", 35),
                new Person("David", "Sales", 28));
        Map<String, List<Person>> grouped = groupByDepartment(persons);
        System.out.println("Grouped by department: " + grouped);

        // Test 4: Find max value key
        Map<String, Integer> scores = Map.of("Alice", 85, "Bob", 92, "Charlie", 78);
        String topScorer = findMaxValueKey(scores);
        System.out.println("Top scorer: " + topScorer);
        // Expected: Bob

        System.out.println("All tests completed!\n");
    }
}

package playground.FunctionalProgramming;

import java.util.*;

/**
 * Question 12: Optional API
 * 
 * Task: Use Optional to handle null values:
 * 1. Create Optional and check presence
 * 2. Use orElse, orElseGet, orElseThrow
 * 3. Chain operations with map and flatMap
 */
public class OptionalHandling {

    static class User {
        private String name;
        private Optional<String> email;

        public User(String name, String email) {
            this.name = name;
            this.email = Optional.ofNullable(email);
        }

        public Optional<String> getEmail() {
            return email;
        }
    }

    // TODO: Implement - Get email or default
    public static String getEmailOrDefault(User user, String defaultEmail) {
        return null;
    }

    // TODO: Implement - Get email length or 0
    public static int getEmailLength(User user) {
        return 0;
    }

    public static void main(String[] args) {
        User user1 = new User("Alice", "alice@example.com");
        User user2 = new User("Bob", null);

        System.out.println("Email: " + getEmailOrDefault(user1, "no-email"));
        System.out.println("Email: " + getEmailOrDefault(user2, "no-email"));
        System.out.println("Length: " + getEmailLength(user1));
    }
}

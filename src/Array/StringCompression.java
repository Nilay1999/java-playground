package Array;

public class StringCompression {
    public int compress(char[] chars) {
        int n = chars.length;
        int i = 0;
        int write = 0;
        while (i < n) {
            int count = 0;
            char curr = chars[i];
            while (i < n && chars[i] == curr) {
                count++;
                i++;
            }
            chars[write] = curr;
            write++;

            if (count > 1) {
                for (char w : String.valueOf(count).toCharArray()) {
                    chars[write] = w;
                    write++;
                }
            }
        }
        return write;
    }
}

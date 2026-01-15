package company.Amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReorderDataLogFiles {
    class Item {
        String identifier;
        String word;
    }

    public String[] reorderLogFiles(String[] logs) {
        List<String> digits = new ArrayList<>();
        List<String> words = new ArrayList<>();

        for (String log: logs) {
            String[] split = log.split(" ", 2); // split into identifier and rest
            String content = split[1];

            if (Character.isDigit(content.charAt(0))) {
                digits.add(log); // it's a digit-log
            } else {
                words.add(log); // it's a letter-log
            }
        }

        Collections.sort(words, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);

            int cmp = split1[1].compareTo(split2[1]); // compare contents
            if (cmp == 0) {
                return split1[0].compareTo(split2[0]); // if contents are same, compare identifiers
            }
            return cmp;
        });

        String[] result = new String[logs.length];
        int i = 0;

        for (String log : words) {
            result[i++] = log;
        }
        for (String log : digits) {
            result[i++] = log;
        }

        return result;
    }

    public static String removeFirstWord(String s) {
        int index = s.indexOf(' ');
        return (index == -1) ? "" : s.substring(index + 1);
    }


    public static void main(String[] args) {
        String[] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        System.out.println(new ReorderDataLogFiles().reorderLogFiles(logs));
    }    
}

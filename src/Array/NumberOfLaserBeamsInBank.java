package Array;

import java.util.ArrayList;
import java.util.List;

public class NumberOfLaserBeamsInBank {
    /**
     * Laser Beam Counting Algorithm:
     * 1. Count security devices ('1') in each row
     * 2. Store only rows with devices (ignore empty rows)
     * 3. Calculate beams: each device in row i connects to each device in row i+1
     * 4. Total beams = sum of (devices_in_row_i × devices_in_row_i+1)
     * 
     * Example: ["011001", "000000", "010100"]
     * Row 0: 3 devices, Row 2: 2 devices (skip row 1)
     * Beams = 3 × 2 = 6
     * 
     * Time: O(m*n) where m=rows, n=cols, Space: O(m) for storing device counts
     */
    public int numberOfBeams(String[] bank) {
        // Step 1: Count devices in each row, store only non-empty rows
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < bank.length; i++) {
            char[] line = bank[i].toCharArray();
            int laser = 0; // count of devices ('1') in current row
            
            // Count security devices in this row
            for (char c : line) {
                if (c == '1')
                    laser++;
            }
            // Only store rows that have at least one device
            if (laser > 0)
                list.add(laser);
        }
        
        // Step 2: Calculate total beams between consecutive rows with devices
        int total = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            // Beams = devices_in_row_i × devices_in_row_(i+1)
            if (i + 1 < list.size())
                total += list.get(i) * list.get(i + 1);
        }

        return total;
    }

    public static void main(String[] args) {
        String[] back = { "011001", "001000", "010100", "001001" };
        System.out.println(new NumberOfLaserBeamsInBank().numberOfBeams(back));
    }
}

package Graph;

public class TownJudge {
    public int findJudge(int n, int[][] trust) {
        int[] in = new int[n + 1];
        int[] out = new int[n + 1];

        for (int[] ints : trust) {
            int source = ints[0];
            int dest = ints[1];

            out[source]++;
            in[dest]++;
        }
        for (int i = 1; i <= n; i++) {
            if (in[i] == n - 1 && out[i] == 0) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int n = 2;
        int[][] trust = {{1, 2}};

        System.out.println(new TownJudge().findJudge(n, trust));
    }
}

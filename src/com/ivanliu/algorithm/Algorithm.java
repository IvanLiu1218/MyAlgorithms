package com.ivanliu.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Algorithm {
    /**
     * ------------------------------------------
     *  Dynamic Programming
     * ------------------------------------------
     */
    /**
     * Longest Increasing Sequence
     */
    public int[] longestIncSequence(int[] nums) {
        int size = nums.length + 1;
        int[] n = new int[size];
        n[0] = Integer.MIN_VALUE;
        for (int i = 1; i < size; ++i) {
            n[i] = nums[i - 1];
        }
        int[] dp = new int[size];
        int[] pos = new int[size];
        Arrays.fill(dp, 0);
        Arrays.fill(pos, -1);
        for (int i = 1; i < size; ++i) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < i; ++j) {
                if (n[i] > n[j]) {
                    if (dp[j] > max) {
                        max = dp[j];
                        pos[i] = j;
                    }
                }
            }
            dp[i] = max + 1;
        }
        int max = Integer.MIN_VALUE;
        int x = -1;
        for (int i = 0; i < size; ++i) {
            if (dp[i] > max) {
                max = dp[i];
                x = i;
            }
        }
        Deque<Integer> stack = new ArrayDeque<>();
        while (x != 0) {
            stack.offerFirst(n[x]);
            x = pos[x];
        }
        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pollFirst());
        }
        return list.stream().mapToInt(i -> i.intValue()).toArray();
    }

    /**
     *  Edit Distance
     */
    public static class Cell {
        public int cost;
        public int parent;
        public Cell(int cost, int parent) {
            this.cost = cost;
            this.parent = parent;
        }
    }
    public static final int MATCH  = 0;
    public static final int INSERT = 1;
    public static final int DELETE = 2;
    public int compareString(String p, String t) {
        int m = p.length() + 1;
        int n = t.length() + 1;
        Cell[][] dp = new Cell[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dp[i][j] = new Cell(0, -1);
            }
        }
        for (int i = 1; i < m; ++i) {
            dp[i][0].cost = i;
            dp[i][0].parent = DELETE;
        }
        for (int j = 1; j < n; ++j) {
            dp[0][j].cost = j;
            dp[0][j].parent = INSERT;
        }
        int[] opt = new int[3];
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                opt[0] = dp[i - 1][j - 1].cost + match(p.charAt(i - 1), t.charAt(j - 1));
                opt[1] = dp[i][j - 1].cost + indel(t.charAt(j - 1));
                opt[2] = dp[i - 1][j].cost + indel(p.charAt(i - 1));
                dp[i][j].cost = opt[0];
                dp[i][j].parent = MATCH;
                for (int x = 1; x <= 2; ++x) {
                    if (opt[x] < dp[i][j].cost) {
                        dp[i][j].cost = opt[x];
                        dp[i][j].parent = x;
                    }
                }
            }
        }
        System.out.println(getOperation(dp, p, t, p.length(), t.length()));
        return dp[p.length()][t.length()].cost;
    }
    public int match(char si, char tj) {
        if (si == tj) return 0;
        return 1;
    }
    public int indel(char c) {  // insert or delete
        return 1;
    }
    public StringBuilder opts;
    public String getOperation(Cell[][] dp, String p, String t, int i, int j) {
        opts = new StringBuilder();
        reconstruct(dp, p, t, i, j);
        return opts.toString();
    }
    public void reconstruct(Cell[][] dp, String p, String t, int i, int j) {
        if (dp[i][j].parent == -1) return;
        if (dp[i][j].parent == MATCH) {
            reconstruct(dp, p, t, i - 1, j - 1);
            opts.append(match_out(p, t, i, j));
            return;
        } else if (dp[i][j].parent == INSERT) {
            reconstruct(dp, p, t, i, j - 1);
            opts.append(insert_out(t, j - 1));
            return;
        } else if (dp[i][j].parent == DELETE) {
            reconstruct(dp, p, t, i - 1, j);
            opts.append(delete_out(p, i));
            return;
        }
    }
    public char match_out(String p, String t, int i, int j) {
        if (p.charAt(i - 1) == t.charAt(j - 1)) return 'M';  // Match
        return 'S'; // Substitute
    }
    public char delete_out(String p, int i) {
        return 'D';
    }
    public char insert_out(String t, int j) {
        return 'I';
    }
}

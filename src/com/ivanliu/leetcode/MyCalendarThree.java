package com.ivanliu.leetcode;

import java.util.Arrays;
import java.util.Random;

public class MyCalendarThree {

    public static class EdgeNode {
        public int y;
        EdgeNode next;
        public EdgeNode(int y, EdgeNode next) {
            this.y = y;
            this.next = next;
        }
    }
    public static class Graph {
        public EdgeNode[] edges;
        public int[] degree;
        public int nVertices;

        public Graph(int size) {
            this.edges = new EdgeNode[size];
            this.degree = new int[size];
            this.nVertices = 0;
        }
        public void insertEdge(int x, int y, boolean directed) {
            EdgeNode edge = new EdgeNode(y, edges[x]);
            edges[x] = edge;
            degree[x]++;
            if (!directed) {
                insertEdge(y, x, true);
            }
        }
    }
    private int nJobs;
    private int[] jobStart;
    private int[] jobEnd;
    private Graph g;
    public MyCalendarThree() {
        this.nJobs = 0;
        jobStart = new int[400];
        jobEnd = new int[400];
        g = new Graph(400);
    }
    public int book(int start, int end) {
        jobStart[nJobs] = start;
        jobEnd[nJobs] = end;
        ++nJobs;
        for (int i = 0; i < nJobs - 1; ++i) {
            if (conflict(jobStart[i], jobEnd[i], start, end)) {
                g.insertEdge(i, nJobs - 1, false);
            }
        }
        return fetchLargestClique(g);
    }

    private int fetchLargestClique(Graph g) {
        int[] index = new int[nJobs];
        for (int i = 0; i < nJobs; ++i) {
            index[i] = i;
        }
        int[] copy = new int[nJobs];
        System.arraycopy(g.degree, 0, copy, 0, nJobs);
        quickSort(copy, 0, copy.length, index);
        boolean[] inClique = new boolean[nJobs];
        int max = 0;
        for (int i = index.length - 1; i >= 0; --i) {
            int x = index[i];
            EdgeNode edge = g.edges[x];
            Arrays.fill(inClique, false);
            inClique[x] = true;
            int nClique = 1;
            while (edge != null) {
                int y = edge.y;
                inClique[y] = true;
                nClique++;
                edge = edge.next;
            }
            if (nClique > max && isClique(inClique)) {
                if (nClique > max) max = nClique;
            }
        }
        return max;
    }
    private boolean isAdjacent(Graph g, int from, int to) {
        EdgeNode edge = g.edges[from];
        while (edge != null) {
            if (edge.y == to) return true;
            edge = edge.next;
        }
        return false;
    }
    private boolean isClique(boolean[] inClique) {
        for (int l = 0; l < inClique.length; ++l) {
            for (int m = 0; m < inClique.length; ++m) {
                if (l != m && inClique[l] && inClique[m]) {
                    if (!isAdjacent(g, l, m)) return false;
                }
            }
        }
        return true;
    }

    private boolean conflict(int s1, int e1, int s2, int e2) {
        if (s2 <= s1 && s1 < e2) return true;
        if (s2 < e1 && e1 < e2) return true;
        if (s1 <= s2 && s2 < e1) return true;
        if (s1 < e2 && e2 < e1) return true;
        return false;
    }
    private void quickSort(int[] nums, int from, int to, int[] index) {
        if (from >= to) return;
        int flagIndex = random(from, to);
        int flag = nums[flagIndex];
        swap(nums, from, flagIndex);
        swap(index, from, flagIndex);
        flagIndex = index[from];
        int i = from;
        int j = to - 1;
        while (i < j) {
            while (i < j && nums[j] > flag) --j;
            nums[i] = nums[j];
            index[i] = index[j];
            while (i < j && nums[i] <= flag) ++i;
            nums[j] = nums[i];
            index[j] = index[i];
        }
        nums[i] = flag;
        index[i] = flagIndex;
        quickSort(nums, from, i, index);
        quickSort(nums, i + 1, to, index);
        return;
    }
    private int random(int from, int to) {
        return new Random().nextInt(to - from) + from;
    }
    private void swap(int[] nums, int from, int to) {
        int temp = nums[from];
        nums[from] = nums[to];
        nums[to] = temp;
    }
}

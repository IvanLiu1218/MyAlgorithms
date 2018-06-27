package com.ivanliu.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class GraphDirected extends Graph {

    public GraphDirected(int size) {
        super(size, true);
        this.low = new int[size];
        this.scc = new int[size];
        this.inTree = new boolean[size];
        this.distance = new int[size];
    }

    @Override
    public void resetStatus() {
        super.resetStatus();
        Arrays.fill(scc, -1);
        for (int i = 0; i < nVertices; ++i) {
            low[i] = i;
        }
        Arrays.fill(inTree, false);
        Arrays.fill(distance, Integer.MAX_VALUE);
    }

    public void insertEdge(int x, int y) {
        insertEdge(x, y, 0);
    }

    public void insertEdge(int x, int y, int weight) {
        super.insertEdge(x, y, weight, true);
    }

    /**
     *  -----------------------------------------------
     *  Application of DFS
     *  -----------------------------------------------
     */
    /**
     *  #1. Topological Sorting
     */
    private Deque<Integer> stack;
//    @Override
//    public void process_later(int x) {
//        stack.offerFirst(x);
//    }
    public int[] topSort() {
        resetStatus();
        stack = new ArrayDeque<>();
        dfs();
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pollFirst());
        }
        return result.stream().mapToInt(i -> i.intValue()).toArray();
    }

    /**
     *  Strongly Connected Components
     */
    protected int[] low;
    protected int[] scc;
    private int component_id;
    @Override
    public void process_early(int x) {
        stack.offerFirst(x);
    }
    @Override
    public void process_edge_dfs(int x, int y) {
        if (parent[x] != y) {
            EdgeType edgeType = identifyEdgeType(x, y);
            if (edgeType == EdgeType.BACK) {
                if (time_entry[y] < time_entry[low[x]]) {
                    low[x] = y;
                }
            }
            if (edgeType == EdgeType.CROSS) {
                if (scc[y] == -1) {
                    if (time_entry[y] < time_entry[low[x]]) {
                        low[x] = y;
                    }
                }
            }
        }
    }
    @Override
    public void process_later(int x) {
        if (low[x] == x) {
            scc[x] = ++component_id;
            int t;
            while ((t = stack.pollFirst()) != x) {
                scc[t] = component_id;
            }
        }
        if (parent[x] != -1) {
            if (time_entry[low[x]] < time_entry[low[parent[x]]]) {
                low[parent[x]] = low[x];
            }
        }
    }
    public List<int[]> strongComponents() {
        component_id = 0;
        resetStatus();
        stack = new ArrayDeque<>();
        dfs();
        List<int[]> result = new ArrayList<>();
        for (int c = 1; c <= component_id; ++c) {
            List<Integer> group = new ArrayList<>();
            for (int i = 0; i < nVertices; ++i) {
                if (scc[i] == c) {
                    group.add(i);
                }
            }
            result.add(group.stream().mapToInt(i -> i.intValue()).toArray());
        }
        return result;
    }

    /**
     *  Dijkstra's Algorithm
     */
    protected boolean[] inTree;
    protected int[] distance;
    public void dijkstra(int start) {
        resetStatus();
        distance[start] = 0;
        int x = start;
        while (!inTree[x]) {
            inTree[x] = true;
            EdgeNode edge = edges[x];
            while (edge != null) {
                int y = edge.y;
                if (distance[x] + edge.weight < distance[y]) {
                    distance[y] = distance[x] + edge.weight;
                    parent[y] = x;
                }
                edge = edge.next;
            }
            x = 0;
            int dist = Integer.MAX_VALUE;
            for (int i = 0; i < nVertices; ++i) {
                if (distance[i] < dist && !inTree[i]) {
                    dist = distance[i];
                    x = i;
                }
            }
        }
    }
}

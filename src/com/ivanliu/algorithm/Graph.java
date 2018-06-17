package com.ivanliu.algorithm;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Graph {

    public enum VertexStatus {
        UNDISCOVERED,
        DISCOVERED,
        PROCESSED;
    }

    public static class EdgeNode {
        public int y;
        public EdgeNode next;
        public EdgeNode(int y, EdgeNode next) {
            this.y = y;
            this.next = next;
        }
    }

    public int nVertices;
    public VertexStatus[] vertexStatus;
    public EdgeNode[] edges;
    public int nEdges;
    public boolean isDirected;

    public int[] parent;  // findShortestPath()
    public int[] component; // connectedComponent

    public Graph(int size, boolean isDirected) {
        this.nVertices = size;
        this.vertexStatus = new VertexStatus[size];
        this.edges = new EdgeNode[size];
        this.nEdges = 0;
        this.isDirected = isDirected;
        this.parent = new int[size];
        this.component = new int[size];
        Arrays.fill(edges, null);
        Arrays.fill(parent, -1);
        Arrays.fill(component, 0);
    }

    private boolean isValid(int x) {
        return 0 <= x && x < nVertices;
    }

    public void prepareForSearch() {
        Arrays.fill(vertexStatus, VertexStatus.UNDISCOVERED);
        Arrays.fill(parent, -1);
        Arrays.fill(component, 0);
    }

    public void insertEdge(int x, int y, boolean isDirected) {
        EdgeNode edge = new EdgeNode(y, edges[x]);
        edges[x] = edge;
        ++nEdges;
        if (!isDirected) {
            insertEdge(y, x, true);
        }
    }

    public void bfs(int start) {
        bfs(start, 0);
    }

    public void bfs(int start, int id) {
        if (!isValid(start)) return;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offerLast(start);
        while (!queue.isEmpty()) {
            int x = queue.pollFirst();
            process_early(x, id);
            EdgeNode edge = edges[x];
            while (edge != null) {
                int y = edge.y;
                if (vertexStatus[y] == VertexStatus.UNDISCOVERED) {
                    vertexStatus[y] = VertexStatus.DISCOVERED;
                    parent[y] = x;
                    queue.offerLast(y);
                    process_edge(x, y);
                } else if (vertexStatus[y] != VertexStatus.PROCESSED || isDirected) {
                    process_edge(x, y);
                }
                edge = edge.next;
            }
            vertexStatus[x] = VertexStatus.PROCESSED;
            process_later(x);
        }
    }

    public void dfs(int start) {
        if (!isValid(start)) return;
        dfs_r(start);
    }

    private void dfs_r(int x) {
        vertexStatus[x] = VertexStatus.DISCOVERED;
        EdgeNode edge = edges[x];
        while (edge != null) {
            int y = edge.y;
            if (vertexStatus[y] == VertexStatus.UNDISCOVERED) {
                parent[y] = x;
                process_edge(x, y);
                dfs_r(y);
            } else if (vertexStatus[y] != VertexStatus.PROCESSED || isDirected) {
                process_edge(x, y);
            }
            edge = edge.next;
        }
        process_later(x);
        vertexStatus[x] = VertexStatus.PROCESSED;
    }

    public boolean isConnected(int from, int to) {
        if (!isValid(from) || !isValid(to)) return false;
        prepareForSearch();
        dfs(from);
        return vertexStatus[to] == VertexStatus.PROCESSED;
    }

    public String findShortestPath(int from, int to) {
        if (!isValid(from) || !isValid(to)) return "Invalid index!";
        if (!isConnected(from, to)) return String.format("No path from %d to %d", from, to);
        prepareForSearch();
        bfs(from);
        Deque<Integer> stack = new ArrayDeque<>();
        int p = to;
        while (p != -1) {
            stack.offerFirst(p);
            p = parent[p];
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst() + "->");
        }
        return sb.toString().substring(0, sb.length() - 2);
    }

    public void connectedComponent() {
        prepareForSearch();
        int id = 1;
        for (int i = 0; i < nVertices; ++i) {
            if (vertexStatus[i] == VertexStatus.UNDISCOVERED) {
                bfs(i, id);
                ++id;
            }
        }
    }

    public void twoColor() {
        prepareForSearch();

    }

    public void process_early(int x) {
        System.out.println("PROC: " + x);
    }

    public void process_early(int x, int id) {
        component[x] = id;
        process_early(x);
    }

    public void process_edge(int x, int y) {
        System.out.println(String.format("EDGE: (%d, %d)", x, y));
    }

    public void process_later(int x) {
        System.out.println("LATE: " + x);
    }
}

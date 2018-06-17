package com.ivanliu.algorithm;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Graph {

    /**
     *  Classes:
     */
    public enum EdgeType {
        TREE,
        BACK,
        FORWARD,
        CROSS;
    }
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

    /**
     *  Attributes:
     */
    public int nVertices;
    public VertexStatus[] vertexStatus;
    public EdgeNode[] edges;
    public int nEdges;
    public boolean isDirected;

    public int[] parent;  // findShortestPath()
    public int[] component; // connectedComponent

    public int time;
    public int[] time_entry;
    public int[] time_exit;

    public Graph(int size, boolean isDirected) {
        this.nVertices = size;
        this.vertexStatus = new VertexStatus[size];
        this.edges = new EdgeNode[size];
        Arrays.fill(edges, null);
        this.nEdges = 0;
        this.isDirected = isDirected;
        this.parent = new int[size];
        Arrays.fill(parent, -1);
        this.component = new int[size];
        Arrays.fill(component, 0);
        this.time = 0;
        this.time_entry = new int[size];
        this.time_exit = new int[size];
        Arrays.fill(time_entry, 0);
        Arrays.fill(time_exit, 0);
    }

    private boolean isValid(int x) {
        return 0 <= x && x < nVertices;
    }

    public void prepareForSearch() {
        Arrays.fill(vertexStatus, VertexStatus.UNDISCOVERED);
        Arrays.fill(parent, -1);
        Arrays.fill(component, 0);
        this.time = 0;
        Arrays.fill(time_entry, 0);
        Arrays.fill(time_exit, 0);
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
        vertexStatus[start] = VertexStatus.DISCOVERED;
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
                }
//                if (vertexStatus[y] != VertexStatus.PROCESSED || !isDirected) {
                    process_edge(x, y);
//                }
                edge = edge.next;
            }
            process_later(x);
            vertexStatus[x] = VertexStatus.PROCESSED;
        }
    }

    public void dfs_f() {
        prepareForSearch();
        for (int i = 0; i < nVertices; ++i) {
            if (vertexStatus[i] == VertexStatus.UNDISCOVERED) {
                dfs(i);
            }
        }
    }

    public void dfs(int start) {
        if (!isValid(start)) return;
        dfs_r(start);
    }

    private void dfs_r(int x) {
        vertexStatus[x] = VertexStatus.DISCOVERED;
        time_entry[x] = time++;
        process_early(x);
        EdgeNode edge = edges[x];
        while (edge != null) {
            int y = edge.y;
            if (vertexStatus[y] == VertexStatus.UNDISCOVERED) {
                parent[y] = x;
                process_edge(x, y);
                dfs_r(y);
            } else /*if (vertexStatus[y] != VertexStatus.PROCESSED || isDirected)*/ {
                process_edge(x, y);
            }
            edge = edge.next;
        }
        process_later(x);
        time_exit[x] = time++;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------\n");
        sb.append(String.format("Vertices: %3d\n", nVertices));
        sb.append(String.format("Edges:    %3d\n", nEdges));
        sb.append("----------------------------\n");
        for (int i = 0; i < nVertices; ++i) {
            EdgeNode edge = edges[i];
            sb.append(String.format("[%d|id:%d|%02d/%02d]", i, component[i], time_entry[i], time_exit[i]));
            while (edge != null) {
                sb.append(String.format(" [%d->%d]", i, edge.y));
                edge = edge.next;
            }
            sb.append("\n");
        }

        return sb.toString();
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

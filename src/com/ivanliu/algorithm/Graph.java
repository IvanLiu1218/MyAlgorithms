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
    public int nEdges;
    public VertexStatus[] vertexStatus;
    public EdgeNode[] edges;
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
        this.nEdges = 0;
        this.isDirected = isDirected;
        this.parent = new int[size];
        this.component = new int[size];
        this.time = 0;
        this.time_entry = new int[size];
        this.time_exit = new int[size];

        Arrays.fill(parent, -1);
        Arrays.fill(component, 0);
        Arrays.fill(edges, null);
        Arrays.fill(time_entry, 0);
        Arrays.fill(time_exit, 0);
    }

    private boolean isValid(int x) {
        return 0 <= x && x < nVertices;
    }

    public void resetStatus() {
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
        resetStatus();
        Deque<Integer> queue = new ArrayDeque<>();
        vertexStatus[start] = VertexStatus.DISCOVERED;
        queue.offerLast(start);
        while (!queue.isEmpty()) {
            int x = queue.pollFirst();
            component[x] = id;
            process_early(x);
            EdgeNode edge = edges[x];
            while (edge != null) {
                int y = edge.y;
                if (vertexStatus[y] == VertexStatus.UNDISCOVERED) {
                    parent[y] = x;
                    vertexStatus[y] = VertexStatus.DISCOVERED;
                    queue.offerLast(y);
                    process_edge_bfs(x, y);
                } else if (vertexStatus[y] != VertexStatus.PROCESSED || isDirected) {
                    process_edge_bfs(x, y);
                }
                edge = edge.next;
            }
            vertexStatus[x] = VertexStatus.PROCESSED;
            process_later(x);
        }
    }

    public void dfs() {
        resetStatus();
        for (int i = 0; i < nVertices; ++i) {
            if (vertexStatus[i] == VertexStatus.UNDISCOVERED) {
                dfs(i);
            }
        }
    }

    public void dfs(int x) {
        vertexStatus[x] = VertexStatus.DISCOVERED;
        time_entry[x] = time++;
        process_early(x);
        EdgeNode edge = edges[x];
        while (edge != null) {
            int y = edge.y;
            if (vertexStatus[y] == VertexStatus.UNDISCOVERED) {
                parent[y] = x;
                process_edge_dfs(x, y);
                dfs(y);
            } else if (vertexStatus[y] != VertexStatus.PROCESSED || isDirected) {
                process_edge_dfs(x, y);
            }
            edge = edge.next;
        }
        vertexStatus[x] = VertexStatus.PROCESSED;
        time_exit[x] = time++;
        process_later(x);
    }

    public EdgeType identifyEdgeType(int x, int y){
        if (parent[y] == x) return EdgeType.TREE;
        if (vertexStatus[y] == VertexStatus.DISCOVERED) return EdgeType.BACK;
        if (vertexStatus[y] == VertexStatus.PROCESSED && time_entry[x] < time_entry[y]) return EdgeType.FORWARD;
        if (vertexStatus[y] == VertexStatus.PROCESSED && time_entry[x] > time_entry[y]) return EdgeType.CROSS;
        return null;
    }

    public void process_early(int x) {
        System.out.println("PROC: " + x);
    }

    public void process_edge_bfs(int x, int y) {
        System.out.println(String.format("EDGE: (%d, %d)", x, y));
    }

    public void process_edge_dfs(int x, int y) {
        if (parent[x] != y) {
            EdgeType edgeType = identifyEdgeType(x, y);
            System.out.println(String.format("EDGE: (%d, %d) %s", x, y, edgeType));
        }
    }

    public void process_later(int x) {
        System.out.println("LATE: " + x);
    }

    /**
     *  -----------------------------------------------
     *  Application of BFS
     *  -----------------------------------------------
     */

    /**
     *  #1 Set component ID
     */
    public void connectedComponent() {
        resetStatus();
        int id = 0;
        for (int i = 0; i < nVertices; ++i) {
            if (vertexStatus[i] == VertexStatus.UNDISCOVERED) {
                bfs(i, ++id);
            }
        }
    }
    /**
     *  #2. Find the shortest path for Unweighted Graph
     */
    public String findShortestPath(int from, int to) {
        if (!isValid(from) || !isValid(to)) return "Invalid index!";
        if (!isConnected(from, to)) return String.format("No path from %d to %d", from, to);
        resetStatus();
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


    /**
     *  ----------------------------------------------
     *  Application of DFS
     *  ----------------------------------------------
     */

    /**
     *  #1. Connection between x and y
     */
    public boolean isConnected(int from, int to) {
        if (!isValid(from) || !isValid(to)) return false;
        resetStatus();
        dfs(from);
        return vertexStatus[to] == VertexStatus.PROCESSED;
    }



    /**
     *  #3. How many nodes are following x
     */
    public int numOfDescendants(int x) {
        if (!isValid(x)) return -1;
        resetStatus();
        dfs(x);
        return (time_exit[x] - time_entry[x] ) / 2;
    }
}

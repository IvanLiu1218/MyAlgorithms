package com.ivanliu.algorithms.utilities;

import java.util.Arrays;

public class Graph {

    // Mandatory fields:
    private EdgeNode[] edges;
    private VertexStatus[] status;
    private int[] degree;
    private int nVertices;
    private int nEdges;
    private boolean isDirected;

    // Optional fields:
    private int[] parent;

    public Graph(int size, boolean isDirected) {

        this.edges = new EdgeNode[size];
        this.status = new VertexStatus[size];
        this.degree = new int[size];
        this.nVertices = size;
        this.nEdges = 0;
        this.isDirected = isDirected;

        this.parent = new int[size];
    }

    private void resetGraph() {
        Arrays.fill(status, VertexStatus.UNDISCOVERED);
        Arrays.fill(parent, -1);
    }

    public void insertEdge(int x, int y, boolean isDirected) {
        insertEdge(x, y, 0, isDirected);
    }

    public void insertEdge(int x, int y, int weight, boolean isDirected) {
        EdgeNode edge = new EdgeNode(y, weight, edges[x]);
        edges[x] = edge;
        degree[x]++;
        nEdges++;
        if (!isDirected) {
            insertEdge(y, x, weight, true);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------\n");
        sb.append(String.format("Vertices: %-5d\n", nVertices));
        sb.append(String.format("Edges:    %-5d\n", nEdges));
        sb.append("----------------\n");
        for (int i = 0; i < nVertices; ++i) {
            EdgeNode node = edges[i];
            sb.append(String.format("[%02d|d:%d]:", i, degree[i]));
            while(node != null) {
                sb.append(' ' + node.toString(i));
                node = node.next;
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public void bfs(int start) {
        if (start < 0 || start > nVertices) return;

    }

    public void dfs(int start) {
        if (start < 0 || start > nVertices) return;
        resetGraph();
        dfs_r(start);
    }

    private void dfs_r(int x) {
        status[x] = VertexStatus.DISCOVERED;
        process_early(x);
        EdgeNode edge = edges[x];
        while (edge != null) {
            int y = edge.y;
            if (status[y] == VertexStatus.UNDISCOVERED) {
                parent[y] = x;
                process_edge(x, y);
                dfs_r(y);
            } else if (status[y] != VertexStatus.PROCESSED || isDirected) {
                process_edge(x, y);
            }
            edge = edge.next;
        }
        process_last(x);
    }

    public void printParent() {
        System.out.print("Vertex: ");
        for (int i = 0; i < nVertices; ++i) {
            System.out.print(String.format("%3d", i));
        }
        System.out.print("\nParent: ");
        for (int i = 0; i < nVertices; ++i) {
            System.out.print(String.format("%3d", parent[i]));
        }
    }

    public void process_early(int index) {
        System.out.println("PREP: " + index);
    }

    public void process_edge(int x, int y) {
        System.out.println(String.format("PROC: (%d, %d)", x, y));
    }

    public void process_last(int index) {
        System.out.println("POST: " + index);
    }
}

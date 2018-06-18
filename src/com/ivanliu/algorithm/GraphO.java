package com.ivanliu.algorithm;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static com.ivanliu.algorithm.Graph.EdgeNode;

public class GraphO {

    public EdgeNode[] edges;
    public int[] degree;
    public int nVertices;
    public int nEdges;
    public boolean directed;

    public boolean[] discovered;
    public boolean[] processed;

    public GraphO(int size, boolean directied) {
        this.nVertices = size;
        this.directed = directied;
        this.edges = new EdgeNode[size];
        this.degree = new int[size];
        this.nEdges = 0;
        this.discovered = new boolean[size];
        this.processed = new boolean[size];
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
            sb.append(String.format("[%d]", i));
            while (edge != null) {
                sb.append(String.format(" [%d->%d]", i, edge.y));
                edge = edge.next;
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public void insertEdge(int x, int y, boolean directed) {
        EdgeNode edge = new EdgeNode(y, edges[x]);
        edges[x] = edge;
        this.nEdges++;
        degree[x]++;
        if (!directed) {
            insertEdge(y, x, true);
        }
    }

    public void prepareForSearch() {
        Arrays.fill(discovered, false);
        Arrays.fill(processed, false);
    }

    public void bfs(int start) {
        Deque<Integer> queue = new ArrayDeque<>();
        discovered[start] = true;
        queue.offerLast(start);
        while (!queue.isEmpty()) {
            int x = queue.pollFirst();
            process_early(x);
            EdgeNode edge = edges[x];
            while (edge != null) {
                int y = edge.y;
                if (processed[y] == false || directed) {   // UNPROCESSED && UNDISCOVERED/DISCOVERED
//                    System.out.println(String.format("%s:%s", discovered[y], processed[y]));
                    process_edge(x, y);
                }
                if (discovered[y] == false) {  // PROCESSED/UNPROCESSED && UNDISCOVERED
//                    System.out.println(String.format("%s:%s", discovered[y], processed[y]));
                    discovered[y] = true;
                    queue.offerLast(y);
                }
                edge = edge.next;
            }
            process_later(x);
            processed[x] = true;
        }
    }

    public void dfs(int start, int id) {
        for(int i = 0; i < nVertices; ++i) {
            if (discovered[i] == false) {

            }
        }
    }
    public void dfs(int x) {
        discovered[x] = true;
        process_early(x);
        EdgeNode edge = edges[x];
        while (edge != null) {
            int y = edge.y;
            if (discovered[y] == false) {
//                System.out.println(String.format("%s:%s", discovered[y], processed[y]));
                process_edge(x, y);
                dfs(y);
            } else if (processed[y] == false || directed) {
//                System.out.println(String.format("%s:%s", discovered[y], processed[y]));
                process_edge(x, y);
            }
            edge = edge.next;
        }
        process_later(x);
        processed[x] = true;
    }

    public void process_early(int x) {
        System.out.println("PROC: " + x);
    }
    public void process_edge(int x, int y) {
        System.out.println(String.format("EDGE: (%d, %d)", x, y));
    }
    public void process_later(int x) {
        System.out.println("LATE: " + x);
    }
}

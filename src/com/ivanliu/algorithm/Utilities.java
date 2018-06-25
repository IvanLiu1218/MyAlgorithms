package com.ivanliu.algorithm;

import static com.ivanliu.algorithm.Graph.EdgeNode;

public class Utilities {

    public static void print(Graph g) {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------\n");
        sb.append(String.format("Vertices: %3d\n", g.nVertices));
        sb.append(String.format("Edges:    %3d\n", g.nEdges));
        sb.append("----------------------------\n");
        for (int i = 0; i < g.nVertices; ++i) {
            EdgeNode edge = g.edges[i];
            sb.append(String.format("[%d]", i));
            while (edge != null) {
                sb.append(String.format(" [%d->%d]", i, edge.y));
                edge = edge.next;
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void printInfo(Graph g) {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertex:   ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3d", i));
        }
        sb.append("\n");
        sb.append("Parent:   ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3d", g.parent[i]));
        }
        sb.append("\n");
        sb.append("Component:");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3d", g.component[i]));
        }
        sb.append("\n");
        sb.append("EntryTime:");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3d", g.time_entry[i]));
        }
        sb.append("\n");
        sb.append("ExitTime: ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3d", g.time_exit[i]));
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }
}

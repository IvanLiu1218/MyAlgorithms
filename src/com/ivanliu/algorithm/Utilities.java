package com.ivanliu.algorithm;

import static com.ivanliu.algorithm.Graph.EdgeNode;

public class Utilities {

    public static String toString(Graph g) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Vertices: %3d\n", g.nVertices));
        sb.append(String.format("Edges:    %3d\n", g.nEdges));
        sb.append("----------------------------\n");
        for (int i = 0; i < g.nVertices; ++i) {
            EdgeNode edge = g.edges[i];
            sb.append(String.format("[%d:id=%d]", i, g.component[i]));
            while (edge != null) {
                sb.append(String.format(" [%d->%d]", i, edge.y));
                edge = edge.next;
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}

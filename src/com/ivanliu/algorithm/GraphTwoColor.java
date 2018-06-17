package com.ivanliu.algorithm;

import java.util.Arrays;

public class GraphTwoColor extends GraphID {

    public enum COLOR {
        UNCOLORED,
        WHITE,
        BLACK;
    }

    private COLOR[] color;

    public GraphTwoColor(int size) {
        super(size);
        this.color = new COLOR[size];
    }

    @Override
    public void prepareForSearch() {
        super.prepareForSearch();
        Arrays.fill(color, COLOR.UNCOLORED);
    }

    private COLOR getComplementColor(COLOR color) {
        if (COLOR.BLACK.equals(color)) return COLOR.WHITE;
        else if (COLOR.WHITE.equals(color)) return COLOR.BLACK;
        return COLOR.UNCOLORED;
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
            sb.append(String.format("[%d:color=%s]", i, color[i]));
            while (edge != null) {
                sb.append(String.format(" [%d->%d]", i, edge.y));
                edge = edge.next;
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public void twoColor() {
        prepareForSearch();
        for (int i = 0; i < nVertices; ++i) {
            if (vertexStatus[i] ==  VertexStatus.UNDISCOVERED) {
                color[i] = COLOR.WHITE;
                bfs(i);
            }
        }
    }

    @Override
    public void process_edge(int x, int y) {
        super.process_edge(x, y);
        if (color[x] == color[y]) {
            System.out.println(String.format("Not bipartite due to (%d, %d)", x, y));
            return;
        }
        color[y] = getComplementColor(color[x]);
    }
}

package com.ivanliu.algorithm;

import java.util.Arrays;

public class GraphUndirected extends Graph {

    public GraphUndirected(int size) {
        super(size, false);
        this.color = new VertexColor[size];
    }

    @Override
    public void resetStatus() {
        super.resetStatus();
        Arrays.fill(color, VertexColor.U);
        this.hasCycle = false;
    }

    public void insertEdge(int x, int y) {
        super.insertEdge(x, y, false);
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
                bfs(i, id++);
            }
        }
    }

    /**
     *  #2. Two Coloring graph
     */
    public enum VertexColor {
        U,    // UNCOLORED
        B,    // BLACK
        W;    // WHITE
    }
    public VertexColor[] color;
    public void twoColor() {
        resetStatus();
        for (int i = 0; i < nVertices; ++i) {
            if (vertexStatus[i] == VertexStatus.UNDISCOVERED) {
                color[i] = VertexColor.B;
                bfs(i);
            }
        }
    }
    private VertexColor complement(VertexColor color) {
        if (color == VertexColor.B) return VertexColor.W;
        if (color == VertexColor.W) return VertexColor.B;
        return VertexColor.U;
    }
    @Override
    public void process_edge_bfs(int x, int y) {
        if (color[x] == color[y]) {
            System.out.println("ERROR");
            return;
        }
        color[y] = complement(color[x]);
    }

    /**
     *  -----------------------------------------------
     *  Application of DFS
     *  -----------------------------------------------
     */
    /**
     *  #1. Finding Cycles
     */
    private boolean hasCycle;
    public boolean hasCycle() {
        resetStatus();
        dfs();
        return hasCycle;
    }
    @Override
    public void process_edge_dfs(int x, int y) {
        if (parent[x] != parent[y]) {
            EdgeType type = identifyEdgeType(x, y);
            System.out.println(String.format("EDGE: (%d, %d) %s", x, y, type));
            if (type == EdgeType.BACK) {
                System.out.println(String.format("CYCLE: %d->%d", y, x));
                FINISHED = true;
                hasCycle = true;
            }
        }
    }
    /**
     *  #1. Finding Articulation Vertices
     */
}

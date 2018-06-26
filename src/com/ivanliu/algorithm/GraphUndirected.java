package com.ivanliu.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphUndirected extends Graph {

    public GraphUndirected(int size) {
        super(size, false);
        this.color = new VertexColor[size];
        this.reachable_ancestor = new int[size];
        this.tree_out_degree = new int[size];
        this.vertexType = new VertexType[size];
    }

    @Override
    public void resetStatus() {
        super.resetStatus();
        Arrays.fill(color, VertexColor.U);
        Arrays.fill(tree_out_degree, 0);
        Arrays.fill(vertexType, VertexType.N);
    }

    public void insertEdge(int x, int y) {
        insertEdge(x, y, 0);
    }

    public void insertEdge(int x, int y, int weight) {
        super.insertEdge(x, y, weight, false);
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
//    @Override
//    public void process_edge_bfs(int x, int y) {
//        if (color[x] == color[y]) {
//            System.out.println("ERROR");
//            return;
//        }
//        color[y] = complement(color[x]);
//    }

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
        this.hasCycle = false;
        dfs();
        return hasCycle;
    }
//    @Override
//    public void process_edge_dfs(int x, int y) {
//        if (parent[x] != parent[y]) {
//            EdgeType type = identifyEdgeType(x, y);
//            System.out.println(String.format("EDGE: (%d, %d) %s", x, y, type));
//            if (type == EdgeType.BACK) {
//                System.out.println(String.format("CYCLE: %d->%d", y, x));
//                FINISHED = true;
//                hasCycle = true;
//            }
//        }
//    }
    /**
     *  #2. Finding Articulation Vertices
     */
    public enum VertexType {
        N,   // Not Articulation
        R,   // Root Articulation
        P,   // Parent Articulation
        B;   // Bridge Articulation
    }
    protected int[] reachable_ancestor;
    protected int[] tree_out_degree;
    protected VertexType[] vertexType;
    @Override
    public void process_early(int x) {
        this.reachable_ancestor[x] = x;
    }
    @Override
    public void process_edge_dfs(int x, int y) {
        if (parent[x] != y) {
            EdgeType edgeType = identifyEdgeType(x, y);
            if (edgeType == EdgeType.TREE) {
                tree_out_degree[x]++;
            } else if (edgeType == EdgeType.BACK) {
                int ax = reachable_ancestor[x];
                if (time_entry[y] < time_entry[ax]) {
                    reachable_ancestor[x] = y;
                }
            }
        }
    }
    @Override
    public void process_later(int x) {
        if (parent[x] == -1) {                 // (1) if x is root
            if (tree_out_degree[x] > 1) {      // (2) x has more than ONE child
                vertexType[x] = VertexType.R;  //  x is Root Articulation
            }
        } else if (reachable_ancestor[x] == parent[x]   // (1) if the oldest reachable node is the direct parent of x
                && parent[parent[x]] != -1) {           // (2) if the direct parent of x is not root
            vertexType[parent[x]] = VertexType.P;               //  x is Parent Articulation
        } else if (reachable_ancestor[x] == x) {    // if the oldest reachable node of x is x itself
            vertexType[parent[x]] = VertexType.B;   // the parent of x is Bridge Articulation
            if (tree_out_degree[x] > 0) {           // if x is not the leaf node
                vertexType[x] = VertexType.B;       // x is Bridge Articulation as well
            }
        }
        if (parent[x] != -1) {
            int time_x = time_entry[reachable_ancestor[x]];
            int time_parent = time_entry[reachable_ancestor[parent[x]]];
            if (time_x < time_parent) {
                reachable_ancestor[parent[x]] = reachable_ancestor[x];
            }
        }
    }
    public int[] getAVs() {
        resetStatus();
        dfs();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nVertices; ++i) {
            if (vertexType[i] != VertexType.N) {
                result.add(i);
            }
        }
        return result.stream().mapToInt(i -> i.intValue()).toArray();
    }

    /**
     *  Weighted Undirected Graph
     */
    /**
     *  #1. Minimum Spanning Tree
     */
}
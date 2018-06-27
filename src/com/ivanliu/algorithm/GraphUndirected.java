package com.ivanliu.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GraphUndirected extends Graph {

    public GraphUndirected(int size) {
        super(size, false);
        this.color = new VertexColor[size];
        this.reachable_ancestor = new int[size];
        this.tree_out_degree = new int[size];
        this.vertexType = new VertexType[size];
        this.inTree = new boolean[size];
        this.distance = new int[size];
    }

    @Override
    public void resetStatus() {
        super.resetStatus();
        Arrays.fill(color, VertexColor.U);
        Arrays.fill(tree_out_degree, 0);
        Arrays.fill(vertexType, VertexType.N);
        Arrays.fill(inTree, false);
        Arrays.fill(distance, Integer.MAX_VALUE);
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
//        if (parent[x] != y) {
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
            vertexType[parent[x]] = VertexType.P;       //  x is Parent Articulation
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
    protected boolean[] inTree;
    protected int[] distance;
    public int prim(int start) {
        resetStatus();
        distance[start] = 0;
        int x = start;
        while (!inTree[x]) {
            inTree[x] = true;
            EdgeNode edge = edges[x];
            while (edge != null) {
                int y = edge.y;
                if (edge.weight < distance[y] && !inTree[y]) {
                    distance[y] = edge.weight;
                    parent[y] = x;
                }
                edge = edge.next;
            }
            x = 0;
            int dist = Integer.MAX_VALUE;
            for (int i = 0; i < nVertices; ++i) {
                if (distance[i] < dist && !inTree[i]) {
                    dist = distance[i];
                    x = i;
                }
            }
        }
        return Arrays.stream(distance).sum();
    }

    /**
     *  Shortest Path by Prim's Algorithm
     */
    public static class TreeNode {
        public int value;
        public List<TreeNode> children;
        public TreeNode(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }
    }
    public TreeNode shortestPathByPrim(int start) {
        resetStatus();
        prim(start);
        TreeNode[] nodes = new TreeNode[nVertices];
        for (int i = 0; i < nVertices; ++i) {
            nodes[i] = new TreeNode(i);
        }
        for (int i = 0; i < parent.length; ++i) {
            if (parent[i] != -1) {
                nodes[parent[i]].children.add(nodes[i]);
            }
        }
        return nodes[start];
    }

    /**
     *  Kruskal's Algorithm
     */
    public static class EdgePair {
        public int x;
        public int y;
        public int weight;
        public EdgePair(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }
    public EdgePair[] toEdgePairs() {
        List<EdgePair> pairs = new ArrayList<>();
        for (int x = 0; x < nVertices; ++x) {
            EdgeNode edge = edges[x];
            while (edge != null) {
                int y = edge.y;
                pairs.add(new EdgePair(x, y, edge.weight));
                edge = edge.next;
            }
        }
        return pairs.stream().toArray(EdgePair[]::new);
    }
    private int random(int from, int to) {
        return new Random().nextInt(to - from) + from;
    }
    private void swap(EdgePair[] pairs, int from, int to) {
        EdgePair temp = pairs[from];
        pairs[from] = pairs[to];
        pairs[to] = temp;
    }
    private void quickSortByWeight(EdgePair[] pairs, int from, int to) {
        if (from >= to) return;
        int flagIndex = random(from, to);
        EdgePair flag = pairs[flagIndex];
        swap(pairs, from, flagIndex);
        int i = from;
        int j = to - 1;
        while (i < j) {
            while (i < j && flag.weight < pairs[j].weight) --j;
            pairs[i] = pairs[j];
            while (i < j && pairs[i].weight <= flag.weight) ++i;
            pairs[j] = pairs[i];
        }
        pairs[i] = flag;
        quickSortByWeight(pairs, from, i);
        quickSortByWeight(pairs, i + 1, to);
    }
    public void kruskal() {
        resetStatus();
        SetUnion set = new SetUnion(nVertices);
        EdgePair[] pairs = toEdgePairs();
        quickSortByWeight(pairs, 0, pairs.length);
        for (int i = 0; i < pairs.length; ++i) {
            EdgePair pair = pairs[i];
            if (!set.sameComponet(pair.x, pair.y)) {
                System.out.println(String.format("Edge (%d, %d) in MST", pair.x, pair.y));
                set.unionSet(pairs[i].x, pairs[i].y);
            }
        }
    }

}
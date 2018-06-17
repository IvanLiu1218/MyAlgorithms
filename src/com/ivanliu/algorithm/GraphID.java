package com.ivanliu.algorithm;

public class GraphID extends Graph {

    private boolean hasCycle;

    public GraphID(int size) {
        super(size, false);
    }

    public void insertEdge(int x, int y) {
        super.insertEdge(x, y, false);
    }

    public String getVisitTime(int x, int y) {
        if (vertexStatus[y] == VertexStatus.UNDISCOVERED) {
            return "1ST";
        } else if (vertexStatus[y] != VertexStatus.PROCESSED) {
            if (y == parent[x]) {
                return "2ND";
            }
            return "1ST";
        }
        // = PROCESSED
        return "2ND";
    }

    @Override
    public void prepareForSearch() {
        super.prepareForSearch();
        this.hasCycle = false;
    }

    @Override
    public void process_edge(int x, int y) {
        System.out.println(String.format("EDGE: (%d, %d) %s", x, y, getVisitTime(x, y)));
        if (parent[x] != y) {
            hasCycle = true;
            System.out.println(String.format(">>>>>Cycle from %d to %d", y, x));
        }
    }

    public boolean hasCycle() {
        prepareForSearch();
        dfs_f();
        return hasCycle;
    }
}

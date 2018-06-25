package com.ivanliu.algorithm;

public class GraphUndirected extends Graph {

    public GraphUndirected(int size) {
        super(size, false);
    }

    public void insertEdge(int x, int y) {
        super.insertEdge(x, y, false);
    }
}

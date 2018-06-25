package com.ivanliu.algorithm;

public class GraphDirected extends Graph {

    public GraphDirected(int size) {
        super(size, true);
    }

    public void insertEdge(int x, int y) {
        super.insertEdge(x, y, true);
    }
}

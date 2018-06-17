package com.ivanliu.algorithm;

public class GraphD extends Graph {

    public GraphD(int size) {
        super(size, true);
    }

    public void insertEdge(int x, int y) {
        super.insertEdge(x, y, true);
    }
}

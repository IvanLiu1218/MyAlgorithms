package com.ivanliu.algorithms.utilities;

public class EdgeNode {
    public int y;
    public int weight;
    public EdgeNode next;

    public EdgeNode(int y, int  weight, EdgeNode next) {
        this.y = y;
        this.weight = weight;
        this.next = next;
    }

    public String toString(int x) {
        if (weight == 0) {
            return String.format("[%d->%d]", x, y);
        }
        return String.format("[%d->%d|w:%d]", x, y, weight);
    }
}
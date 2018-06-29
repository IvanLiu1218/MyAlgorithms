package com.ivanliu.algorithm;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class GraphAM implements IGraph {

    public int nVertices;
    public int[][] weight;
    public int[][] parent;

    public GraphAM(int size) {
        this.nVertices = size;
        this.weight = new int[size][];
        this.parent = new int[size][];
        for (int i = 0; i < size; ++i) {
            this.weight[i] = new int[size];
            this.parent[i] = new int[size];
            Arrays.fill(this.weight[i], Integer.MAX_VALUE);
            Arrays.fill(this.parent[i], -1);
        }
    }

    @Override
    public void resetStatus() {
        for (int i = 0; i < nVertices; ++i) {
            Arrays.fill(parent[i], -1);
        }
    }

    @Override
    public void insertEdge(int x, int y, boolean isDirected) {
        insertEdge(x, y, 0, isDirected);
    }

    public void insertEdge(int x, int y, int weight, boolean isDirected) {
        this.weight[x][y] = weight;
        if (!isDirected) {
            this.weight[y][x] = weight;
        }
    }

    public void floydWarshall() {
        resetStatus();
        for (int k = 0; k < nVertices; ++k) {
            for (int x = 0; x < nVertices; ++x) {
                for (int y = 0; y < nVertices; ++y) {
                    if (weight[x][k] == Integer.MAX_VALUE || weight[k][y] == Integer.MAX_VALUE) {
                        continue;
                    }
                    int throughK = weight[x][k] + weight[k][y];
                    if (throughK < weight[x][y]) {
                        weight[x][y] = throughK;
                        parent[x][y] = k;
                    }
                }
            }
        }
    }

    public Set<Integer> findShortestPath(int x, int y) {
        if (parent[x][y] == -1) {
            Set<Integer> path = new TreeSet<>();
            path.add(x);
            path.add(y);
            return path;
        }
        int k = parent[x][y];
        Set<Integer> path = new TreeSet<>();
        path.addAll(findShortestPath(x, k));
        path.addAll(findShortestPath(k, y));
        return path;
    }
}

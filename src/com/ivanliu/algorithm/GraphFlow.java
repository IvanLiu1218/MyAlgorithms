package com.ivanliu.algorithm;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static com.ivanliu.algorithm.Graph.VertexStatus;

public class GraphFlow implements IGraph {

    public static class EdgeNodeR {
        public int y;
        public int capacity;
        public int flow;
        public int residual;
        public EdgeNodeR next;

        public EdgeNodeR(int y, int c, int f, int r, EdgeNodeR next) {
            this.y = y;
            this.capacity = c;
            this.flow = f;
            this.residual = r;
            this.next = next;
        }
    }

    public boolean isDirected;
    public int nVertices;
    public EdgeNodeR[] edges;
    public VertexStatus[] vertexStatus;
    public int[] parent;

    public GraphFlow(int size) {
        this.isDirected = false;
        this.nVertices = size;
        this.edges = new EdgeNodeR[size];
        this.vertexStatus = new VertexStatus[size];
        this.parent = new int[size];
    }

    @Override
    public void resetStatus() {
        Arrays.fill(vertexStatus, VertexStatus.UNDISCOVERED);
        Arrays.fill(parent, -1);
    }

    @Override
    public void insertEdge(int x, int y, boolean isDirected) {
        insertEdge(x, y, 0);
    }

    public void insertEdge(int x, int y, int weight) {
        EdgeNodeR edge = new EdgeNodeR(y, weight, 0, weight, edges[x]);
        edges[x] = edge;
        edge = new EdgeNodeR(x, weight, 0, 0, edges[y]);
        edges[y] = edge;
    }

    public void bfs(int start) {
        resetStatus();
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offerLast(start);
        while (!queue.isEmpty()) {
            int x = queue.pollFirst();
            process_early(x);
            vertexStatus[x] = VertexStatus.DISCOVERED;
            EdgeNodeR edge = edges[x];
            while (edge != null) {
                int y = edge.y;
                if (vertexStatus[y] == VertexStatus.UNDISCOVERED) {
                    vertexStatus[y] = VertexStatus.DISCOVERED;
                    parent[y] = x;
                    queue.offerLast(y);
                    process_edge_bfs(x, y);
                } else if (vertexStatus[y] != VertexStatus.PROCESSED || isDirected) {
                    process_edge_bfs(x, y);
                }
                edge = edge.next;
            }
            process_later(x);
            vertexStatus[x] = VertexStatus.PROCESSED;
        }
    }

    public void process_early(int x) {
        System.out.println("PROC: " + x);
    }

    public void process_edge_bfs(int x, int y) {
        EdgeNodeR edge = findEdge(x, y);
        //edge.residual = edge.capacity;
        System.out.println(String.format("EDGE: (%d,%d) %d/%d/%d", x, y, edge.capacity, edge.flow, edge.residual));
        edge = findEdge(y, x);
        //edge.flow = edge.capacity;
        System.out.println(String.format("EDGE: (%d,%d) %d/%d/%d", y, x, edge.capacity, edge.flow, edge.residual));
    }

    public void process_later(int x) {
        System.out.println("LATE: " + x);
    }

    public EdgeNodeR findEdge(int x, int y) {
        EdgeNodeR edge = edges[x];
        while (edge != null) {
            if (edge.y == y) return edge;
            edge = edge.next;
        }
        return null;
    }

    public int pathVolume(int source, int sink) {
        if (parent[sink] == -1) return 0;
        EdgeNodeR edge = findEdge(parent[sink], sink);
        if (source == parent[sink]) {
            return edge.residual;
        } else {
            return Math.min(pathVolume(source, parent[sink]), edge.residual);
        }
    }

    public void augmentPath(int source, int sink, int volume) {
        if (source == sink) return;
        EdgeNodeR edge = findEdge(parent[sink], sink);
        edge.flow += volume;
        edge.residual -= volume;
        edge = findEdge(sink, parent[sink]);
        edge.residual += volume;
        augmentPath(source, parent[sink], volume);
    }

    public int netflow(int source, int sink) {
        resetStatus();
        bfs(source);
        int volume = pathVolume(source, sink);
        while (volume > 0) {
            augmentPath(source, sink, volume);
            resetStatus();
            bfs(source);
            volume = pathVolume(source, sink);
        }
        int flow = 0;
        EdgeNodeR edge = edges[source];
        while (edge != null) {
            flow += edge.residual;
            edge = edge.next;
        }
        return flow;
    }
}

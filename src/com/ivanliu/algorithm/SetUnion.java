package com.ivanliu.algorithm;

public class SetUnion {

    public int[] parent;
    public int[] size;
    public int n;

    public SetUnion(int n) {
        this.n = n;
        this.parent = new int[n];
        this.size = new int[n];
        init();
    }

    public int find(int e) {
        if (parent[e] == e) return e;
        return find(parent[e]);
    }

    public void init() {
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public boolean sameComponet(int e1, int e2) {
        return find(e1) == find(e2);
    }

    public int unionSet(int e1, int e2) {
        int r1 = find(e1);
        int r2 = find(e2);
        if (r1 == r2) return r1;
        if (size[r1] >= size[r2]) {
            size[r1] = size[r1] + size[r2];
            parent[r2] = r1;
            return r1;
        } else {
            size[r2] = size[r1] + size[r2];
            parent[r1] = r2;
            return r2;
        }
    }
}

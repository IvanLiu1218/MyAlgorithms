package com.ivanliu.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphTest {
    private GraphDirected gd1;
    private GraphUndirected gu1;

    @Before
    public void init() {
        gd1 = new GraphDirected(8);
        gd1.insertEdge(0, 1);
        gd1.insertEdge(0, 7);
        gd1.insertEdge(1, 2);
        gd1.insertEdge(1, 6);
        gd1.insertEdge(2, 3);
        gd1.insertEdge(2, 6);
        gd1.insertEdge(3, 5);
        gd1.insertEdge(3, 4);
        gd1.insertEdge(7, 6);

        gu1 = new GraphUndirected(8);
        gu1.insertEdge(0, 1);
        gu1.insertEdge(0, 7);
        gu1.insertEdge(1, 2);
        gu1.insertEdge(1, 6);
        gu1.insertEdge(2, 3);
        gu1.insertEdge(2, 6);
        gu1.insertEdge(3, 5);
        gu1.insertEdge(3, 4);
        gu1.insertEdge(7, 6);
    }

    @Test
    public void testBFS1() {
        Utilities.print(gd1);
        gd1.bfs(0);
        Utilities.printInfo(gd1);
    }

    @Test
    public void testBFS2() {
        Utilities.print(gu1);
        gu1.bfs(0);
        Utilities.printInfo(gu1);
    }

    @Test
    public void testDFS1() {
        Utilities.print(gd1);
        gd1.dfs();
        Utilities.printInfo(gd1);
    }

    @Test
    public void testDFS2() {
        Utilities.print(gu1);
        gu1.dfs();
        Utilities.printInfo(gu1);
    }

    @Test
    public void testFindShortestPath1() {
        String path = gd1.findShortestPath(1, 5);
        System.out.println(path);

        path = gd1.findShortestPath(7, 5);
        System.out.println(path);

        path = gd1.findShortestPath(0, 4);
        System.out.println(path);
    }

    @Test
    public void testFindShortestPath2() {
        String path = gu1.findShortestPath(1, 6);
        System.out.println(path);
    }

    @Test
    public void testConnectedComponent() {
        GraphUndirected gu = new GraphUndirected(8);
        gu.insertEdge(0, 1);
        gu.insertEdge(0, 7);
        gu.insertEdge(1, 2);
        gu.insertEdge(1, 6);
        gu.insertEdge(2, 3);
        gu.insertEdge(2, 6);
//        gu.insertEdge(3, 5);
        gu.insertEdge(3, 4);
        gu.insertEdge(7, 6);
        gu.connectedComponent();
        Utilities.printInfo(gu);
    }

    @Test
    public void testTwoColor() {
        gu1.twoColor();
        Utilities.printInfo(gu1);
    }

    @Test
    public void testHasCycle() {
        assertTrue(gu1.hasCycle());
        Utilities.printInfo(gu1);
    }
}
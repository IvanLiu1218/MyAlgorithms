package com.ivanliu.algorithm;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphTest {

    public GraphD initDGraph(GraphD g) {
        g.insertEdge(0, 1, true);
        g.insertEdge(0, 4, true);
        g.insertEdge(0, 7, true);
        g.insertEdge(1, 2, true);
        g.insertEdge(1, 6, true);
        g.insertEdge(2, 3, true);
        g.insertEdge(2, 5, true);
        g.insertEdge(4, 3, true);
        g.insertEdge(5, 3, true);
        g.insertEdge(5, 4, true);
        g.insertEdge(6, 5, true);
        g.insertEdge(7, 2, true);
        g.insertEdge(7, 6, true);
        return g;
    }

    public GraphD initDGraph1(GraphD g) {
        g.insertEdge(0, 1);
        g.insertEdge(1, 2);
        g.insertEdge(0, 7);
        g.insertEdge(7, 6);
        g.insertEdge(1, 6);
        g.insertEdge(2, 3);
        g.insertEdge(3, 4);
        g.insertEdge(3, 5);
        return g;
    }

    public GraphID initIDGraph(GraphID g) {
        g.insertEdge(0, 1);
        g.insertEdge(0, 4);
        g.insertEdge(0, 7);
        g.insertEdge(1, 2);
        g.insertEdge(1, 6);
        g.insertEdge(2, 3);
        g.insertEdge(2, 5);
        g.insertEdge(4, 3);
        g.insertEdge(5, 3);
        g.insertEdge(5, 4);
        g.insertEdge(6, 5);
        g.insertEdge(7, 2);
        g.insertEdge(7, 6);
        return g;
    }

    public GraphID initIDGraph1(GraphID g) {
        g.insertEdge(0, 1);
        g.insertEdge(1, 2);
        g.insertEdge(0, 7);
        g.insertEdge(7, 6);
        g.insertEdge(1, 6);
        g.insertEdge(2, 3);
        g.insertEdge(3, 4);
        g.insertEdge(3, 5);
        return g;
    }

    public GraphID initIDGraph2(GraphID g) {
        g.insertEdge(0, 1);
        g.insertEdge(1, 2);
        g.insertEdge(0, 7);
        g.insertEdge(7, 6);
        g.insertEdge(1, 6);
        g.insertEdge(3, 4);
        g.insertEdge(3, 5);
        return g;
    }

    public GraphID initIDGraph3(GraphID g) {
        g.insertEdge(0, 1);
        g.insertEdge(1, 2);
        g.insertEdge(0, 7);
        g.insertEdge(7, 6);
        g.insertEdge(1, 6);
        g.insertEdge(2, 5);
        g.insertEdge(3, 4);
        g.insertEdge(3, 5);
        g.insertEdge(2, 3);
        return g;
    }

    @Test
    public void testInsertEdge() {
        Graph g = initDGraph(new GraphD(8));
        System.out.println(g.toString());
    }

    @Test
    public void testBFS_D() {
        GraphD g = initDGraph(new GraphD(8));
        g.prepareForSearch();
        g.bfs(0);
        System.out.println(g.toString());
    }

    @Test
    public void testBFS_ID() {
        GraphID g = initIDGraph(new GraphID(8));
        g.prepareForSearch();
        g.bfs(0);
        System.out.println(g.toString());
    }

    @Test
    public void testIsConnected() {
        Graph g = initDGraph(new GraphD(8));
        assertTrue(g.isConnected(0, 5));
        assertTrue(g.isConnected(1, 4));
        assertFalse(g.isConnected(1, 7));
        assertFalse(g.isConnected(2, 6));
    }

    @Test
    public void testFindShortestPath() {
        Graph g = initDGraph(new GraphD(8));
        assertEquals("0->4->3", g.findShortestPath(0, 3));
        assertEquals("0->4", g.findShortestPath(0, 4));
        assertEquals("No path from 1 to 7", g.findShortestPath(1, 7));
        assertEquals("1->2->3", g.findShortestPath(1, 3));
        assertEquals("7->2->3", g.findShortestPath(7, 3));
    }

    @Test
    public void testBFSID() {  // indirected
        Graph g = initIDGraph1(new GraphID(8));
        g.prepareForSearch();
        g.bfs(0);
    }

    @Test
    public void testConnectComponent() {
        Graph g = initIDGraph2(new GraphID(8));
        g.connectedComponent();
        System.out.println(g.toString());
    }

    @Test
    public void testTwoColor() {
        GraphTwoColor g = (GraphTwoColor) initIDGraph1(new GraphTwoColor(8));
        g.twoColor();
        System.out.println(g.toString());

        g = (GraphTwoColor) initIDGraph2(new GraphTwoColor(8));
        g.twoColor();
        System.out.println(g.toString());

        g = (GraphTwoColor) initIDGraph3(new GraphTwoColor(8));
        g.twoColor();
        System.out.println(g.toString());
    }

    @Test
    public void testDFS() {
        Graph g = initDGraph(new GraphD(8));
        g.prepareForSearch();
        g.dfs(0);
    }

    @Test
    public void testDFS_F_D() {
        GraphD g = initDGraph1(new GraphD(8));
        g.dfs_f();
        System.out.println(g.toString());
    }

    @Test
    public void testDFS_F_ID() {
        GraphID g = initIDGraph1(new GraphID(8));
        g.dfs_f();
        System.out.println(g.toString());
    }

    @Test
    public void testHasCycle() {
        GraphID g = initIDGraph1(new GraphID(8));
        assertTrue(g.hasCycle());
    }

}
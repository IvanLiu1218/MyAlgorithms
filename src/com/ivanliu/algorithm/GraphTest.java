package com.ivanliu.algorithm;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphTest {

    public Graph createDGraph() {
        Graph g = new Graph(8, true);
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

    public Graph createIDGraph1() {
        Graph g = new Graph(8, false);
        g.insertEdge(0, 1, false);
        g.insertEdge(1, 2, false);
        g.insertEdge(0, 7, false);
        g.insertEdge(7, 6, false);
        g.insertEdge(1, 6, false);
        g.insertEdge(2, 5, false);
        g.insertEdge(3, 4, false);
        g.insertEdge(3, 5, false);
        return g;
    }

    public Graph createIDGraph2() {
        Graph g = new Graph(8, false);
        g.insertEdge(0, 1, false);
        g.insertEdge(1, 2, false);
        g.insertEdge(0, 7, false);
        g.insertEdge(7, 6, false);
        g.insertEdge(1, 6, false);
//        g.insertEdge(2, 5, false);
        g.insertEdge(3, 4, false);
        g.insertEdge(3, 5, false);
        return g;
    }

    @Test
    public void testInsertEdge() {
        Graph g = createDGraph();
        System.out.println(Utilities.toString(g));
    }

    @Test
    public void testBFS() {
        Graph g = createDGraph();
        g.prepareForSearch();
        g.bfs(0);
    }

    @Test
    public void testDFS() {
        Graph g = createDGraph();
        g.prepareForSearch();
        g.dfs(0);
    }

    @Test
    public void testIsConnected() {
        Graph g = createDGraph();
        assertTrue(g.isConnected(0, 5));
        assertTrue(g.isConnected(1, 4));
        assertFalse(g.isConnected(1, 7));
        assertFalse(g.isConnected(2, 6));
    }

    @Test
    public void testFindShortestPath() {
        Graph g = createDGraph();
        assertEquals("0->4->3", g.findShortestPath(0, 3));
        assertEquals("0->4", g.findShortestPath(0, 4));
        assertEquals("No path from 1 to 7", g.findShortestPath(1, 7));
        assertEquals("1->2->3", g.findShortestPath(1, 3));
        assertEquals("7->2->3", g.findShortestPath(7, 3));
    }

    @Test
    public void testBFSID() {  // indirected
        Graph g = createIDGraph1();
        g.prepareForSearch();
        g.bfs(0);
    }

    @Test
    public void testConnectComponent() {
        Graph g = createIDGraph2();
        g.connectedComponent();
        System.out.println(Utilities.toString(g));
    }

//    @Test
//    public void testDFSID() {
//        Graph g = createIDGraph1();
//        g.dfs(0);
//    }

}
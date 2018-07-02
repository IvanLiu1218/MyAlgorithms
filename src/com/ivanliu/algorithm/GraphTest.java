package com.ivanliu.algorithm;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.ivanliu.algorithm.GraphUndirected.TreeNode;
import static org.junit.Assert.assertEquals;
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

    @Test
    public void testGetAVs() {
        GraphUndirected gu = new GraphUndirected(6);
        gu.insertEdge(0, 5);
        gu.insertEdge(0, 1);
        gu.insertEdge(1, 2);
        gu.insertEdge(1, 4);
        gu.insertEdge(2, 3);
        gu.insertEdge(4, 3);
        int[] result = gu.getAVs();
        Utilities.printInfo(gu);
        assertEquals("[0, 1]", Arrays.toString(result));
    }

    @Test
    public void testTopSort() {
        GraphDirected gd = new GraphDirected(7);
        gd.insertEdge(0, 1);
        gd.insertEdge(0, 2);
        gd.insertEdge(1, 2);
        gd.insertEdge(1, 3);
        gd.insertEdge(2, 4);
        gd.insertEdge(2, 5);
        gd.insertEdge(4, 3);
        gd.insertEdge(5, 4);
        gd.insertEdge(6, 5);
        gd.insertEdge(6, 0);
        int[] result = gd.topSort();
        Utilities.printInfo(gd);
        assertEquals("[6, 0, 1, 2, 5, 4, 3]", Arrays.toString(result));
    }

    @Test
    public void testStrongComponents() {
        GraphDirected gd = new GraphDirected(8);
        gd.insertEdge(0, 1);
        gd.insertEdge(1, 2);
        gd.insertEdge(1, 3);
        gd.insertEdge(1, 4);
        gd.insertEdge(2, 0);
        gd.insertEdge(3, 0);
        gd.insertEdge(3, 5);
        gd.insertEdge(3, 7);
        gd.insertEdge(4, 5);
        gd.insertEdge(5, 6);
        gd.insertEdge(6, 4);
        gd.insertEdge(7, 5);
        List<int[]> result = gd.strongComponents();
        Utilities.printInfo(gd);
        for (int i = 0; i < result.size(); ++i) {
            System.out.println(Arrays.toString(result.get(i)));
        }
    }

    @Test
    public void testPrim() {
        GraphUndirected gu = new GraphUndirected(8);
        gu.insertEdge(0, 1, 2);
        gu.insertEdge(0, 2, 3);
        gu.insertEdge(0, 3, 4);
        gu.insertEdge(1, 2, 2);
        gu.insertEdge(1, 3, 3);
        gu.insertEdge(1, 4, 1);
        gu.insertEdge(3, 5, 1);
        gu.insertEdge(3, 7, 5);
        gu.insertEdge(4, 5, 2);
        gu.insertEdge(4, 6, 4);
        gu.insertEdge(5, 6, 3);
        gu.insertEdge(5, 7, 6);
        int sum = gu.prim(0);
        Utilities.printInfo(gu);
        assertEquals(16, sum);
    }

    @Test
    public void testShortestPathByPrim() {
        GraphUndirected gu = new GraphUndirected(8);
        gu.insertEdge(0, 1, 2);
        gu.insertEdge(0, 2, 3);
        gu.insertEdge(0, 3, 4);
        gu.insertEdge(1, 2, 2);
        gu.insertEdge(1, 3, 3);
        gu.insertEdge(1, 4, 1);
        gu.insertEdge(3, 5, 1);
        gu.insertEdge(3, 7, 5);
        gu.insertEdge(4, 5, 2);
        gu.insertEdge(4, 6, 4);
        gu.insertEdge(5, 6, 3);
        gu.insertEdge(5, 7, 6);
        TreeNode root = gu.shortestPathByPrim(0);
        Utilities.printInfo(gu);
        Utilities.print(root);
    }

    @Test
    public void testKruskal() {
        GraphUndirected gu = new GraphUndirected(8);
        gu.insertEdge(0, 1, 2);
        gu.insertEdge(0, 2, 3);
        gu.insertEdge(0, 3, 4);
        gu.insertEdge(1, 2, 2);
        gu.insertEdge(1, 3, 3);
        gu.insertEdge(1, 4, 1);
        gu.insertEdge(3, 5, 1);
        gu.insertEdge(3, 7, 5);
        gu.insertEdge(4, 5, 2);
        gu.insertEdge(4, 6, 4);
        gu.insertEdge(5, 6, 3);
        gu.insertEdge(5, 7, 6);
        gu.kruskal();
        Utilities.printInfo(gu);
    }

    @Test
    public void testDijkstra() {
        GraphDirected gd = new GraphDirected(8);
        gd.insertEdge(0, 1, 2);
        gd.insertEdge(1, 2, 2);
        gd.insertEdge(1, 3, 3);
        gd.insertEdge(2, 0, 3);
        gd.insertEdge(3, 0, 4);
        gd.insertEdge(1, 4, 1);
        gd.insertEdge(3, 5, 1);
        gd.insertEdge(3, 7, 5);
        gd.insertEdge(4, 5, 2);
        gd.insertEdge(7, 5, 6);
        gd.insertEdge(5, 6, 3);
        gd.insertEdge(6, 4, 4);
        gd.dijkstra(0);
        Utilities.printInfo(gd);
    }

    @Test
    public void testFloydWarshall() {
        GraphAM g = new GraphAM(8);
        g.insertEdge(0, 1, 2, false);
        g.insertEdge(0, 2, 3, false);
        g.insertEdge(0, 3, 4, false);
        g.insertEdge(1, 2, 2, false);
        g.insertEdge(1, 3, 3, false);  // weight: 3 <--> 0
        g.insertEdge(1, 4, 1, false);
        g.insertEdge(3, 5, 1, false);
        g.insertEdge(3, 7, 5, false);
        g.insertEdge(4, 5, 2, false);
        g.insertEdge(4, 6, 4, false);
        g.insertEdge(5, 6, 3, false);
        g.insertEdge(5, 7, 6, false);
        g.floydWarshall();
        Utilities.printInfo(g);
        Set<Integer> path = g.findShortestPath(0, 6);
        System.out.println(Arrays.toString(path.stream().toArray()));
        path = g.findShortestPath(0, 3);
        System.out.println(Arrays.toString(path.stream().toArray()));
        path = g.findShortestPath(1, 6);
        System.out.println(Arrays.toString(path.stream().toArray()));
    }

    @Test
    public void testGraphFlow() {
        GraphFlow g = new GraphFlow(7);
        g.insertEdge(0, 1, 5);
        g.insertEdge(0, 6, 12);
        g.insertEdge(1, 2, 7);
        g.insertEdge(1, 3, 9);
        g.insertEdge(2, 3, 3);
        g.insertEdge(2, 4, 5);
        g.insertEdge(3, 5, 3);
        g.insertEdge(3, 6, 4);
        g.insertEdge(4, 5, 2);
        g.insertEdge(5, 6, 7);
        int flow = g.netflow(0, 4);
        Utilities.printInfo(g);
        assertEquals(7, flow);

        g = new GraphFlow(7);
        g.insertEdge(0, 1, 5);
        g.insertEdge(0, 6, 12);
        g.insertEdge(1, 2, 7);
        g.insertEdge(1, 3, 9);
        g.insertEdge(2, 3, 3);
        g.insertEdge(2, 4, 5);
        g.insertEdge(3, 5, 3);
        g.insertEdge(3, 6, 4);
        g.insertEdge(4, 5, 10);  // 2 --> 10
        g.insertEdge(5, 6, 7);
        flow = g.netflow(0, 4);
        Utilities.printInfo(g);
        assertEquals(15, flow);

        g = new GraphFlow(7);
        g.insertEdge(0, 1, 5);
        g.insertEdge(0, 6, 12);
        g.insertEdge(1, 2, 7);
        g.insertEdge(1, 3, 9);
        g.insertEdge(2, 3, 3);
        g.insertEdge(2, 4, 5);
        g.insertEdge(3, 5, 3);
        g.insertEdge(3, 6, 4);
        g.insertEdge(4, 5, 10);  // 2 --> 10
        g.insertEdge(5, 6, 1);   // 7 --> 1
        flow = g.netflow(0, 4);
        Utilities.printInfo(g);
        assertEquals(9, flow);
    }
}
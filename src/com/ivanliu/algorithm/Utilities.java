package com.ivanliu.algorithm;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.ivanliu.algorithm.Graph.EdgeNode;
import static com.ivanliu.algorithm.GraphUndirected.TreeNode;

public class Utilities {

    private static StringBuilder sb;

    public static void print(TreeNode root) {
        sb = new StringBuilder();
        dfs(root, new String());
        System.out.println(sb.toString());
    }
    private static void dfs(TreeNode node, String path) {
        if (node == null || node.children.size() == 0) {
            sb.append(new String(path + "->" + node.value + "\n"));
            return;
        }
        for (int i = 0; i < node.children.size(); ++i) {
            dfs(node.children.get(i), path + "->" + node.value);
        }
    }

    public static void print(Graph g) {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------\n");
        sb.append(String.format("Vertices: %3d\n", g.nVertices));
        sb.append(String.format("Edges:    %3d\n", g.nEdges));
        sb.append("----------------------------\n");
        for (int i = 0; i < g.nVertices; ++i) {
            EdgeNode edge = g.edges[i];
            sb.append(String.format("[%d]", i));
            while (edge != null) {
                sb.append(String.format(" [%d->%d]", i, edge.y));
                edge = edge.next;
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void printInfo(Graph g) {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertex:   ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3d", i));
        }
        sb.append("\n");
        sb.append("Parent:   ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3d", g.parent[i]));
        }
        sb.append("\n");
        sb.append("Component:");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3d", g.component[i]));
        }
        sb.append("\n");
        sb.append("EntryTime:");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3d", g.time_entry[i]));
        }
        sb.append("\n");
        sb.append("ExitTime: ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3d", g.time_exit[i]));
        }
        System.out.println(sb.toString());
    }

    public static void printInfo(GraphUndirected g) {
        printInfo((Graph) g);
        StringBuilder sb = new StringBuilder();
        sb.append("Color:    ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3s", g.color[i]));
        }
        sb.append("\n");
        sb.append("Ancestor: ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3d", g.reachable_ancestor[i]));
        }
        sb.append("\n");
        sb.append("OutDegree:");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3d", g.tree_out_degree[i]));
        }
        sb.append("\n");
        sb.append("VType:    ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3s", g.vertexType[i]));
        }
        sb.append("\n");
        sb.append("InTree:   ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3s", g.inTree[i] ? "T" : "F"));
        }
        sb.append("\n");
        sb.append("Distance: ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3s", g.distance[i] != Integer.MAX_VALUE ? g.distance[i] : "X"));
        }
        System.out.println(sb.toString());
    }

    public static void printInfo(GraphDirected g) {
        printInfo((Graph) g);
        StringBuilder sb = new StringBuilder();
        sb.append("Oldest:   ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3d", g.low[i]));
        }
        sb.append("\n");
        sb.append("ComId:    ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3d", g.scc[i]));
        }
        sb.append("\n");
        sb.append("InTree:   ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3s", g.inTree[i] ? "T" : "F"));
        }
        sb.append("\n");
        sb.append("Distance: ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3s", g.distance[i] != Integer.MAX_VALUE ? g.distance[i] : "X"));
        }
        System.out.println(sb.toString());
    }

    public static void printInfo(GraphAM g) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb1.append("Weight: \n");
        sb2.append("Parent: \n");
        for (int i = 0; i < g.nVertices; ++i) {
            for (int j = 0; j < g.nVertices; ++j) {
                sb1.append(String.format("%3s", g.weight[i][j] != Integer.MAX_VALUE ? g.weight[i][j] : "X"));
                sb2.append(String.format("%3d", g.parent[i][j]));
            }
            sb1.append("\n");
            sb2.append("\n");
        }
        System.out.println(sb1.toString());
        System.out.println(sb2.toString());
    }

    public static void printInfo(GraphFlow g) {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertex:   ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3d", i));
        }
        sb.append("\n");
        sb.append("Parent:   ");
        for (int i = 0; i < g.nVertices; ++i) {
            sb.append(String.format("%3d", g.parent[i]));
        }
        System.out.println(sb.toString());
    }

    /**
     * --------------------------------------
     *  Others
     * --------------------------------------
     */
    // get random number between [x, y)
    public static int random(int x, int y) {
        return new Random().nextInt(y - x) + x;
    }

    public static Pair<Integer, Integer> randomPair(int x, int y) {
        int i;
        int j;
        do {
            i = random(x, y);
            j = random(x, y);
            if (i > j) {
                int t = i;
                i = j;
                j = t;
            }
        } while (i == j);
        return new Pair<>(i, j);
    }

    private static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    /**
     *  -------------------------------------------
     *   Greatest Common Divisor (GCD)
     *  -------------------------------------------
     */
    public static int gcd(int a, int b) {
        if (a % b == 0) return b;
        int r = a % b;
        return gcd(b, r);
    }

    /**
     *  --------------------------------------------
     *   Least Common Multiple (LCM)
     *  --------------------------------------------
     */
    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    /**
     *  -------------------------------------------
     *  Permutation
     *  -------------------------------------------
     */
    public static int[] randomPermutation(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = i;
        }
        for (int i = 0; i < n - 1; ++i) {
            int x = i;
            int y = random(i, n);  // random[i, n)
            swap(array, x, y);
        }
        return array;
    }

    public static void reverse(int[] array, int begin, int end) {
        int i = begin;
        int j = end - 1;
        while (i < j) {
            swap(array, i++, j--);
        }
    }

    public static boolean nextPermutation(int[] array, int begin, int end) {
        if (begin == end || begin + 1 == end) return false;
        for (int i = end - 2; begin <= i; --i) {
            int j = i + 1;
            if (array[i] >= array[j]) continue;
            for (int k = end - 1; j <= k; --k) {
                if (array[i] >= array[k]) continue;
                swap(array, i, k);
                reverse(array, j, end);
                return true;
            }
        }
        reverse(array, begin, end);
        return false;
    }

    public static boolean prevPermutation(int[] array, int begin, int end) {
        if (begin == end || begin + 1 == end) return false;
        for (int i = end - 2; begin <= i; --i) {
            int j = i + 1;
            if (array[i] <= array[j]) continue;
            for (int k = end - 1; j <= k; --k) {
                if (array[i] <= array[k]) continue;
                swap(array, i, k);
                reverse(array, j, end);
                return true;
            }
        }
        reverse(array, begin, end);
        return false;
    }

    public static List<int[]> allPermutation(int[] array) {
        Arrays.sort(array);
        List<int[]> all = new ArrayList<>();
        do {
            int[] copy = new int[array.length];
            System.arraycopy(array, 0, copy, 0, array.length);
            all.add(copy);
        } while (Utilities.nextPermutation(array, 0, array.length));
        return all;
    }

    /**
     *  -------------------------------------------
     *  Subset
     *  -------------------------------------------
     */

    /**
     *  -------------------------------------------
     *   Partition
     *  -------------------------------------------
     */
    /**
     *  -------------------------------------------
     *   Integer Partition
     *  -------------------------------------------
     */
    public static int integerPartition(int n, int k) {
        if (n < 1 || k < 1) return -1; // ERROR
        if (n == 1 || k == 1) return 1;
        if (n < k) return integerPartition(n, n);
        if (n == k) return 1 + integerPartition(n, k - 1);
        // if n > k
        return integerPartition(n - k, k) + integerPartition(n, k - 1);
    }

    private static List<List<Integer>> allIntegerPartitions;
    public static List<List<Integer>> integerPartition(int n) {
        allIntegerPartitions = new ArrayList<>();
        integerPartition(n, n, new ArrayList<>());
        return allIntegerPartitions;
    }

    private static int integerPartition(int n, int k, List<Integer> list) {
        if (n < 1 || k < 1) return -1; // ERROR
        if (n == 1) {
            List<Integer> copy = new ArrayList<>(list);
            copy.add(1);
            allIntegerPartitions.add(copy);
            return 1;
        } else if (k == 1) {
            List<Integer> copy = new ArrayList<>(list);
            for (int i = 0; i < n; ++i) {
                copy.add(1);
            }
            allIntegerPartitions.add(copy);
            return 1;
        } else if (n < k) {
            return integerPartition(n, n, list);
        } else if (n == k) {
            List<Integer> copy = new ArrayList<>(list);
            copy.add(n);
            allIntegerPartitions.add(copy);
            return 1 + integerPartition(n, k - 1, list);
        } else {  // n > k
            list.add(k);
            int num1 = integerPartition(n - k, k, list);
            list.remove(new Integer(k));
            int num2 = integerPartition(n, k - 1, list);
            return num1 + num2;
        }
    }

    /**
     *  ------------------------------------------
     *   Set Partition
     *  ------------------------------------------
     */
    public static int setPartition(int n) {
        int num = 0;
        for (int i = 1; i <= n; ++i) {
            num += setPartition(n, i);
        }
        return num;
    }

    public static int setPartition(int n, int k) {
        if (n < 1 || k == 0) return -1; // ERROR
        if (k == 1 || n == k) return 1;
        return setPartition(n - 1, k - 1) + k * setPartition(n - 1, k);
    }

//    private static List<List<List<Integer>>> allSetPartitons;
//    public static List<List<List<Integer>>> setPartition(int[] nums, int k) {
//        allSetPartitons = new ArrayList<>();
//        setPartition(nums, nums.length, k, new ArrayList<>());
//        return allSetPartitons;
//    }
//    public static int setPartition(int[] nums, int len, int k, List<Integer> list) {
//        if (nums == null || len < 1 || k == 0 ) return -1; // ERROR
//        if (k == 1) {
//            List<List<Integer>> copy = new ArrayList<>();
//            copy.add(list);
//            List<Integer> l = new ArrayList<>();
//            for (int i = 0; i < len; ++i) {
//                l.add(nums[i]);
//            }
//            copy.add(l);
//            allSetPartitons.add(copy);
//            return 1;
//        } else if (len == k) {
//            List<List<Integer>> ll = new ArrayList<>();
//            for (int i = 0; i < len; ++i) {
//                List<Integer> l = new ArrayList<>();
//                l.add(nums[i]);
//                ll.add(l);
//            }
//            for (int x = 0; x < k; ++x) {
//                List<List<Integer>> copy = new ArrayList<>();
//                for (int i = 0; i < ll.size(); ++i) {
//                    List<Integer> l = new ArrayList<>();
//                    if (i == x) {
//                        l.addAll(list);
//                    }
//                    for (int j = 0; j < ll.get(i).size(); ++j) {
//                        l.add(ll.get(i).get(j));
//                    }
//                    copy.add(l);
//                }
//                allSetPartitons.add(copy);
//            }
//            return 1;
//        } else {
//            list.add(nums[len - 1]);
//            int num1 = setPartition(nums, len - 1, k - 1, list);
//            int num2 = k * setPartition(nums, len - 1, k, list);
//            return num1 + num2;
//        }
//    }
//
//    public static List<List<List<Integer>>> setPartition(int[] nums) {
//        allSetPartitons = new ArrayList<>();
//        for (int i = 1; i <= nums.length; ++i) {
//            setPartition(nums, nums.length, i, new ArrayList<>());
//        }
//        return allSetPartitons;
//    }
    private static List<List<List<Integer>>> allSetPartitions;
    public static List<List<List<Integer>>> setPartition(int[] nums, int k) {
        allSetPartitions = new ArrayList<>();
        setPartition(nums, k, 0, new ArrayList<>());
        return allSetPartitions;
    }
    public static int setPartition(int[] nums, int k, int begin, List<List<Integer>> list) {
        int n = nums.length - begin;
        if (n < 1 || k < 1) return -1; // ERROR
        if (k == 1) {
            List<Integer> set = new ArrayList<>();
            for (int i = begin; i < nums.length; ++i) {
                set.add(nums[i]);
            }
            List<List<Integer>> copyList = copyLList(list);
            copyList.add(set);
            allSetPartitions.add(copyList);
//            copyList.stream().forEach(l -> System.out.print(Arrays.toString(l.toArray())));
//            System.out.println(' ');
            return 1;
        } else if (n == k) {
            List<List<Integer>> copyList = copyLList(list);
            for (int i = begin; i < nums.length; ++i) {
                List<Integer> set = new ArrayList<>();
                set.add(nums[i]);
                copyList.add(set);
            }
            allSetPartitions.add(copyList);
//            copyList.stream().forEach(l -> System.out.print(Arrays.toString(l.toArray())));
//            System.out.println(' ');
            return 1;
        } else {
            // a[1] is one subset
            List<Integer> set = new ArrayList<>();
            set.add(nums[begin]);
            list.add(set);
            int num1 = setPartition(nums, k - 1, begin + 1, list);
            list.stream().forEach(l -> System.out.println(Arrays.toString(l.toArray())));
            list.remove(set);
            int num2 = setPartition(nums, k, begin + 1, list);
            return num1 + k * num2;
        }
    }
    public static List<Integer> copyList(int[] nums, int begin) {
        List<Integer> copyList = new ArrayList<>();
        for (int i = begin; i < nums.length; ++i) {
            copyList.add(nums[i]);
        }
        return copyList;
    }
    public static List<List<Integer>> copyLList(List<List<Integer>> list) {
        List<List<Integer>> copyList = new ArrayList<>();
        for (int i = 0; i < list.size(); ++i) {
            copyList.add(new ArrayList<>(list.get(i)));
        }
        return copyList;
    }
//    private List<List<List<Integer>>> allKSets;
//    public List<List<List<Integer>>> kSet(int[] nums, int k) {
//    }
//    public void setPartition(int[] nums, int k, int begin) {
//
//    }

    /**
     * ------------------------------------------
     *  Linked List
     * ------------------------------------------
     */
    public static boolean hasCycle(ListNode node) {
        if (node == null) return false;
        if (node.next == null) return false;
        ListNode p1 = node;
        ListNode p2 = node.next;
        while (p1 != null && p2 != null) {
            if (p1 == p2) return true;
            p1 = p1.next;
            if (p2.next == null) return false;
            else p2 = p2.next.next;
        }
        return false;
    }

    /**
     * ------------------------------------------
     *  Tree
     * ------------------------------------------
     */
    private static List<List<TreeNodeBinary>> dfsPaths;
    public static List<List<TreeNodeBinary>> dfs(TreeNodeBinary node) {
        dfsPaths = new ArrayList<>();
        dfs(node, new ArrayList<>());
        return dfsPaths;
    }
    private static void dfs(TreeNodeBinary node, List<TreeNodeBinary> path) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            List<TreeNodeBinary> copy = new ArrayList<>(path);
            copy.add(node);
            dfsPaths.add(copy);
            return;
        }
        path.add(node);
        if (node.left != null) {
            dfs(node.left, path);
        }
        if (node.right != null) {
            dfs(node.right, path);
        }
        path.remove(node);
    }
    public static List<TreeNodeBinary> preOrder(TreeNodeBinary node) {
        List<TreeNodeBinary> nodeList = new ArrayList<>();
        preOrder(node, nodeList);
        return nodeList;
    }
    private static void preOrder(TreeNodeBinary node, List<TreeNodeBinary> nodeList) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            nodeList.add(node);
            return;
        }
        nodeList.add(node);
        if (node.left != null) {
            preOrder(node.left, nodeList);
        }
        if (node.right != null) {
            preOrder(node.right, nodeList);
        }
    }

    public static List<TreeNodeBinary> inOrder(TreeNodeBinary node) {
        List<TreeNodeBinary> nodeList = new ArrayList<>();
        inOrder(node, nodeList);
        return nodeList;
    }
    private static void inOrder(TreeNodeBinary node, List<TreeNodeBinary> nodeList) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            nodeList.add(node);
            return;
        }
        if (node.left != null) {
            inOrder(node.left, nodeList);
        }
        nodeList.add(node);
        if (node.right != null) {
            inOrder(node.right, nodeList);
        }
    }

    public static List<TreeNodeBinary> postOrder(TreeNodeBinary node) {
        List<TreeNodeBinary> nodeList = new ArrayList<>();
        postOrder(node, nodeList);
        return nodeList;
    }
    private static void postOrder(TreeNodeBinary node, List<TreeNodeBinary> nodeList) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            nodeList.add(node);
            return;
        }
        if (node.left != null) {
            postOrder(node.left, nodeList);
        }
        if (node.right != null) {
            postOrder(node.right, nodeList);
        }
        nodeList.add(node);
    }

}

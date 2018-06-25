package com.ivanliu.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.ivanliu.algorithm.Graph.EdgeNode;

public class Utilities {

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
        System.out.println(sb.toString());
    }

    // get random number between [x, y)
    public static int random(int x, int y) {
        return new Random().nextInt(y - x) + x;
    }

    private static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
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
     *  Partition
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
        } else {  // n < k
            list.add(k);
            int num1 = integerPartition(n - k, k, list);
            list.remove(new Integer(k));
            int num2 = integerPartition(n, k - 1, list);
            return num1 + num2;
        }
    }

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

    private static List<List<List<Integer>>> allSetPartitons;
    public static List<List<List<Integer>>> setPartition(int[] nums, int k) {
        allSetPartitons = new ArrayList<>();
        setPartition(nums, nums.length, k, new ArrayList<>());
        return allSetPartitons;
    }
    public static int setPartition(int[] nums, int len, int k, List<Integer> list) {
        if (nums == null || len < 1 || k == 0 ) return -1; // ERROR
        if (k == 1) {
            List<List<Integer>> copy = new ArrayList<>();
            copy.add(list);
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < len; ++i) {
                l.add(nums[i]);
            }
            copy.add(l);
            allSetPartitons.add(copy);
            return 1;
        } else if (len == k) {
            List<List<Integer>> ll = new ArrayList<>();
            for (int i = 0; i < len; ++i) {
                List<Integer> l = new ArrayList<>();
                l.add(nums[i]);
                ll.add(l);
            }
            for (int x = 0; x < k; ++x) {
                List<List<Integer>> copy = new ArrayList<>();
                for (int i = 0; i < ll.size(); ++i) {
                    List<Integer> l = new ArrayList<>();
                    if (i == x) {
                        l.addAll(list);
                    }
                    for (int j = 0; j < ll.get(i).size(); ++j) {
                        l.add(ll.get(i).get(j));
                    }
                    copy.add(l);
                }
                allSetPartitons.add(copy);
            }
            return 1;
        } else {
            list.add(nums[len - 1]);
            int num1 = setPartition(nums, len - 1, k - 1, list);
            int num2 = k * setPartition(nums, len - 1, k, list);
            return num1 + num2;
        }
    }

    public static List<List<List<Integer>>> setPartition(int[] nums) {
        allSetPartitons = new ArrayList<>();
        for (int i = 1; i <= nums.length; ++i) {
            setPartition(nums, nums.length, i, new ArrayList<>());
        }
        return allSetPartitons;
    }
}

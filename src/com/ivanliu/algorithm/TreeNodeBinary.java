package com.ivanliu.algorithm;

public class TreeNodeBinary {
    public int value;
    public TreeNodeBinary left;
    public TreeNodeBinary right;
    public TreeNodeBinary(int value, TreeNodeBinary left, TreeNodeBinary right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
    public TreeNodeBinary(int value) {
        this(value, null, null);
    }
}

package com.ivanliu.algorithm;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public int value;
    public List<TreeNode> children;
    public TreeNode(int value) {
        this.value = value;
        this.children = new ArrayList<>();
    }
}

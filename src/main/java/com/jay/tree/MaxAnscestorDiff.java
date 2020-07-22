package com.jay.tree;

import java.util.Stack;

public class MaxAnscestorDiff {

    public int maxAncestorDiff(TreeNode root) {

        return maxHelper(root, new Stack<TreeNode>());
    }

    public int maxHelper(TreeNode root, Stack<TreeNode> ancestors){

        if(root == null)
            return 0;

        ancestors.push(root);

        int left = maxHelper(root.left, ancestors);
        int right = maxHelper(root.right, ancestors);

        int max = Math.max(left, right);

        ancestors.pop();

        for(TreeNode ancestor: ancestors){
            var val = Math.abs(ancestor.val - root.val);
            max = Math.max(max, val);
        }

        return max;
    }

    public static void main(String[] args) {

        MaxAnscestorDiff ob = new MaxAnscestorDiff();
        TreeNode root = TreeUtils.build(new Integer[]{8, 1, 15, 5, 3, null, 3, 14, 2});
        int res = ob.maxAncestorDiff(root);
        System.out.println("res: " + res);
    }
}

package com.jay.tree;

import java.util.TreeMap;

public class MaxAnscestorDiff {

    public int maxAncestorDiff(TreeNode root) {

        return maxHelper(root, new TreeMap<Integer, Integer>());
    }

    public int maxHelper(TreeNode root, TreeMap<Integer, Integer> ancestors){

        if(root == null)
            return 0;

        int count = ancestors.getOrDefault(root.val, 0);
        ancestors.put(root.val, 1 + count);

        int left = maxHelper(root.left, ancestors);
        int right = maxHelper(root.right, ancestors);

        int max = Math.max(left, right);

        if(count == 0) {
            ancestors.remove(root.val);
        }else{
            ancestors.put(root.val, count);
        }

        if(!ancestors.isEmpty()) {
            var small = ancestors.firstKey();
            var big = ancestors.lastKey();

            var val = Math.max(Math.abs(small - root.val), Math.abs(big - root.val));
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

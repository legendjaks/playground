package com.jay.tree;

import java.util.*;
import java.util.stream.Collectors;

public class DeleteNodes {

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {

        Set<Integer> deleteSet = new HashSet<>();
        deleteSet.addAll(Arrays.stream(to_delete).boxed().collect(Collectors.toList()));

        List<TreeNode> res = new ArrayList<>();
        TreeNode node = delete(root, deleteSet, res);
        if(node != null)
            res.add(node);

        return res;
    }

    public TreeNode delete(TreeNode node, Set<Integer> deleteSet, List<TreeNode> res){

        if(node == null) return null;

        node.left = delete(node.left, deleteSet, res);
        node.right = delete(node.right, deleteSet, res);

        if(deleteSet.contains(node.val)){

            if(node.left != null)
                res.add(node.left);

            if(node.right != null)
                res.add(node.right);

            return null;
        }

        return node;
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{1,2,3,4,5,6,7};
        TreeNode root = TreeUtils.build(data);
        //TreeUtils.printInOrder(root);

        DeleteNodes ob = new DeleteNodes();
        List<TreeNode> res = ob.delNodes(root, new int[]{3, 5});
        res.forEach(n -> System.out.print(n.val + " "));
    }
}

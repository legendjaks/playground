package com.jay.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtils {

    public static TreeNode build(Integer[] data) {

        TreeNode root = new TreeNode(data[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        for (int index = 1; index < data.length; index+=2) {

            TreeNode current = queue.poll();

            if(data[index] != null){
                TreeNode left = new TreeNode(data[index]);
                current.left = left;
                queue.offer(left);
            }

            if(index != data.length -1 && data[index+1] != null){
                TreeNode right = new TreeNode(data[index+1]);
                current.right = right;
                queue.offer(right);
            }
        }

        return root;
    }

    public static void printInOrder(TreeNode node){

        if(node == null) return;

        printInOrder(node.left);
        System.out.print(node.val + " -> ");
        printInOrder(node.right);
    }
}

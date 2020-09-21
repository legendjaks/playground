package com.jay.tree;

public class MaxPathSum {

    public int maxPathSum(TreeNode root) {
        var res = helper(root);
        return res.max;
    }

    private Result helper(TreeNode root) {

        if (root == null)
            return new Result(Integer.MIN_VALUE, 0);

        var left = helper(root.left);
        var right = helper(root.right);

        int childSum = Math.max(left.sum, right.sum);
        int sum = Math.max(root.val, root.val + childSum);

        int max = root.val + left.sum + right.sum;
        max = Math.max(Math.max(max, sum), Math.max(left.max, right.max));

        return new Result(max, sum);
    }

    class Result {

        int max;
        int sum;

        public Result(int max, int sum) {
            this.max = max;
            this.sum = sum;
        }
    }

    public static void main(String[] args) {
        MaxPathSum ob = new MaxPathSum();
        //TreeNode root = TreeUtils.build(new Integer[]{-10,9,20,null,null,15,7});
        TreeNode root = TreeUtils.build(new Integer[]{-10});
        int res = ob.maxPathSum(root);
        System.out.println("res: " + res);
    }
}

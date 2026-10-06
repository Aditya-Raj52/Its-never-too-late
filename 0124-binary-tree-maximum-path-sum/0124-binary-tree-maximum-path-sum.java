/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int maxPs = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root){
        maxPathSum1(root);
        return maxPs;
    }
    private int maxPathSum1(TreeNode node) {
        if(node == null) return 0;
        int leftSum = Math.max(0,maxPathSum1(node.left));
        int rightSum = Math.max(0,maxPathSum1(node.right));

        maxPs = Math.max(maxPs, leftSum + rightSum + node.val);
        
        return node.val + Math.max(leftSum, rightSum);
        
    }
}
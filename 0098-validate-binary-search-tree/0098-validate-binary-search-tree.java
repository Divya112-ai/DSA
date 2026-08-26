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

    public boolean isValidBST(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean check(TreeNode node, long min, long max) {

        // Empty tree is valid
        if (node == null) {
            return true;
        }

        // Node must be strictly between min and max
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // Left subtree:
        // values must be between min and node.val
        boolean left = check(node.left, min, node.val);

        // Right subtree:
        // values must be between node.val and max
        boolean right = check(node.right, node.val, max);

        return left && right;
    }
}
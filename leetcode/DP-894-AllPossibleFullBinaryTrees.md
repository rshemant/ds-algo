# 894. All Possible Full Binary Trees

Medium

Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the answer must have Node.val == 0.

Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.

A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Example 1:

![alt text](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/08/22/fivetrees.png)

Input: n = 7
Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
Example 2:

Input: n = 3
Output: [[0,0,0]]

Constraints:

1 <= n <= 20

```java

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
    Map<Integer, List<TreeNode>> dp;

    public List<TreeNode> allPossibleFBT(int n) {
        dp = new HashMap<Integer, List<TreeNode>>();

        if(n%2 == 0)
            return new ArrayList<TreeNode>();

        return helper(n);
    }

    public List<TreeNode> helper(int index) {
        if(dp.containsKey(index))
            return dp.get(index);

        List<TreeNode> list = new ArrayList<TreeNode>();

        if(index == 1)
            list.add(new TreeNode(0));

        for(int i = 1; i < index; i+=2){

            List<TreeNode> leftTrees = helper(i);
            List<TreeNode> rightTrees = helper(index-i-1);

            for(TreeNode leftTree: leftTrees){
                for(TreeNode rightTree: rightTrees){

                    TreeNode root = new TreeNode(0, leftTree, rightTree);
                    list.add(root);
                }
            }
        }

        dp.put(index, list);
        return list;
    }
}
```

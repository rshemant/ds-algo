# 993. Cousins in Binary Tree
Easy

Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y, return true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.

Two nodes of a binary tree are cousins if they have the same depth with different parents.

Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth k + 1.

Example 1:

Input: root = [1,2,3,4], x = 4, y = 3\
Output: false

Example 2:

Input: root = [1,2,3,null,4,null,5], x = 5, y = 4\
Output: true

Example 3:

Input: root = [1,2,3,null,4], x = 2, y = 3\
Output: false

Constraints:

The number of nodes in the tree is in the range [2, 100].\
1 <= Node.val <= 100\
Each node has a unique value.\
x != y\
x and y are exist in the tree.\

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
    public boolean isCousins(TreeNode root, int x, int y) {
        TreeNode parentX = findParent(root, x, null);
        TreeNode parentY = findParent(root, y, null);

        if(parentX != parentY){
            return heightOfTree(root, x) == heightOfTree(root, y);
        }

        return false;
    }


    public TreeNode findParent(TreeNode root, int value, TreeNode parent){
        if(root == null)
            return null;

        if(root.val == value)
            return parent;

        TreeNode left = findParent(root.left, value, root);
        TreeNode right = findParent(root.right, value, root);
        return left != null ? left : right;
    }

    public int heightOfTree(TreeNode root, int value){
        if(root == null)
            return 0;

        if(root.val == value)
            return 1;

        int left = heightOfTree(root.left, value);
        int right = heightOfTree(root.right, value);
        int max = Math.max(left, right);

        if(max > 0)
            return ++max;

        return 0;
    }

}
```

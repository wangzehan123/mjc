package linkedin.tree;

import linkedin.model.TreeNode;

public class SecondMinimumNode_MaximumDepth {

  int min1;
  long ans = Long.MAX_VALUE;

  public void dfs(TreeNode root) {
    if (root != null) {
      if (min1 < root.val && root.val < ans) {
        ans = root.val;
      } else if (min1 == root.val) {
        dfs(root.left);
        dfs(root.right);
      }
    }
  }
  public int findSecondMinimumValue(TreeNode root) {
    min1 = root.val;
    dfs(root);
    return ans < Long.MAX_VALUE ? (int) ans : -1;
  }

  public int maxDepth(TreeNode root) {
    // Base Condition
    if(root == null) return 0;
    // Hypothesis
    int left = maxDepth(root.left);
    int right = maxDepth(root.right);
    // Induction
    return Math.max(left, right) + 1;
  }
}

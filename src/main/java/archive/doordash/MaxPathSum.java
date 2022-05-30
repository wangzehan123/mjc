package archive.doordash;

import linkedin.model.TreeNode;

public class MaxPathSum {

  /**
   * @param root: The root of binary tree.
   * @return: An integer
   */
  public int maxPathSum(TreeNode root) {

    int[] result = new int[1];
    result[0] = Integer.MIN_VALUE;
    subTreeSum(root, result);
    return result[0];
  }

  public int subTreeSum(TreeNode root, int[] result ) {
    if (root == null) {
      return 0;
    }
    int left = subTreeSum(root.left, result);
    int right = subTreeSum(root.right, result);

    result[0] = Math.max(left + right + root.val, result[0]);

    int whatReturn = Math.max(left + root.val, right + root.val);
    whatReturn = Math.max(whatReturn, root.val);

    result[0] = Math.max(result[0], whatReturn);

    return whatReturn;
  }
}

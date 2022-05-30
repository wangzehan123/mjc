package linkedin.tree;
public class LCACollection {
//
//    public class TreeNode {
//      int val;
//     TreeNode left;
//      TreeNode right;
//      TreeNode(int x) { val = x; }
//  }
//
//  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//
//    // Value of current node or parent node.
//    int parentVal = root.val;
//
//    // Value of p
//    int pVal = p.val;
//
//    // Value of q;
//    int qVal = q.val;
//
//    if (pVal > parentVal && qVal > parentVal) {
//      // If both p and q are greater than parent
//      return lowestCommonAncestor(root.right, p, q);
//    } else if (pVal < parentVal && qVal < parentVal) {
//      // If both p and q are lesser than parent
//      return lowestCommonAncestor(root.left, p, q);
//    } else {
//      // We have found the split point, i.e. the LCA node.
//      return root;
//    }
//  }
//
//  /*
//   * @param root: The root of the binary search tree.
//   * @param A: A TreeNode in a Binary.
//   * @param B: A TreeNode in a Binary.
//   * @return: Return the least common ancestor(LCA) of the two nodes.
//   */
//  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//    if(root == null || root == p || root == q)  return root;
//    TreeNode left = lowestCommonAncestor(root.left, p, q);
//    TreeNode right = lowestCommonAncestor(root.right, p, q);
//    if(left != null && right != null)   return root;
//    return left != null ? left : right;
//  }
}

package archive.google;

import java.util.ArrayList;
import java.util.List;
import linkedin.model.TreeNode;

public class FindLeavesofBinaryTree {

  List<List<Integer>> list = new ArrayList<>();

  public List<List<Integer>> findLeaves(TreeNode root) {
    getHeight(root);
    return list;
  }

  private int getHeight(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int currentHeight = Math.max(getHeight(root.left), getHeight(root.right));
    if (list.size() == currentHeight) {
      list.add(new ArrayList<>());
    }
    list.get(currentHeight).add(root.val);
    return currentHeight;
  }

  private TreeNode dfs(TreeNode root, List<Integer>list) {
    if (root == null) {
      return null;
    }
    TreeNode left = dfs(root.left, list);
    TreeNode right = dfs(root.right, list);
    if(root.left == null && root.right == null) {
      list.add(root.val);
      return null;                                        //return null when reach leaf
    }
    root.left = left;                                       //delete left leaf
    root.right = right;                                     //delete right leaf
    return root;
  }

}

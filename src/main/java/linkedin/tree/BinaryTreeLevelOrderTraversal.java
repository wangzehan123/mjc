package linkedin.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import linkedin.model.TreeNode;

public class BinaryTreeLevelOrderTraversal {

  public List<List<Integer>> levelOrder(TreeNode root) {
    List result = new ArrayList();

    if (root == null) {
      return result;
    }

    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      ArrayList<Integer> level = new ArrayList<Integer>();
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode head = queue.poll();
        level.add(head.val);
        if (head.left != null) {
          queue.offer(head.left);
        }
        if (head.right != null) {
          queue.offer(head.right);
        }
      }
      result.add(level);
    }

    return result;
  }

  /**
   * @param root: The root of binary tree.
   * @return: Level order a list of lists of integer
   */
  public List<List<Integer>> levelOrder_DFS(TreeNode root) {
    List<List<Integer>> results = new ArrayList<List<Integer>>();
    if (root == null) {
      return results;
    }
    int maxLevel = 0;
    while (true) {
      List<Integer> level = new ArrayList<Integer>();
      dfs(root, level, 0, maxLevel);
      if (level.size() == 0) {
        break;
      }
      results.add(level);
      maxLevel++;
    }
    return results;
  }

  private void dfs(TreeNode root,
      List<Integer> level,
      int curtLevel,
      int maxLevel) {
    if (root == null || curtLevel > maxLevel) {
      return;
    }

    if (curtLevel == maxLevel) {
      level.add(root.val);
      return;
    }

    dfs(root.left, level, curtLevel + 1, maxLevel);
    dfs(root.right, level, curtLevel + 1, maxLevel);
  }

}

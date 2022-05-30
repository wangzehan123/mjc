package linkedin.tree;

import java.util.LinkedList;
import java.util.Stack;
import linkedin.model.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class ClosestBinarySearchTreeValue {

  public int closestValue(TreeNode root, double target) {
    int ret = root.val;
    while(root != null){
      if(Math.abs(target - root.val) < Math.abs(target - ret)){
        ret = root.val;
      }
      root = root.val > target? root.left: root.right;
    }
    return ret;
  }


  /*
  * 暴力方法。时间复杂度 O(n)，空间复杂度也是 O(n)
  * 先用 inorder traversal 求出中序遍历
  * 找到第一个 >= target 的位置 index
  * 从 index-1 和 index 出发，设置两根指针一左一右，获得最近的 k 个整数
  * */
  class ClosestBinarySearchTreeValueII {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
      List<Integer> ret = new LinkedList<Integer>();
      dfs(root, target, k, ret);
      return ret;
    }

    private void dfs(TreeNode root, double target, int k, List<Integer> ret) {
      if (root == null) {
        return;
      }
      dfs(root.left, target, k, ret);
      if (ret.size() < k) {
        ret.add(root.val);
      } else if (Math.abs(root.val - target) < Math.abs(ret.get(0) - target)) {
        ret.remove(0);
        ret.add(root.val);
      }
      dfs(root.right, target, k, ret);
    }
  }

  class Optimize {

        /*
    * 最优算法，O(K + Logn) 空间O(logn)
    *
    * A brief description of this approach:
    * Search for the closest 1 or 2 vavlues in the BST.
    * Expand the answer upward and downward until our answer array reaches the window size k.
    * Build a succesors stack succStack to facilitate us to find the node with the smallest value >= target; and a predecessor stack predStack to facilitate us find the node with the largest value <= target.
    * The top value of both succStack and predStack will be the same if node with value == target exists.
    * If two stacks are both empty, it means we have an empty tree, in this case we simply return [].
    * If two stacks are both NOT empty and both contain the target value, we need to pop one of them since we don't want to duplicate it.
    * Loop through 0..<k, if one of the stacks is empty, just append the top value of the other stack to the answer array.
    * If two stacks are both not empty, we peek each stack and compare the distances of their top values to the target, and select the one with shorter distance and pop it out.
    */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
      List<Integer> values = new ArrayList<>();
      if (k == 0 || root == null) {
        return values;
      }

      Stack<TreeNode> lowerStack = getStack(root, target);
      Stack<TreeNode> upperStack = new Stack<>();
      upperStack.addAll(lowerStack);
      if (target < lowerStack.peek().val) {
        moveLower(lowerStack);
      } else {
        moveUpper(upperStack);
      }

      for (int i = 0; i < k; i++) {
        if (lowerStack.isEmpty() ||
            !upperStack.isEmpty() && target - lowerStack.peek().val > upperStack.peek().val - target) {
          values.add(upperStack.peek().val);
          moveUpper(upperStack);
        } else {
          values.add(lowerStack.peek().val);
          moveLower(lowerStack);
        }
      }

      return values;
    }

    private Stack<TreeNode> getStack(TreeNode root, double target) {
      Stack<TreeNode> stack = new Stack<>();

      while (root != null) {
        stack.push(root);

        if (target < root.val) {
          root = root.left;
        } else {
          root = root.right;
        }
      }

      return stack;
    }

    public void moveUpper(Stack<TreeNode> stack) {
      TreeNode node = stack.peek();
      if (node.right == null) {
        node = stack.pop();
        while (!stack.isEmpty() && stack.peek().right == node) {
          node = stack.pop();
        }
        return;
      }

      node = node.right;
      while (node != null) {
        stack.push(node);
        node = node.left;
      }
    }

    public void moveLower(Stack<TreeNode> stack) {
      TreeNode node = stack.peek();
      if (node.left == null) {
        node = stack.pop();
        while (!stack.isEmpty() && stack.peek().left == node) {
          node = stack.pop();
        }
        return;
      }

      node = node.left;
      while (node != null) {
        stack.push(node);
        node = node.right;
      }
    }
  }
}

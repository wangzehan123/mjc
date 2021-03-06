package archive.wepay;

import linkedin.model.TreeNode;

public class ValidAnagram_InvertTree {

  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode temp = root.left;
    root.left = root.right;
    root.right = temp;

    invertTree(root.left);
    invertTree(root.right);

    return root;
  }

  public boolean isAnagram(String s, String t) {
    int[] cntS = new int[256];
    int[] cntT = new int[256];
    for (char c : s.toCharArray()) {
      cntS[c]++;
    }
    for (char c : t.toCharArray()) {
      cntT[c]++;
    }
    for (int i = 0; i < 256; i++) {
      if (cntS[i] != cntT[i]) {
        return false;
      }
    }
    return true;
  }

}

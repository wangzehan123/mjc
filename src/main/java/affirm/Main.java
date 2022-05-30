package affirm;

import static affirm.ShortestSubString.findShortestUniqueSubstring;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// "static void main" must be defined in a public class.
public class Main {

  /*
      // Initial State:

      //              ROOT
      //          /     |    \
      //         /      |     \
      //       B       C        D
      //    /   |            /  | \  \
      //   /    |           /   |  \  \
      // F      G      (POPUP)  I  J  (K)
      //              /   |   \   / \
      //             /    |    \  Z  Y
      //            N     O    (P)


      // After openPopup called:

      //              ROOT
      //          /     |    \
      //         /      |     \
      //      (B)      (C)      D
      //    /   |            /  | \   \
      //   /    |           /   |  \   \
      // F      G       POPUP  (I)  (J) (K)
      //              /   |   \
      //             /    |    \
      //            N     O     (P)

  Requirement:
      Find POPUP, make all the sibling of POPUP to hidden
      Find out POPUP's parent, make all the sibling of parent to hidden
  */
  public static class DomNode {
    String id;
    boolean hidden;
    List<DomNode> children;

    public DomNode(String id, boolean hidden, List<DomNode> children) {
      this.hidden = hidden;
      this.id = id;
      this.children = children;
    }

    public String toString() {
      return hidden ? "(" + this.id + ")" : this.id;
    }
  }

  public static DomNode openPopup(DomNode root) {
    if (root == null) return root;
    Queue<DomNode> queue = new LinkedList<>();
    queue.offer(root);
    // List<DomNode> prevLevel = new ArrayList<>();
    while (!queue.isEmpty()) {
      int size = queue.size();
      List<DomNode> currLevel = new ArrayList<>();
      DomNode parent = null;
      boolean foundInLevel = false;
      for (int i = 0; i < size; i++) {
        boolean found = false;
        DomNode node = queue.poll();
        currLevel.add(node);
        List<DomNode> children = node.children;
        for (DomNode c : children) {
          queue.offer(c);
          if (c.id.equals("POPUP")) {
            // set POPUP to visable
            c.hidden = false;

            found = true;
            foundInLevel = true;
          }
        }
        if (found) {
          // set all siblings to hidden
          for (DomNode c : children) {
            if (c.id.equals("POPUP")) continue;
            c.hidden = true;
          }
          parent = node;
        }
      }
      // set POPUP's parent's siblings to hidden
      if (foundInLevel) {
        for (DomNode sibling : currLevel) {
          if (sibling.equals(parent)) continue;
          sibling.hidden = true;
        }
        break;
      }
    }
    return root;
  }

  // add parent pointer
  public static DomNode openPopupII(DomNode root) {
    if (root == null) {
      return null;
    }
    return null;
  }

  public static void main(String[] args) {
    // find unique name
    // POPUP node
    DomNode N = new DomNode("N", false, new ArrayList<>());
    DomNode O = new DomNode("O", false, new ArrayList<>());
    DomNode P = new DomNode("P", true, new ArrayList<>());
    List<DomNode> popupChildren = new ArrayList<>();
    popupChildren.add(N);
    popupChildren.add(O);
    popupChildren.add(P);
    DomNode POPUP = new DomNode("POPUP", true, popupChildren);

    DomNode I = new DomNode("I", false, new ArrayList<>());
    DomNode J = new DomNode("J", false, new ArrayList<>());
    DomNode K = new DomNode("K", true, new ArrayList<>());

    List<DomNode> dChildren = new ArrayList<>();
    dChildren.add(POPUP);
    dChildren.add(I);
    dChildren.add(J);
    dChildren.add(K);
    DomNode D = new DomNode("D", false, dChildren);
    DomNode C = new DomNode("C", false, new ArrayList<>());

    DomNode F = new DomNode("F", false, new ArrayList<>());
    DomNode G = new DomNode("G", false, new ArrayList<>());

    List<DomNode> bChildren = new ArrayList<>();
    bChildren.add(F);
    bChildren.add(G);
    DomNode B = new DomNode("B", false, bChildren);

    List<DomNode> rootChildren = new ArrayList<>();
    rootChildren.add(B);
    rootChildren.add(C);
    rootChildren.add(D);

    DomNode root = new DomNode("ROOT", false, rootChildren);

    System.out.println("Before: ");
    bfs(root);

    openPopup(root);

    System.out.println("After: ");
    bfs(root);
    // System.out.println(bfs(root));
  }

  private static void bfs(DomNode root) {
    Queue<DomNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        DomNode node = queue.poll();
        System.out.print(node.toString() + ", ");
        for (DomNode c : node.children) {
          queue.offer(c);
        }
      }
      System.out.println("");
    }
  }
}

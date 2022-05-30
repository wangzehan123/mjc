package microsoft.mj;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import microsoft.data.MyLinkedList.ListNode;

public class MergeK {

  public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
      if (lists==null||lists.size()==0) return null;

      PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.size(),new Comparator<ListNode>(){
        public int compare(ListNode o1,ListNode o2){
          if (o1.val<o2.val)
            return -1;
          else if (o1.val==o2.val)
            return 0;
          else
            return 1;
        }
      });

      ListNode dummy = new ListNode(0);
      ListNode tail=dummy;

      for (ListNode node:lists)
        if (node!=null)
          queue.add(node);

      while (!queue.isEmpty()){
        tail.next=queue.poll();
        tail=tail.next;

        if (tail.next!=null)
          queue.add(tail.next);
      }
      return dummy.next;
    }
  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode head = new ListNode(0);
    ListNode handler = head;
    while(l1 != null && l2 != null) {
      if (l1.val <= l2.val) {
        handler.next = l1;
        l1 = l1.next;
      } else {
        handler.next = l2;
        l2 = l2.next;
      }
      handler = handler.next;
    }

    if (l1 != null) {
      handler.next = l1;
    } else if (l2 != null) {
      handler.next = l2;
    }

    return head.next;
  }
}

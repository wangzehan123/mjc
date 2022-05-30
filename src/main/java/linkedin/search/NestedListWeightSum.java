package linkedin.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NestedListWeightSum {

  public int depthSumBFS(List<NestedInteger> nestedList) {
    if (nestedList == null || nestedList.size() == 0) {
      return 0;
    }
    int sum = 0;
    Queue<NestedInteger> queue = new LinkedList<>();
    for (NestedInteger nestedInteger : nestedList) {
      queue.offer(nestedInteger);
    }
    int depth = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      depth++;
      for (int i = 0; i < size; i++) {
        NestedInteger nesIn = queue.poll();
        if (nesIn.isInteger()) {
          sum += nesIn.getInteger() * depth;
        }else {
          for (NestedInteger innerInt : nesIn.getList()) {
            queue.offer(innerInt);
          }
        }
      }
    }
    return sum;
  }

  public int depthSumDFS(List<NestedInteger> nestedList) {
    return helper(nestedList, 1);
  }

  public int helper(List<NestedInteger> nestedList, int depth){
    if (nestedList == null || nestedList.size() == 0)
      return 0;

    int sum = 0;
    for(NestedInteger ele : nestedList) {
      if (ele.isInteger()) {
        sum += ele.getInteger() * depth;
      } else {
        sum += helper(ele.getList(), depth + 1);
      }
    }

    return sum;
  }

  List<Integer> depthSums;

  public int depthSumInverseDFS(List<NestedInteger> nList) {
    depthSums = new ArrayList();
    dfs(nList, 0);
    int sum = 0, depth = depthSums.size();
    for (int i = 0; i < depth; i++) {
      sum += (depth - i) * depthSums.get(i);
    }
    return sum;
  }

  public void dfs(List<NestedInteger> nList, int depth) {
    if (nList == null) return;
    if (depth >= depthSums.size()) {
      depthSums.add(0); // if next level is visited, add new space to depth sum list
    }
    for (NestedInteger n : nList) {
      if (!n.isInteger()) {
        dfs(n.getList(), depth + 1);
      } else {
        depthSums.set(depth, depthSums.get(depth) + n.getInteger());
      }
    }
  }

  public int depthSumInverseBFS(List<NestedInteger> nestedList) {
    // corner case
    if(nestedList == null || nestedList.size() == 0) {
      return 0;
    }
    // initialize
    int preSum = 0;
    int result = 0;
    // put each item of list into the queue
    Queue<NestedInteger> queue = new LinkedList<>(nestedList);
    while(!queue.isEmpty()){
      //depends on different depth, queue size is changeable
      int size = queue.size();
      int levelSum = 0;
      for(int i = 0; i < size; i++){
        NestedInteger n = queue.poll();
        if(n.isInteger()){
          levelSum += n.getInteger();
        }
        else{
          // depends on different depth, queue size is changeable
          queue.addAll(n.getList());
        }
      }
      preSum += levelSum;
      result += preSum;
    }
    return result;
  }




   public class NestedInteger {

     public NestedInteger() {}

     public NestedInteger(int value) {
     }

     public boolean isInteger() {
       return false;
     }

     public Integer getInteger() {
       return new Integer(1);
     }

     public void setInteger(int value) {
     }

     public void add(NestedInteger ni) {

     }


     public List<NestedInteger> getList() {
       return null;
     }
   }

}

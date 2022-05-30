package archive.wepay;

import linkedin.search.NestedListWeightSum.NestedInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedList_FindAllAnagrams {

  public class NestedIterator implements Iterator<Integer> {

    private Stack<NestedInteger> stack;

    private void pushListToStack(List<NestedInteger> nestedList) {
      Stack<NestedInteger> temp = new Stack<>();
      for (NestedInteger nested : nestedList) {
        temp.push(nested);
      }

      while (!temp.isEmpty()) {
        stack.push(temp.pop());
      }
    }

    public NestedIterator(List<NestedInteger> nestedList) {
      stack = new Stack<>();
      pushListToStack(nestedList);
    }

    // @return {int} the next element in the iteration
    @Override
    public Integer next() {
      if (!hasNext()) {
        return null;
      }
      return stack.pop().getInteger();
    }

    // @return {boolean} true if the iteration has more element or false
    @Override
    public boolean hasNext() {
      while (!stack.isEmpty() && !stack.peek().isInteger()) {
        pushListToStack(stack.pop().getList());
      }

      return !stack.isEmpty();
    }

    @Override
    public void remove() {}
  }

  public List<Integer> findAnagrams(String s, String p) {
    // Write your code here
    List<Integer> ans = new ArrayList<Integer>();
    int[] sum = new int[30];

    int plength = p.length(), slength = s.length();
    for(char c : p.toCharArray()){
      sum[c - 'a'] ++;
    }

    int start = 0, end = 0, matched = 0;
    while(end < slength){
      if(sum[s.charAt(end) - 'a'] >= 1){
        matched ++;
      }
      sum[s.charAt(end) - 'a'] --;
      end ++;
      if(matched == plength) {
        ans.add(start);
      }
      if(end - start == plength){
        if(sum[s.charAt(start) - 'a'] >= 0){
          matched --;
        }
        sum[s.charAt(start) - 'a'] ++;
        start ++;
      }
    }
    return ans;
  }
}

package archive.ebay;

import java.util.Stack;

class CustomStack {

  int n;
  Stack<Integer> stack;
  int[] increment;

  public CustomStack(int maxSize) {
    n = maxSize;
    stack = new Stack<>();
    increment = new int[n];
  }

  public void push(int x) {
    if (stack.size() < n) {
      stack.push(x);
    }
  }

  public int pop() {
    int i = stack.size() - 1;
    if (i < 0) {
      return -1;
    }
    if (i > 0) {
      increment[i - 1] += increment[i];
    }
    int res = stack.pop() + increment[i];
    increment[i] = 0;
    return res;
  }

  public void increment(int k, int val) {
    int i = Math.min(k, stack.size()) - 1;
    if (i >= 0) {
      increment[i] = increment[i] + val;
    }
  }
}
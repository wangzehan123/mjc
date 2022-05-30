package archive.opendoor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PriorityQueue {

  private int length;
  private int[] arr;
  private int nItems;

  public PriorityQueue() {
    length = 100;
    arr = new int[length];
    nItems = 0;
  }

  public void offer(int val) {
    if (nItems == 0) {
      arr[0] = val;
      nItems++;
      return;
    }
    int i;
    for (i = nItems - 1; i > 0; i--) {
      if (val > arr[i]) {
        arr[i + 1] = arr[i];
      }else {
        break;
      }
    }
    arr[i + 1] = val;
  }

  public int poll() {
    return arr[--nItems];
  }

  public int peek() {
    return arr[nItems - 1];
  }

  class MyStack {

    /** Initialize your data structure here. */
    private Queue<Integer> q1;
    private Queue<Integer> q2;
    public MyStack() {
      q1 = new LinkedList<>();
      q2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
      q1.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
      while (q1.size() > 1) {
        q2.offer(q1.poll());
      }
      int res = q1.poll();
      Queue<Integer> temp = q1;
      q1 = q2;
      q2 = temp;
      return res;
    }

    /** Get the top element. */
    public int top() {
      int res = pop();
      q1.offer(res);
      return res;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
      return q1.isEmpty();
    }
  }

  class MyQueue {

    Stack<Integer> input = new Stack();
    Stack<Integer> output = new Stack();

    public void push(int x) {
      input.push(x);
    }

    public void pop() {
      peek();
      output.pop();
    }

    public int peek() {
      if (output.empty())
        while (!input.empty())
          output.push(input.pop());
      return output.peek();
    }

    public boolean empty() {
      return input.empty() && output.empty();
    }
  }
}

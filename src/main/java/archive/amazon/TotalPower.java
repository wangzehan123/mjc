package archive.amazon;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TotalPower {

  public static void main(String[] args) {
    BigInteger bg = new BigInteger("1000000000000000000000000000000000000000000000000000000");
    BigInteger bg2 = new BigInteger("1000000000000000000000000000000000000000000000000000000");
    System.out.println(bg.add(bg2));
  }
  public static int solution(List<Integer> nums) {
    int n = nums.size();
    int[] preMin = new int[n];
    int[] nextMin = new int[n];
    Arrays.fill(preMin, -1);
    Arrays.fill(nextMin, n);
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && nums.get(stack.peek()) > nums.get(i)) {
        nextMin[stack.pop()] = i;
      }
      stack.push(i);
    }

    stack = new Stack<>();
    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && nums.get(stack.peek()) > nums.get(i)) {
        stack.pop();
      }
      if (!stack.isEmpty()) {
        preMin[i] = stack.peek();
      }
      stack.push(i);
    }
    int result = 0;
    for (int i = 0; i < n; i++) {
      int temp = 0;
      for (int j = preMin[i] + 1; j < i; j++) {
        temp += nums.get(j) * (j - preMin[i]) * (nextMin[i] - i);
      }
      for (int j = i; j < nextMin[i]; j++) {
        temp += nums.get(j) * (nextMin[i] - j) * (i - preMin[i]);
      }
      result += nums.get(i) * temp;
    }
    return result;
  }
}

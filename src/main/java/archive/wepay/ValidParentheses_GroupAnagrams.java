package archive.wepay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses_GroupAnagrams {

  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
      if (c == '(' || c == '[' || c == '{') {
        stack.push(c);
      }
      if (c == ')') {
        if (stack.isEmpty() || stack.pop() != '(') {
          return false;
        }
      }
      if (c == ']') {
        if (stack.isEmpty() || stack.pop() != '[') {
          return false;
        }
      }
      if (c == '}') {
        if (stack.isEmpty() || stack.pop() != '{') {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }

  public List<List<String>> groupAnagrams(String[] strs) {
    // write your code here
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) {
      char[] sc = s.toCharArray();
      Arrays.sort(sc);
      String key = String.valueOf(sc);
      map.putIfAbsent(key, new ArrayList<>());
      map.get(key).add(s);
    }
    return new ArrayList<>(map.values());
  }
}

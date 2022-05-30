package archive.opendoor;

import java.util.Stack;

public class ReversePolishNotation {

  public int evalRPN(String[] tokens) {
    Stack<Integer> s = new Stack<Integer>();
    String operators = "+-*/";
    for (String token : tokens) {
      if (!operators.contains(token)) {
        s.push(Integer.valueOf(token));
        continue;
      }

      int a = s.pop();
      int b = s.pop();
      if (token.equals("+")) {
        s.push(b + a);
      } else if(token.equals("-")) {
        s.push(b - a);
      } else if(token.equals("*")) {
        s.push(b * a);
      } else {
        s.push(b / a);
      }
    }
    return s.pop();
  }
}

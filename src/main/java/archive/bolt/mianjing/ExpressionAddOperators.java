package archive.bolt.mianjing;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {

  public static void main(String[] args) {
    System.out.println('b' - 'a');
  }

  public static List<String> addOperators(String num, int target) {

    List<String> results = new ArrayList<>();
    if (num == null || num.length() == 0) {
      return results;
    }
    dfs(results, num, new StringBuilder(), target, 0, 0,0);
    return results;
  }

  public static void dfs(List<String> result, String num, StringBuilder path, long target, int pos,
      long tmp, long last) {
    if (pos == num.length()) {
      if (target == tmp) {
        result.add(path.toString());
      }
      return;
    }
    for (int i = pos; i < num.length(); i++) {
      if (num.charAt(pos) == '0' && i != pos) {
        break;
      }
      long val = Long.valueOf(num.substring(pos, i + 1));
      int len = path.length();
      if (pos == 0) {
        dfs(result, num, path.append(val), target, i + 1, val, val);
        path.setLength(len);
      } else {
        dfs(result, num, path.append("+").append(val), target, i + 1, tmp + val, val);
        path.setLength(len);
        dfs(result, num, path.append("-").append(val), target, i + 1, tmp - val, -val);
        path.setLength(len);
        dfs(result, num, path.append("*").append(val), target, i + 1, tmp - last + last * val, last * val);
        path.setLength(len);
      }
    }
  }

}

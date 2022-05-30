package archive.instacart.expression;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExpressionString {

  public static void main(String[] args){
    String[] txt1 = {"T2", "T1 = 1", "T2 = T3", "T3 = T1"};
    String[] txt2 = {"T2", "T1 = 10", "T2 = 2 + T4", "T3 = T1 - 4", "T4 = T1 + T3"};
    String[] txt3 = {"T3", "T1 = 10", "T2 = 2 + T4", "T3 = T1 - 4", "T4 = T1 + T3"};
    String[] txt4 = {"T4", "T1 = 10", "T2 = 2 + T4", "T3 = T1 - 4", "T4 = T1 + T3"};
    String[] txt5 = {"T1", "T1 = 10", "T2 = 2 + T4", "T3 = T1 - 4", "T4 = T1 + T3"};
    String[] txt6 = {"T2", "T1 = T2", "T2 = T1"};
    System.out.println(calculate(txt1));
    System.out.println(calculate(txt2));
    System.out.println(calculate(txt3));
    System.out.println(calculate(txt4));
    System.out.println(calculate(txt5));
    System.out.println(calculate(txt6));
  }

  public static Integer calculate(String[] txt){
    String target = txt[0];
    Map<String, String> map = new HashMap<>();
    for (int i = 1; i < txt.length; i++) {
      String curEquation = txt[i];
      String left = curEquation.split("=")[0]
          .trim().replace("\"", "");
      String right = curEquation.split("=")[1]
          .trim().replace("\"", "");
      map.put(left, right);
    }
    return recursion2(map, target, new HashSet<>());
  }

  public static Integer recursion
      (Map<String, String> map, String target, Set<String> visited) {
    if (visited.contains(target)) {
      return null;
    }
    if (isNumeric(map.get(target))) {
      return Integer.valueOf(map.get(target));
    }
    visited.add(target);
    return recursion(map, map.get(target),visited);
  }

  public static Integer recursion2
      (Map<String, String> map, String target, Set<String> visited) {
    if (visited.contains(target + "=" + map.get(target))) {
      return null;
    }
    if (isNumeric(target)) {
      return Integer.valueOf(target);
    }
    if (isNumeric(map.get(target))) {
      return Integer.valueOf(map.get(target));
    }
    visited.add(target + "=" + map.get(target));
    String curValue = map.get(target);
    String left = "";
    String right = "";
    if (curValue.contains("+")) {
      left = curValue.split("\\+")[0].trim();
      right = curValue.split("\\+")[1].trim();
      return recursion2(map, left, visited) + recursion2(map, right, visited);
    } else if (curValue.contains("-")) {
      left = curValue.split("-")[0].trim();
      right = curValue.split("-")[1].trim();
      return recursion2(map, left, visited) - recursion2(map, right, visited);
    }
    return recursion2(map, map.get(target), visited);
  }

  public static boolean isNumeric(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch(NumberFormatException e){
      return false;
    }
  }

}

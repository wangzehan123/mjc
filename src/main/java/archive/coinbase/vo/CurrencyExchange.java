package archive.coinbase.vo;

import java.util.*;

/*
you can't use BFS here as it might terminate once it reaches the target currency.
* */
public class CurrencyExchange {

  public static void main(String[] args) {

    List<Node> data = new ArrayList<>();
    data.add(new Node("BTC", "USD", 3, 4));
    data.add(new Node("USD", "YEN", 20, 30));
    data.add(new Node("USD", "JPM", 25, 14));
    data.add(new Node("JPM", "YEN", 15, 7));
    data.add(new Node("BBC", "JPM", 25, 20)); //comp5
    data.add(new Node("USD", "BBC", 100, 150));

    System.out.println(convert("USD", "BTC", data));
    System.out.println(maxPath);
    System.out.println(res);
    System.out.println(values);
  }


  public static double convert(String start, String end, List<Node> data) {
    Map<String, Map<String, Double>> map = new HashMap<>();
    for (Node node : data) {
      if(!map.containsKey(node.start)) {
        map.put(node.start, new HashMap<>());
        map.get(node.start).put(node.end, node.ratio2);
      }
      double c1 = map.get(node.start).getOrDefault(node.end, 0.0);
      map.get(node.start).put(node.end, node.ratio2);
      if (!map.containsKey(node.end)) {
        map.put(node.end, new HashMap<>());
        map.get(node.end).put(node.start, 1.0 / node.ratio1);
      }
      double c2 = map.get(node.end).getOrDefault(node.start, 0.0);
      map.get(node.end).put(node.start, 1.0 / node.ratio1);
    }
    List<String> path = new ArrayList<>();
    path.add(start);
    Set<String> visited = new HashSet<>();
    visited.add(start);
    dfs(start, end, path, 1.0, map, visited);
    return max;
  }

  private static double max = 0;
  private static List<String> maxPath = new ArrayList<>();
  private static List<List<String>> res = new ArrayList<>();
  private static List<Double> values = new ArrayList<>();

  private static void dfs(String cur, String end, List<String> path, double curMoney,
      Map<String, Map<String, Double>> map, Set<String> visited) {
    if (cur.equals(end)) {
      if (curMoney > max) {
        maxPath = new ArrayList<>(path);
        max = curMoney;
      }
      res.add(new ArrayList<>(path));
      values.add(curMoney);
      return;
    }

    for (String sub : map.get(cur).keySet()) {
      if (visited.contains(sub)) {
        continue;
      }
      visited.add(sub);
      curMoney *= map.get(cur).get(sub);
      path.add(sub);

      dfs(sub, end, path, curMoney, map, visited);

      curMoney /= map.get(cur).get(sub);
      visited.remove(sub);
      path.remove(path.size() - 1);
    }
  }

  static class Node {

    String start;
    String end;
    double ratio1;
    double ratio2;

    public Node(String s, String e, double ratio1, double ratio2) {
      this.start = s;
      this.end = e;
      this.ratio1 = ratio1;
      this.ratio2 = ratio2;
    }
  }
}


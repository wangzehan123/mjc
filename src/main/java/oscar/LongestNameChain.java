package oscar;

import static oscar.LongestNameChain.Pair.convert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestNameChain {

  public static void main(String[] args) {

    List<Pair>data = new ArrayList<>();
    data.add(new Pair("a", "b"));
    data.add(new Pair("b", "c"));
    data.add(new Pair("c", "d"));
    data.add(new Pair("e", "f"));
    System.out.println(convert(data));
  }

  static class Pair {

    String start;
    String end;

    public Pair(String s, String e) {
      this.start = s;
      this.end = e;
    }

    public static Map<String, List<String>> convert(List<Pair> data) {
      Map<String, List<String>> map = new HashMap<>();
      for (Pair pair : data) {
        if (!map.containsKey(pair.start)) {
          map.put(pair.start, new ArrayList<>());
          map.get(pair.start).add(pair.end);
        }
        else {
          map.get(pair.start).add(pair.end);
        }
      }
      return map;
    }

  }
}

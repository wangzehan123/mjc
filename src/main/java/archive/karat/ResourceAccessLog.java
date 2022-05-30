package archive.karat;


import java.util.*;

public class ResourceAccessLog {
  static String[][] logs1 = new String[][]{
      {"58523", "user_1", "resource_1"},
      {"62314", "user_2", "resource_2"},
      {"54001", "user_1", "resource_3"},
      {"200", "user_6", "resource_5"},
      {"215", "user_6", "resource_4"},
      {"54060", "user_2", "resource_3"},
      {"53760", "user_3", "resource_3"},
      {"58522", "user_22", "resource_1"},
      {"53651", "user_5", "resource_3"},
      {"2", "user_6", "resource_1"},
      {"100", "user_6", "resource_6"},
      {"400", "user_7", "resource_2"},
      {"100", "user_8", "resource_6"},
      { "54359", "user_1", "resource_3"},
  };

  static String[][] logs2 = new String[][]{
      {"300", "user_1", "resource_3"},
      {"599", "user_1", "resource_3"},
      {"900", "user_1", "resource_3"},
      {"1199", "user_1", "resource_3"},
      {"1200", "user_1", "resource_3"},
      {"1201", "user_1", "resource_3"},
      {"1202", "user_1", "resource_3"}
  };

  public static void main(String[] args) {
    System.out.println("first question");
    Map<String, int[]> map = findUserMaxAndMin(logs1);
    for (Map.Entry<String, int[]> entry : map.entrySet()) {
      System.out.println(entry.getKey() + ":" + Arrays.toString(entry.getValue()));
    }
    System.out.println("----------------");
    String[][] logs2 = new String[][]{
        {"300", "user_1", "resource_3"},
        {"599", "user_1", "resource_3"},
        {"900", "user_1", "resource_3"},
        {"1199", "user_1", "resource_3"},
        {"1200", "user_1", "resource_3"},
        {"1201", "user_1", "resource_3"},
        {"1202", "user_1", "resource_3"}
    };
    findHighestAccessedRes(logs1);
    System.out.println("----------------");
        /* expected
        transition_graph(logs1) # =>transition_graph(logs1) # =>
{{
'START': {'resource_1': 0.25, 'resource_2': 0.125, 'resource_3': 0.5, 'resource_6': 0.125}
'resource_1': {'resource_6': 0.333, 'END': 0.667}
'resource_2': {'END': 1.0}
'resource_3': {'END': 0.4, 'resource_1': 0.2, 'resource_2': 0.2, 'resource_3': 0.2}
'resource_4': {'END': 1.0}
'resource_5': {'resource_4': 1.0}
'resource_6': {'END': 0.5, 'resource_5': 0.5}
}}
        */
    System.out.println("follow up");
    Map<String, Map<String, Double>> res = buildTransition(logs1);
    for (Map.Entry<String, Map<String, Double>> entry : res.entrySet()) {
      System.out.println(entry.getKey() + ":" + (entry.getValue()));
    }
  }

  public static Map<String, int[]> findUserMaxAndMin(String[][] logs) {
    Map<String, int[]> res = new HashMap<>();
    for (String[] log : logs) {
      int time = Integer.parseInt(log[0]);
      String name = log[1];
      if (!res.containsKey(name)) {
        res.put(name, new int[]{Integer.MAX_VALUE, 0});
      }
      int[] pair = res.get(name);
      if (time < pair[0]) {
        pair[0] = time;
      }
      if (time > pair[1]) {
        pair[1] = time;
      }
    }
    return res;
  }

  // Write a function that takes the logs and returns the resource with the highest number of accesses in any 5 minute window, together with how many accesses it saw.
  public static void findHighestAccessedRes(String[][] records) {
    Map<String, List<Integer>> res2Acc = new HashMap<>();
    for (String[] record : records) {
      String res = record[2];
      int time = Integer.parseInt(record[0]);
      res2Acc.putIfAbsent(res, new ArrayList<>());
      res2Acc.get(res).add(time);
    }
    int max = 0;
    String maxRes = "";
    for (String res : res2Acc.keySet()) {
      List<Integer> acc = res2Acc.get(res);
      Collections.sort(acc);
      int localMax = helper(acc);
      if (localMax > max) {
        max =localMax;
        maxRes = res;
      }
    }
    System.out.println(maxRes + ":" + max);
  }

  private static int helper(List<Integer> list) {
    int start = 0;
    int max = 0;
    for (int end = 0; end < list.size(); end++) {
      while (list.get(end) - list.get(start) > 300) {
        start++;
      }
      max = Math.max(max, end - start + 1);
    }
    return max;
  }

  //Write a function that takes the logs as input, builds the transition graph and returns it as an adjacency list with probabilities.
  // Add START and END states.Write a function that takes the logs as input, builds the transition graph and returns it as an adjacency list
  // with probabilities. Add START and END states.
  public static Map<String, Map<String, Double>> buildTransition(String[][] logs) {
    // sort by time
    Arrays.sort(logs, (l1, l2) -> (Integer.parseInt(l1[0]) - Integer.parseInt(l2[0])));
    Map<String, List<String>> p2res = new HashMap<>();
    for (String[] log : logs) {
      String name = log[1];
      String res = log[2];
      p2res.putIfAbsent(name, new ArrayList<>());
      p2res.get(name).add(res);
    }

    // a as begin, all possible next stop, with freq
    Map<String, Map<String, Integer>> graph = new HashMap<>();
    String START = "START";
    String END = "END";
    for (List<String> list : p2res.values()) {
      for (int i = 0; i <= list.size(); i++) {
        String prev = i == 0 ? START : list.get(i-1);
        String next = i == list.size() ? END : list.get(i);
        graph.putIfAbsent(prev, new HashMap<>());
        graph.get(prev).put(next, graph.get(prev).getOrDefault(next, 0) + 1);
      }
    }

    // build transition graph
    Map<String, Map<String, Double>> tGraph = new HashMap<>();
    for (String res : graph.keySet()) {
      Map<String, Integer> freqMap = graph.get(res);
      int sum = 0;
      for (int val : freqMap.values()) sum += val;

      Map<String, Double> probMap = new HashMap<>();
      for (String nextRes : freqMap.keySet()) {
        probMap.put(nextRes, freqMap.get(nextRes) * 1.0 / sum);
      }
      tGraph.put(res, probMap);
    }

    return tGraph;
  }
}

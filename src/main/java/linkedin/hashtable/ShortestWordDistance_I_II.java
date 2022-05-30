package linkedin.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShortestWordDistance_I_II {

  public int shortestDistance(String[] words, String word1, String word2) {
    // Write your code here
    int p1 = -1, p2 = -1, min = Integer.MAX_VALUE;
    for (int i = 0; i < words.length; i++) {
      if (words[i].equals(word1)) {
        p1 = i;
      }
      if (words[i].equals(word2)) {
        p2 = i;
      }
      if (p1 != -1 && p2 != -1) {
        min = Math.min(min, Math.abs(p1 - p2));
      }
    }
    return min;
  }

  HashMap<String, List<Integer>> map;

  public ShortestWordDistance_I_II(String[] wordsDict) {
    map = new HashMap<>();
    for (int i = 0; i < wordsDict.length; i++) {
      List<Integer> indexes = map.getOrDefault(wordsDict[i], new ArrayList<>());
      indexes.add(i);
      map.put(wordsDict[i], indexes);
    }
  }

  public int shortest(String word1, String word2) {
    List<Integer> p1 = map.get(word1);
    List<Integer> p2 = map.get(word2);
    int min = Integer.MAX_VALUE;
    int i = 0;
    int j = 0;
    while (i < p1.size() && j < p2.size()) {
      int wp1 = p1.get(i);
      int wp2 = p2.get(j);
      if (wp1 < wp2) {
        min = Math.min(min, wp2 - wp1);
        i++;
      }else {
        min = Math.min(min, wp1 - wp2);
        j++;
      }
    }
    return min;
  }

  public int shortestWordDistance_III(String[] words, String word1, String word2) {
    long dist = Integer.MAX_VALUE, i1 = dist, i2 = -dist;
    for (int i=0; i<words.length; i++) {
      if (words[i].equals(word1))
        i1 = i;
      if (words[i].equals(word2)) {
        if (word1.equals(word2))
          i1 = i2;
        i2 = i;
      }
      dist = Math.min(dist, Math.abs(i1 - i2));
    }
    return (int) dist;
  }

}

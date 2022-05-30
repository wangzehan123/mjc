package archive.flexton;

import java.util.HashMap;

public class MaxDistance {

  int solution(int[] A) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int max = 0;
    for (int i = 0; i < A.length; i++) {
      if (!map.containsKey(A[i])) {
        map.put(A[i], i);
      }else {
        max = Math.max(max, Math.abs(i - map.get(A[i])));
      }
    }
    return max;
  }

}

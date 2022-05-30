package archive.wepay;

import java.util.ArrayList;
import java.util.List;

public class Fib_IntervalList {

  public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
    if(firstList.length==0 || secondList.length==0) return new int[0][0];
    int i = 0;
    int j = 0;
    int startMax = 0, endMin = 0;
    List<int[]> ans = new ArrayList<>();

    while(i<firstList.length && j<secondList.length){
      startMax = Math.max(firstList[i][0],secondList[j][0]);
      endMin = Math.min(firstList[i][1],secondList[j][1]);

      //you have end greater than start and you already know that this interval is sorrounded with startMin and endMax so this must be the intersection
      if(endMin>=startMax){
        ans.add(new int[]{startMax,endMin});
      }

      //the interval with min end has been covered completely and have no chance to intersect with any other interval so move that list's pointer
      if(endMin == firstList[i][1]) i++;
      if(endMin == secondList[j][1]) j++;
    }

    return ans.toArray(new int[ans.size()][2]);
  }

  public int fib(int N)
  {
    if(N <= 1)
      return N;

    int[] fib_cache = new int[N + 1];
    fib_cache[0] = 0;
    fib_cache[1] = 1;

    for(int i = 2; i <= N; i++)
    {
      fib_cache[i] = fib_cache[i - 1] + fib_cache[i - 2];
    }
    return fib_cache[N];
  }

  public int fibRecursion(int N)
  {
    if(N <= 1)
      return N;

    int[] fib_cache = new int[N + 1];
    fib_cache[1] = 1;

    for(int i = 2; i <= N; i++)
    {
      fib_cache[i] = fib_cache[i - 1] + fib_cache[i - 2];
    }
    return fib_cache[N];
  }


}

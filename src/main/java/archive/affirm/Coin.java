package archive.affirm;

import java.util.ArrayList;
import java.util.List;

public class Coin {
    static int[] coinSet = {1,5,10,25};
    static List<List<Integer>> possibleWays = new ArrayList<>();
    static List<Integer> currentWay = new ArrayList<>();
    public static void main(String[] args) {
      List<Integer> countOfCoins = new ArrayList<>();
      makeChange(7, 0, countOfCoins);
      //System.out.print(possibleWays);
    }

    private static int makeChange(int amount, int startCoinIdx, List<Integer> coinsSoFar) {
      if(startCoinIdx == coinSet.length){
        if(amount == 0){
          possibleWays.add(coinsSoFar);
          System.out.println(coinsSoFar);
        }
        //System.out.println(coinsSoFar);
        return 0;
      }
      for(int count = 0;(count*coinSet[startCoinIdx]) <= amount;count++){
        List<Integer> temp = new ArrayList<>();
        for(int i = 0;i < coinsSoFar.size();i++) temp.add(coinsSoFar.get(i));
        for(int i = 0;i < count;i++) temp.add(coinSet[startCoinIdx]);
        makeChange(amount - (count * coinSet[startCoinIdx]),startCoinIdx+1, temp);
        temp.clear();
      }
      return 0;
    }


  public class Solution {

    public int coinChange(int[] coins, int amount) {
      // write your code here
      final int INF = 0x3f3f3f3f;
      int[] dp = new int[amount + 1];
      dp[0] = 0;
      for (int i = 1; i <= amount; i++) {
        dp[i] = INF;// 边界条件
        for (int j = 0; j < coins.length; j++) {
          if (i >= coins[j] && dp[i - coins[j]] != INF) {
            dp[i] = Integer.min(dp[i], dp[i - coins[j]] + 1);
          }
        }
      }
      // 如果不存任意的方案 返回-1
      return dp[amount] == INF ? -1 : dp[amount];
    }
  }
}

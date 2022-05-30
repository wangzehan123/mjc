package archive.coinbase.jiamian;

import java.util.Arrays;

public class Kanapsack {

  static int n;
  static int V;
  static int MaxValue = 0;//n为物品的总件数，V为背包的容量，MaxValue为最大价值
  static int[] w = {5, 4 , 6};
  static int[] c = {10, 5 ,3};

  static void DFS_Kanapsack(int index,int sumW,int sumC) {
    if (index == n) {
      if (sumW <= V && sumC >= MaxValue) {
        MaxValue = sumC;
      }
      return;
    }
    DFS_Kanapsack(index+1,sumW,sumC);//不选择第index件物品
    DFS_Kanapsack(index+1,sumW + w[index],sumC + c[index]);//选择第index件物品
  }

  public static void main(String[] args) {
    n = 3;
    V = 10;
    DFS_Kanapsack(0, 0, 0);
    System.out.println(MaxValue);
  }

  public static void testweightbagproblem(int[] weight, int[] value, int bagsize){
    int wlen = weight.length, value0 = 0;
    //定义dp数组：dp[i][j]表示背包容量为j时，前i个物品能获得的最大价值
    int[][] dp = new int[wlen + 1][bagsize + 1];
    //初始化：背包容量为0时，能获得的价值都为0
    for (int i = 0; i <= wlen; i++){
      dp[i][0] = value0;
    }
    //遍历顺序：先遍历物品，再遍历背包容量
    for (int i = 1; i <= wlen; i++){
      for (int j = 1; j <= bagsize; j++){
        if (j < weight[i - 1]){
          dp[i][j] = dp[i - 1][j];
        }else{
          dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
        }
      }
    }
    int[] used = new int[wlen];
    int x = wlen;
    int v = bagsize;
    while (v > 0 && x > 0) {
      if (dp[x][v] != dp[x - 1][v]){
        System.out.print(dp[x][v]);
        v -= weight[--x];
        used[x] = 1;
        continue;
      }
      x--;
    }
    System.out.println(Arrays.toString(used));

    for (int i = 0; i <= wlen; i++){
      for (int j = 0; j <= bagsize; j++){
        System.out.print(dp[i][j] + " ");
      }
      System.out.print("\n");
    }
  }

}

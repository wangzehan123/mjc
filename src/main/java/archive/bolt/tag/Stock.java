package archive.bolt.tag;

public class Stock {
/*
* 回到本题来，我们的目标是求解最大利润，其中有两个状态属性：「交易最大次数k」和「天数n」。当天面临的选择也很简单：「不做处理」和「卖出股票」，
* 暂时先不考虑何时买入的问题。我们定义dp[k + 1][n]，元素dp[i][j]表示最多交易i次时在第j天能获得的最大利润。
* dp[i][j] = max(不做处理， 卖出股票)
* 如果不做处理，第j天的最大利润就和第j-1天相等。
如果卖出股票，设是在第m天买入，那么第j天的最大利润就是两天的价格差+最多交易i-1次时第m天的获利。当然，这里的m需要从0遍历到j-1。
* 当k为常数时，我们需要重新进行复杂度分析，复杂度中不再需要考虑k的影响。
* 时间复杂度:
O(n)遍历dp的每个元素。空间复杂度: O(n)考虑dp数组占用的空间。
* */

  public int maxProfit(int[] prices) {
    int n = prices.length;
    int K = 2;
    // corner case
    if (n == 0 || K == 0){
      return 0;
    }
    // main part
    int[][] dp = new int[K + 1][n];
    for (int i = 1; i < dp.length; i ++){
      int maxDiff = -prices[0];
      for (int j = 1; j < dp[0].length; j ++){
        maxDiff = Math.max(maxDiff, dp[i - 1][j - 1] - prices[j]);
        dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff);
      }
    }
    return dp[K][n - 1];
  }
}

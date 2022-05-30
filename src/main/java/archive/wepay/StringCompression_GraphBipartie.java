package archive.wepay;

import java.util.Arrays;

public class StringCompression_GraphBipartie {

  public int compress(char[] chars) {
    int indexAns = 0, index = 0;
    while(index < chars.length){
      char currentChar = chars[index];
      int count = 0;
      while(index < chars.length && chars[index] == currentChar){
        index++;
        count++;
      }
      chars[indexAns++] = currentChar;
      if(count != 1)
        for(char c : Integer.toString(count).toCharArray())
          chars[indexAns++] = c;
    }
    return indexAns;
  }

  public int getLengthOfOptimalCompression(String s, int k) {
    // dp[i][k]: the minimum length for s[:i] with at most k deletion.
    int n = s.length();
    int[][] dp = new int[110][110];
    for (int i = 0; i <= n; i++) for (int j = 0; j <= n; j++) dp[i][j] = 9999;
    // for (int[] i : dp) Arrays.fill(i, n); // this is a bit slower (100ms)
    dp[0][0] = 0;
    for(int i = 1; i <= n; i++) {
      for(int j = 0; j <= k; j++) {
        int cnt = 0, del = 0;
        for(int l = i; l >= 1; l--) { // keep s[i], concat the same, remove the different
          if(s.charAt(l - 1) == s.charAt(i - 1)) cnt++;
          else del++;
          if(j - del >= 0)
            dp[i][j] = Math.min(dp[i][j],
                dp[l-1][j-del] + 1 + (cnt >= 100 ? 3 : cnt >= 10 ? 2 : cnt >= 2 ? 1: 0));
        }
        if (j > 0) // delete s[i]
          dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
      }
    }
    return dp[n][k];
  }

  public boolean isBipartite(int[][] graph) {
    int n = graph.length;
    int[] colors = new int[n];
    Arrays.fill(colors, -1);

    for (int i = 0; i < n; i++) {       //This graph might be a disconnected graph. So check each unvisited node.
      if (colors[i] == -1 && !validColor(graph, colors, 0, i)) {
        return false;
      }
    }
    return true;
  }

  public boolean validColor(int[][] graph, int[] colors, int color, int node) {
    if (colors[node] != -1) {
      return colors[node] == color;
    }
    colors[node] = color;
    for (int next : graph[node]) {
      if (!validColor(graph, colors, 1 - color, next)) {
        return false;
      }
    }
    return true;
  }
}

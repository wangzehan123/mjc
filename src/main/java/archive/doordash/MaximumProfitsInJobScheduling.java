package archive.doordash;

import java.util.Arrays;

public class MaximumProfitsInJobScheduling {

  class Job {

    int startTime;
    int endTime;
    int profit;

    Job(int st, int et, int pt) {
      startTime = st;
      endTime = et;
      profit = pt;
    }

    @Override
    public String toString() {
      return "Job [startTime=" + startTime + ", endTime=" + endTime + ", profit=" + profit + "]";
    }
  }

  public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {

    int len = startTime.length;
    Job[] jobs = new Job[len];
    for (int i = 0; i < len; i++) {
      jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
    }
    Arrays.sort(jobs, (a, b) -> a.endTime
        - b.endTime); // sort by 'endTime', because we have to make sure that all the visited jobs have ended.

    int[] dp = new int[len]; // dp[i] := maxProfit from jobs[0] to jobs[i] (might not be included)

    dp[0] = jobs[0].profit;

    for (int i = 1; i < len; i++) {
      int curProfit = jobs[i].profit; // profit of jobs[i]
      int curMax = Math.max(curProfit, dp[i - 1]); // two choices: 1. include jobs[i]; 2. exclude jobs[i]
      for (int j = i - 1; j >= 0; j--) {
        if (jobs[j].endTime
            <= jobs[i].startTime) { // try to find a previous time slot that does not have overlap with jobs[i]. (jobs[0] <-- jobs[i - 1])
          curMax = Math.max(curMax, dp[j] + curProfit);
          break; // once we find a valid jobs[j], no need to check jobs before it, because dp[j] is the max in dp[0] ~ dp[j]
        }
      }
      dp[i] = curMax;
    }
    return dp[dp.length - 1];
  }
}

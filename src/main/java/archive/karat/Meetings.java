package archive.karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Meetings {

  public boolean canAttendMeetings(int[][] meetings, int[] newMeeting) {
    for (int[] meeting : meetings) {
      if (overlap(meeting, newMeeting)) {
        return false;
      }
    }
    return true;
  }

  private boolean overlap(int[] interval1, int[] interval2) {
    return (interval1[0] >= interval2[0] && interval1[0] < interval2[1])
        || (interval2[0] >= interval1[0] && interval2[0] < interval1[1]);
  }


  public List<int[]> merge(int[][] intervals) {
    Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
    List<int[]> ans  =  new ArrayList<>();
    int start = intervals[0][0];
    int end = intervals[0][1];
    int i = 1;
    while (i < intervals.length) {
      int s = intervals[i][0];
      int e = intervals[i][1];

      if (s <= end) {
        end = Math.max(end, e);
      }else {
        ans.add(new int[]{start, end});
        start = s;
        end = e;
      }
      i++;
    }
    ans.add(new int[] {start, end});
    List<int[]> free = new ArrayList<>();
    int[] first = {0, ans.get(0)[0]};
    free.add(first);
    if (ans.size() == 1) {
      return free;
    }
    for (int x = 1; x < ans.size(); x++) {
      int freeEnd = ans.get(x)[0];
      int freeStart = ans.get(x - 1)[1];
      free.add(new int[]{freeStart, freeEnd});
    }
    return free;
  }
}

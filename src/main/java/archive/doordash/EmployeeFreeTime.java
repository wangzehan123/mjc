package archive.doordash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeFreeTime {

    public static class Interval {
        public int start;
      public int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

  /**
   * @param schedule: a list schedule of employees
   * @return: Return a list of finite intervals
   * 先把所有start和end都分别放到两个list中并排序，然后用同向双指针比较下一个interval的start和前一个interval的end，
   * 如果下一个interval的start大于前一个interval的end，那么就找到了一个free time的interval
   */
  public List<Interval> employeeFreeTime(int[][] schedule) {
    // Write your code here
    List<Integer> starts = new ArrayList<>();
    List<Integer> ends = new ArrayList<>();
    for(int i = 0; i < schedule.length; i++) {
      for (int j = 0; j < schedule[i].length; j++) {
        if (j % 2 == 0) {
          starts.add(schedule[i][j]);
        } else {
          ends.add(schedule[i][j]);
        }
      }
    }
    Collections.sort(starts);
    Collections.sort(ends);

    int startIndex = 1;
    int endIndex = 0;
    List<Interval> result = new ArrayList<>();

    //If the start of next interval is greater than the end of the previous interval, then a free time found
    while (startIndex < starts.size()) {
      if (starts.get(startIndex) > ends.get(endIndex)) {
        result.add(new Interval(ends.get(endIndex), starts.get(startIndex)));
      }
      startIndex++;
      endIndex++;
    }
    return result;
  }
}

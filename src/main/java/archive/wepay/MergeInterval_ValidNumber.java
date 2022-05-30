package archive.wepay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MergeInterval_ValidNumber {

  public int[][] merge(int[][] intervals) {
    if (intervals.length <= 1)
      return intervals;

    // Sort by ascending starting point
    Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

    List<int[]> result = new ArrayList<>();
    int[] newInterval = intervals[0];
    result.add(newInterval);
    for (int[] interval : intervals) {
      if (interval[0] <= newInterval[1]) // Overlapping intervals, move the end if needed
        newInterval[1] = Math.max(newInterval[1], interval[1]);
      else {                             // Disjoint intervals, add the new interval to the list
        newInterval = interval;
        result.add(newInterval);
      }
    }

    return result.toArray(new int[result.size()][]);
  }

  public boolean isNumber(String s) {
    int len = s.length();
    int i = 0, e = len - 1;
    while (i <= e && Character.isWhitespace(s.charAt(i)))
      i++;
    if (i > len - 1)
      return false;
    while (e >= i && Character.isWhitespace(s.charAt(e)))
      e--;
    // skip leading +/-
    if (s.charAt(i) == '+' || s.charAt(i) == '-')
      i++;
    boolean num = false; // is a digit
    boolean dot = false; // is a '.'
    boolean exp = false; // is a 'e'
    while (i <= e) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        num = true;
      } else if (c == '.') {
        if (exp || dot)
          return false;
        dot = true;
      } else if (c == 'e') {
        if (exp || num == false)
          return false;
        exp = true;
        num = false;
      } else if (c == '+' || c == '-') {
        if (s.charAt(i - 1) != 'e')
          return false;
      } else {
        return false;
      }
      i++;
    }
    return num;
  }

  public List<Interval> merge(List<Interval> intervals) {
    if (intervals.size() <= 1)
      return intervals;

    // Sort by ascending starting point using an anonymous Comparator
    intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));

    List<Interval> result = new LinkedList<Interval>();
    int start = intervals.get(0).start;
    int end = intervals.get(0).end;

    for (Interval interval : intervals) {
      if (interval.start <= end) // Overlapping intervals, move the end if needed
        end = Math.max(end, interval.end);
      else {                     // Disjoint intervals, add the previous one and reset bounds
        result.add(new Interval(start, end));
        start = interval.start;
        end = interval.end;
      }
    }

    // Add the last interval
    result.add(new Interval(start, end));
    return result;
  }

  class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
}

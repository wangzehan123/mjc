package microsoft.mj;

import archive.doordash.EmployeeFreeTime.Interval;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
* The idea is to sort the intervals by their starting points. Then, we take the first interval and compare its end with the next intervals starts. As long as they overlap,
* we update the end to be the max end of the overlapping intervals. Once we find a non overlapping interval, we can add the previous "extended" interval and start over.
* Sorting takes O(n log(n)) and merging the intervals takes O(n). So, the resulting algorithm takes O(n log(n)).
* */
public class MergeInterval {

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
}

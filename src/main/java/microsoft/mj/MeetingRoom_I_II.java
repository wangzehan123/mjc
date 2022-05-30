package microsoft.mj;

import archive.doordash.EmployeeFreeTime.Interval;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoom_I_II {

  public boolean canAttendMeetings(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
    for (int i = 0; i < intervals.length - 1; i++) {
      if (intervals[i][1] > intervals[i + 1][0]) {
        return false;
      }
    }
    return true;
  }

  public int minMeetingRooms(Interval[] intervals) {
    if (intervals == null || intervals.length == 0) return 0;
    int n = intervals.length, index = 0;
    int[] begins = new int[n];
    int[] ends = new int[n];
    for (Interval i: intervals) {
      begins[index] = i.start;
      ends[index] = i.end;
      index++;
    }
    Arrays.sort(begins);
    Arrays.sort(ends);
    int rooms = 0, pre = 0;
    for (int i = 0; i < n; i++) {
      rooms++;
      if (begins[i] >= ends[pre]) {
        rooms--;
        pre++;
      }
    }
    return rooms;
  }

  public int minMeetingRooms(int[][] intervals) {
    // Check for the base case. If there are no intervals, return 0
    if (intervals.length == 0) {
      return 0;
    }
    // Min heap
    PriorityQueue<Integer> allocator =
        new PriorityQueue<Integer>(
            intervals.length,
            new Comparator<Integer>() {
              public int compare(Integer a, Integer b) {
                return a - b;
              }
            });
    // Sort the intervals by start time
    Arrays.sort(
        intervals,
        new Comparator<int[]>() {
          public int compare(final int[] a, final int[] b) {
            return a[0] - b[0];
          }
        });
    // Add the first meeting
    allocator.add(intervals[0][1]);
    // Iterate over remaining intervals
    for (int i = 1; i < intervals.length; i++) {
      // If the room due to free up the earliest is free, assign that room to this meeting.
      if (intervals[i][0] >= allocator.peek()) {
        allocator.poll();
      }
      // If a new room is to be assigned, then also we add to the heap,
      // If an old room is allocated, then also we have to add to the heap with updated end time.
      allocator.add(intervals[i][1]);
    }
    // The size of the heap tells us the minimum rooms required for all the meetings.
    return allocator.size();
  }
}

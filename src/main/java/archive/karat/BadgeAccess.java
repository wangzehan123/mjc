package archive.karat;


import java.util.*;
/*
We want to find employees who badged into our secured room together often. Given an unordered list of
names and access times over a single day, find the largest group of people that were in the room together during
two or more separate time periods, and the times when they were all present.

badge_records = [
["Paul", "1214", "enter"],
["Paul", "830", "enter"],
["Curtis", "1100", "enter"],
["Paul", "903", "exit"],
["John", "908", "exit"],
["Paul", "1235", "exit"],
["Jennifer", "900", "exit"],
["Curtis", "1330", "exit"],
["John", "815", "enter"],
["Jennifer", "1217", "enter"],
["Curtis", "745", "enter"],
["John", "1230", "enter"],
["Jennifer", "800", "enter"],
["John", "1235", "exit"],
["Curtis", "810", "exit"],
["Jennifer", "1240", "exit"],
]
*/

public class BadgeAccess {

  public static void main(String[] args) {
    String[][] badge_records = {
        {"Paul", "1335"},{"Jennifer", "1910"},
        {"John", "830"},{"Paul","1315"},
        {"John", "835"},{"Paul", "1405"},
        {"Paul", "1630"},{"John","855"},
        {"John", "915"},{"John", "930"},
        {"Jennifer", "1335"},{"Jennifer", "730"},
        {"John", "1630"}
    };
    find(badge_records);

    String[][] records = {
        {"Paul", "1214", "enter"},
        {"Paul", "830", "enter"},
        {"Curtis", "1100", "enter"},
        {"Paul", "903", "exit"},
        {"John", "908", "exit"},
        {"Paul", "1235", "exit"},
        {"Jennifer", "900", "exit"},
        {"Curtis", "1330", "exit"},
        {"John", "815", "enter"},
        {"Jennifer", "1217", "enter"},
        {"Curtis", "745", "enter"},
        {"John", "1230", "enter"},
        {"Jennifer", "800", "enter"},
        {"John", "1235", "exit"},
        {"Curtis", "810", "exit"},
        {"Jennifer", "1240", "exit"},
    };
    String[][] mytest = {
        {"a", "1", "enter"}, // a
        {"b", "2", "enter"}, // ab
        {"c", "3", "enter"}, // abc
        {"b", "4", "exit"}, // ac
        {"c", "5", "exit"}, // a
        {"a", "6", "exit"}, // x
        {"b", "7", "enter"}, // b
        {"a", "7", "enter"}, // ab
        {"b", "8", "exit"}, // a
        {"a", "9", "exit"}, // x
    };
    findLargeGroup(mytest);
  }

  public static void find(String[][] records){
    Map<String, List<Integer>> p2rec = new HashMap<>();
    for (String[] record : records) {
      p2rec.putIfAbsent(record[0], new ArrayList<>());
      p2rec.get(record[0]).add(Integer.parseInt(record[1]));
    }
    // for each employee, see if badged too often
    for (String p : p2rec.keySet()) {
      List<Integer> rec = p2rec.get(p);
      Collections.sort(rec);
      int[] pair = helper(rec);
      if (pair[0] != -1) {
        List<Integer> visits = new ArrayList<>();
        for (int i = pair[0]; i <= pair[1]; i++) {
          visits.add(rec.get(i));
        }
        System.out.println(p + ": " + visits);
      }
    }
  }

  private static int[] helper(List<Integer> list){
    int start = 0;
    for(int end = 0; end < list.size(); end++){
      while(list.get(end) - list.get(start) > 100){
        start++;
      }

      if(end - start >= 2){
        while(end < list.size() && list.get(end) - list.get(start) <= 100) end++;
        return new int[]{start, end-1};
      }
    }
    //don't find a valid hour
    return new int[]{-1,-1};
  }

  public static void findLargeGroup(String[][] records) {
    String Enter = "enter";
    // sort by time and exit 1 enter 2
    Arrays.sort(records, (o1, o2) -> {
      int t1 = Integer.parseInt(o1[1]);
      int t2 = Integer.parseInt(o2[1]);
      if (t1 != t2) {
        return t1 - t2;
      } else {
        // enter compare to exit
        return o2[2].compareTo(o1[2]);
      }
    });

    Set<String> seenGroup = new HashSet<>();
    // people currently in the room
    Set<String> curGroup = new HashSet<>();

    int maxSize = 0;
    String maxGroupKey = "";

    // first pass find qualified group
    for (String[] record : records) {
      if (record[2].equals(Enter)) {
        String pToEnter = record[0];
        curGroup.add(pToEnter);
        String newGroupKey = getKey(curGroup);
        // if newGroupKey been seen
        if (seenGroup.contains(newGroupKey)) {
          if (curGroup.size() > maxSize) {
            maxSize = curGroup.size();
            maxGroupKey = newGroupKey;
          }
        } else {
          // try to find intersection with seenGroup
          for (String seenKey : seenGroup) {
            HashSet<String> seenSet = new HashSet<>(Arrays.asList(seenKey.split(",")));
            // find intersection
            seenSet.retainAll(curGroup);
            // update result if needed
            if (seenSet.size() > maxSize) {
              maxSize = seenSet.size();
              maxGroupKey = getKey(seenSet);
            }
          }
        }
        seenGroup.add(newGroupKey);

      } else {
        // remove employee from group
        curGroup.remove(record[0]);
      }
    }

    // find time
    curGroup.clear();
    Set<String> maxSet = new HashSet<>(Arrays.asList(maxGroupKey.split(",")));
    List<String[]> timeList = new ArrayList<>();
    String lastEnter = "";
    for (String[] record : records) {
      if (record[2].equals(Enter)) {
        curGroup.add(record[0]);
        if (maxSet.contains(record[0])) {
          lastEnter = record[1];
        }
      } else {
        // cur group has every member and person about to leave is in the group
        if (curGroup.containsAll(maxSet) && curGroup.contains(record[0])) {
          timeList.add(new String[]{lastEnter, record[1]});
        }
        curGroup.remove(record[0]);
      }
    }
    System.out.println(maxSet);
    for (String[] time : timeList) {
      System.out.println(Arrays.toString(time));
    }

  }

  private static String getKey(Set<String> set) {
    List<String> list = new ArrayList<>(set);
    Collections.sort(list);
    return String.join(",", list);
  }
}


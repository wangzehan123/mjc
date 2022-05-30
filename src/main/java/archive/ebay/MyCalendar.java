package archive.ebay;

import java.util.TreeMap;

class MyCalendar {

  TreeMap<Integer, Integer> calendar;

  public MyCalendar() {
    calendar = new TreeMap<>();
  }

  public boolean book(int start, int end) {
    Integer floorKey = calendar.floorKey(start);
    if (floorKey != null && calendar.get(floorKey) > start)
      return false;
    Integer ceilingKey = calendar.ceilingKey(start);
    if (ceilingKey != null && ceilingKey < end)
      return false;
    calendar.put(start, end);
    return true;
  }

  public static void main(String[] args) {
    TreeMap<Integer, Integer> tree_map = new TreeMap<>();
    tree_map.put(10, 20);
    tree_map.put(20, 30);
    tree_map.put(40, 50);
    System.out.println(tree_map.floorKey(15) + "" + tree_map.ceilingKey(30));
  }

}

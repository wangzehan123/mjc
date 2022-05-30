package archive.instacart.wildcardmatching;

public class WildCardMatching {

  public static void main(String[] args) {
    System.out.println(findMatching("abcdef", "b*d"));
    System.out.println(findMatching("abcdef", "a*c*d"));
    System.out.println(findMatching("abcdef", "b**f"));
    System.out.println(findMatching("abcdef", "c*"));
    System.out.println(findMatching("abcdef", "*f*"));
    System.out.println(findMatching("abcdef", "*z*"));
    System.out.println(findMatching("baabcdefabba", "ab*ba"));
    System.out.println(findMatching("baabba", "ab*b"));
  }

  public static int findMatching(String s, String target) {
    if (target.length() > s.length()) {
      return -1;
    }
    String[] arr = target.split("\\*");
    int index = Integer.MIN_VALUE;
    int start = 0;
    for (String sub : arr) {
      if (sub.length() == 0) {
        continue;
      }
      int currentIndex = s.indexOf(sub, start);
      if (currentIndex == -1 || currentIndex < index) {
        return -1;
      }
      index = currentIndex;
      start = index;
    }
    if (target.charAt(0) == '*') {
      return 0;
    }
    return s.indexOf(arr[0]);
  }

}

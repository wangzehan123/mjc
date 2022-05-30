package archive.instacart.wildcardmatching;

public class PM {

  public static void main(String[] args) {
    String text = "CDFGAGB";
    String pattern = "*A*";
    System.out.println(patternMatch("abcdef", "b*d"));
    System.out.println(patternMatch("abcdef", "a*c*d"));
    System.out.println(patternMatch("abcdef", "b**f"));
    System.out.println(patternMatch("abcdef", "c*"));
    System.out.println(patternMatch("abcdef", "*f*"));
    System.out.println(patternMatch("abcdef", "*z*"));
    System.out.println(patternMatch("baabcdefabba", "ab*ba"));
    System.out.println(patternMatch("baabba", "ab*b"));
    System.out.println(patternMatch( "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaad", "*aaaaaaaaaaaaaaad"));
  }


  public static int patternMatch(String s, String pattern) {
    String[] patternList = pattern.split("\\*");
    if (patternList.length == 0) {
      return 0;
    }
    if (patternMatch(s, patternList, 0)) {
      return getIndex(s, patternList[0]);
    } else {
      return -1;
    }
  }

  public static boolean patternMatch(String s, String[] pattern, int index) {
    while (index < pattern.length && pattern[index] == "") {
      index++;
    }
    if (index == pattern.length) {
      return true;
    }
    int patIndex = getIndex(s, pattern[index]);
    if (patIndex == -1) {
      return false;
    }
    if (index == pattern.length - 1) {
      return true;
    }
    if (patIndex + pattern[index].length() >= s.length()) {
      return false;
    }
    return patternMatch(s.substring(patIndex + pattern[index].length()), pattern, index + 1);
  }

  public static int getIndex(String s, String pattern) {
    int m = s.length();
    int n = pattern.length();
    for (int i = 0; i <= m - n; i++) {
      boolean notMatch = false;
      for (int j = 0; j < n; j++) {
        if (s.charAt(i + j) != pattern.charAt(j)) {
          notMatch = true;
          break;
        }
      }
      if (!notMatch) {
        return i;
      }
    }
    return -1;
  }
}

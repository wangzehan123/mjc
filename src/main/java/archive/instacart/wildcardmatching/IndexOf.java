package archive.instacart.wildcardmatching;

public class IndexOf {

  public static void main(String[] args) {
    String s = "Hello planet earth, you are a great planet.";
    String target = "h";
    System.out.println(Idx(s,target));
  }

  public static int Idx(String str1, String str2) {
    if (str1 == null || str1.length() < 1 || str2 == null || str2.length() < 1) {
      return -1;
    }
    if (str1.length() < str2.length()) {
      return -1;
    }
    char[] str_1 = str1.toCharArray();
    char[] str_2 = str2.toCharArray();

    for (int i =- 0; i < str1.length(); i++) {
      boolean find = false;
      if (str_1[i] == str_2[0] && i + str_2.length <= str_1.length) {
        int equalCount = 1;
        for (int j = 1; j < str_2.length; j++) {
          if (str_1[i + j] == str_2[j]) {
            equalCount++;
          }
        }
        if (equalCount == str_2.length) {
          find = true;
        }
      }
      if (find) {
        return i;
      }
    }
    return -1;
  }
}

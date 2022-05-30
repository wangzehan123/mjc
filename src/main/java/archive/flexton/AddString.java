package archive.flexton;

public class AddString {

  /**
   * @param num1: a non-negative integers
   * @param num2: a non-negative integers
   * @return: return sum of num1 and num2
   */
  public static String addStrings(String num1, String num2) {
    // write your code here
    StringBuilder res = new StringBuilder();
    int carry = 0;
    for(int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0; i--, j--) {
      int sum = carry;
      sum += (i >= 0) ? num1.charAt(i) - '0' : 0;
      sum += (j >= 0) ? num2.charAt(j) - '0' : 0;
      res.insert(0, (char)(sum % 10 + '0'));
      carry = sum / 10;
    }
    if (carry != 0) {
      res.insert(0, "1");
    }
    return res.toString();
  }

  public static void main(String[] args)
  {
    String str = "geeksforgeeks";
    addStrings("138", "246");
  }
}

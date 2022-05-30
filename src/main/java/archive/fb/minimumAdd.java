package archive.fb;

public class minimumAdd {

  // Function to return required minimum number
  static int minParentheses(String p)
  {

    // maintain balance of string
    int bal = 0;
    int ans = 0;

    for (int i = 0; i < p.length(); ++i) {

      bal += p.charAt(i) == '(' ? 1 : -1;

      // It is guaranteed bal >= -1
      if (bal == -1) {
        ans += 1;
        bal += 1;
      }
    }

    return bal + ans;
  }
}

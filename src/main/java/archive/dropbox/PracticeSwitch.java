package archive.dropbox;

public class PracticeSwitch {

  public static void main(String[] args) {
    String[][] arr = {
        {"APPEND", "HELLO"},
        {"CUT", "CUT"},
        {"PASTE", "PASTE"}
    };
    for (String[] s : arr) {
      String expression = s[0];
      switch (expression) {
        case "APPEND":
          System.out.println("APPEND");
          continue;
        case "CUT":
          System.out.println("CUT");
          continue;
        case "PASTE":
          System.out.println("PASTE");
          continue;
      }
    }
  }
}

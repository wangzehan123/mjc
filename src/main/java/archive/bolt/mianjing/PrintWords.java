package archive.bolt.mianjing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintWords {

  public static void main(String[] args) {
    String s = "sd... jkj11jl/ tes apple tyuia iopuya opiu";
    System.out.println(vertical(s, 3));
  }

  public static List<List<String>> vertical(String s, int N) {
    String[] arr = s.split(" ");
    for (int j = 0; j < arr.length; j++) {
      arr[j] = arr[j].replaceAll("[^a-zA-Z]", "");
    }
    Arrays.sort(arr);
    List<List<String>> result = new ArrayList<>();
    int counter = 0;
    for (String a : arr) {
      if (counter % N == 0) {
        result.add(new ArrayList<>());
      }
      result.get(result.size() - 1).add(a);
      counter++;
    }
    return result;
  }

  public static StringBuilder appendWhiteSpace(StringBuilder sb, int num) {
    while (num > 0) {
      sb.append(" ");
      num--;
    }
    return sb;
  }

}

package archive.instacart.ps;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PassScanner {

  public static Character getCode(Scanner scanner) {
    String line;
    int i = 0, x = -1, y = -1;
    List<String> list = new ArrayList<>();
    while (((line = scanner.nextLine()) != null) &&
        !line.isEmpty()) {
      if (i == 0) {
        String[] strs =
            line.replace("[", "")
            .replace("]", "")
            .replace(" ", "")
                .split(",");
        y = Integer.parseInt(strs[0]);
        x = Integer.parseInt(strs[1]);
      } else {
        list.add(line);
      }
      i++;
    }
    int m = list.size(), n = list.get(0).length();
    if (x >= m || y >= n) {
      return null;
    }
    return list.get(m - x - 1).charAt(y);
  }

  public static void main(String[] args) throws FileNotFoundException {
    File f = new File("C:\\Users\\Y2T\\Desktop\\Wyatt\\DNM\\Code\\mianjing-code\\src\\main\\java\\instacart\\ps\\password1.txt");
    Scanner scanner = new Scanner(f);
    System.out.println(getCode(scanner));
  }
}

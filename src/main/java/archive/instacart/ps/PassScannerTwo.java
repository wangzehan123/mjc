package archive.instacart.ps;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class PassScannerTwo {

  public static String genPassword(Scanner scanner) {
    char[] chars = new char[100];
    int consecutiveEmptyLines = 0, index = 0, x = 0, y = 0;
    List<String> list = new ArrayList<>();
    ArrayList<String> ps = new ArrayList<String>();
    HashSet<Integer> set = new HashSet<>();
    while (scanner.hasNext()){
      ps.add(scanner.nextLine());
    }
    for (String line : ps) {
      if (line.matches("[0-9]+")){ // digits
        Integer currentIndex = Integer.parseInt(line);
        if (set.contains(currentIndex)) {
          break;
        }
        if (!list.isEmpty() && x < list.size() && y < list.get(0).length()) {
          chars[index] = list.get(list.size() - x - 1).charAt(y);
          list = new ArrayList<>();
        }
        consecutiveEmptyLines = 0;
        index = currentIndex;
        set.add(index);
      } else if (line.indexOf('[') != -1) {
        String[] strs = line.replace("[", "")
            .replace("]", "")
            .replace(" ", "").split(",");
        y = Integer.parseInt(strs[0]);
        x = Integer.parseInt(strs[1]);
      } else if (!line.isEmpty()) {
        list.add(line);
      } else { // empty
        consecutiveEmptyLines++;
      }
    }
    if (!list.isEmpty()) {
      chars[index] = list.get(list.size() - x - 1).charAt(y);
    }
    StringBuilder sb = new StringBuilder();
    return new String(chars).trim();
  }

  public static void main(String[] args) throws FileNotFoundException {
    File f = new File("C:\\Users\\Y2T\\Desktop\\Wyatt\\DNM\\Code\\mianjing-code\\src\\main\\java\\instacart\\ps\\password2.txt");
    Scanner scanner = new Scanner(f);
    System.out.println(genPassword(scanner));
  }
}


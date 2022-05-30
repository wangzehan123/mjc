package affirm;

import java.util.HashMap;
import java.util.Map;

public class ShortestSubString {

  /* Input: ["cheapair", "cheapoair", "peloton", "pelican"] Output:     "cheapair": "pa"
  // every other 1-2 length substring overlaps with cheapoair     "cheapoair": "po"
  // "oa" would also be acceptable     "pelican": "ca"
  // "li", "ic", or "an" would also be acceptable     "peloton": "t"
  // this single letter doesn't occur in any other string */

  public static Map<String, String> findShortestUniqueSubstring(String[] names) {
    Map<String, String> res = new HashMap<>();
    for (String name : names) {
      res.put(name, name);
      for (int i = 0; i < name.length(); i++) {
        for (int j = i + 1; j <= name.length(); j++) {
          String substring = name.substring(i, j);
          boolean unique = true;
          for (String other : names) {
            if (other.equals(name)) {
              continue;
            }
            if (other.contains(substring)) {
              unique = false;
              break;
            }
          }
          if (unique && res.get(name).length() > substring.length()) {
            res.put(name, substring);
          }
        }
      }
    }
    return res;
  }
}

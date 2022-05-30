package archive.bolt.mianjing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPattern_I_II {
    public boolean wordPattern(String pattern, String s) {
      Map<Character, String> dic = new HashMap<>(); // the projection map, key is the char in pattern and the value is a word
      Set<String> word_set = new HashSet<>();;
      Set<Character> pattern_set = new HashSet<>();
      String[] words = s.split(" ");
      for (String word: words) word_set.add(word);
      for (char c: pattern.toCharArray()) pattern_set.add(c);
      // 'aa' -> 'dog', 'ab' -> 'dog dog', 'abc'- > 'cat dog cat'
      if (words.length != pattern.length() || word_set.size() != pattern_set.size()) return false;
      for (int i = 0; i < words.length; i++) {
        if (dic.containsKey(pattern.charAt(i))) { // pattern[i] has been projected to some word
          if (!dic.get(pattern.charAt(i)).equals(words[i])) return false; // 'aba' -> 'cat dog dog'
        } else {
          dic.put(pattern.charAt(i), words[i]);
        }
      }
      return true;
    }

  public boolean wordPatternMatch(String pattern, String str) {
    Map<Character, String> map = new HashMap<>();
    Set<String> set = new HashSet<>();

    return isMatch(str, 0, pattern, 0, map, set);
  }

  boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
    // base case
    if (i == str.length() && j == pat.length()) return true;
    if (i == str.length() || j == pat.length()) return false;

    // get current pattern character
    char c = pat.charAt(j);
    // if the pattern character exists
    if (map.containsKey(c)) {
      String s = map.get(c);
      // then check if we can use it to match str[i...i+s.length()]
      if (!str.startsWith(s, i)) {
        return false;
      }
      // if it can match, great, continue to match the rest
      return isMatch(str, i + s.length(), pat, j + 1, map, set);
    }
    // pattern character does not exist in the map
    for (int k = i; k < str.length(); k++) {
      String p = str.substring(i, k + 1);
      if (set.contains(p)) {
        continue;
      }
      // create or update it
      map.put(c, p);
      set.add(p);
      // continue to match the rest
      if (isMatch(str, k + 1, pat, j + 1, map, set)) {
        return true;
      }
      // backtracking
      map.remove(c);
      set.remove(p);
    }
    // we've tried our best but still no luck
    return false;
  }

}

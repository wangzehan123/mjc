package archive.instacart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
* Working Java solution. Basically, first count the number of each type of card you have.
* This is O(N) time and O(1) space (27 possibilities - 3 types * 3 values * 3 counts).
* Next, iterate through the number of cards and for each card, iterate through the number of types.
* Given two cards, you can write logic to generate the third one. For example, if the first two cards are +B, and -A,
* then we know the third card must be =C. First two cards have different types, so third must be different.
* First two cards have different values, so third must be different. First two cards have same length,
* so third one must have same count as well. Then, we can just look up all three cards in our count map, and if all exist,
* we have found a valid pair! Otherwise, we will continue iterating. This solution runs in O(81*n) time = O(N) time and O(1) space.
* */
public class CardMatching {

  public static void main(String[] args) {
    String s = "iusop";
    System.out.println(indexOf(s));
  }

  private static int indexOf(String s) {
    return s.indexOf("s");
  }

  private static List<String> cards (List<String> cards) {
    Set<Character> types = new HashSet<>(Arrays.asList('+', '-', '='));
    Set<Character> colors = new HashSet<>(Arrays.asList('A', 'B', 'C'));
    Set<Integer> counts = new HashSet<>(Arrays.asList(2, 3, 4));

    Map<String, Integer> countOfCards = new HashMap<>();
    for (String card: cards) {
      countOfCards.put(card, countOfCards.getOrDefault(card, 0) + 1);
    }

    for (String card: cards) {
      for (char type: types) {
        for (char color: colors) {
          for (int count: counts) {
            String secondCard = "" + type;
            for (int i = 1 ; i < count; i++) secondCard += color;
            String thirdCard = getThirdCard(card, secondCard, new HashSet<>(types), new HashSet<>(colors), new HashSet<>(counts));
            if (checkPresent(countOfCards, card, secondCard, thirdCard)) return Arrays.asList(card, secondCard, thirdCard);
          }
        }
      }
    }
    return new ArrayList<>();
  }

  private static boolean checkPresent (Map<String, Integer> countMap, String firstCard, String secondCard, String thirdCard) {
    if (firstCard.equals(secondCard)) {
      return countMap.getOrDefault(firstCard, 0) >= 3;
    }
    return countMap.getOrDefault(firstCard, 0) >= 1 &&
        countMap.getOrDefault(secondCard, 0) >= 1 &&
        countMap.getOrDefault(thirdCard, 0) >= 1;
  }

  private static String getThirdCard(String firstCard,
      String secondCard,
      Set<Character> types,
      Set<Character> colors,
      Set<Integer> counts) {
    char firstType = firstCard.charAt(0);
    char firstColor = firstCard.charAt(1);
    int firstLength = firstCard.length();

    char secondType = secondCard.charAt(0);
    char secondColor = secondCard.charAt(1);
    int secondLength = secondCard.length();

    char thirdType;
    char thirdColor;
    int thirdLength;
    if (firstType == secondType) thirdType = firstType;
    else {
      types.remove(firstType);
      types.remove(secondType);
      thirdType = types.iterator().next();
    }

    if (firstColor == secondColor) thirdColor = firstColor;
    else {
      colors.remove(firstColor);
      colors.remove(secondColor);
      thirdColor = colors.iterator().next();
    }

    if (firstLength == secondLength) thirdLength = firstLength;
    else {
      counts.remove(firstLength);
      counts.remove(secondLength);
      thirdLength = counts.iterator().next();
    }

    String thirdCard = "" + thirdType;
    for (int i = 1; i < thirdLength; i++) thirdCard += thirdColor;
    return thirdCard;
  }
}

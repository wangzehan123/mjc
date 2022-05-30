/*
* You operate a marketplace for buying & selling used textbooks. For a given textbook, e.g. “Theory of Cryptography,” there are people who want to buy this textbook and people who want to sell.

Offers to BUY: $100, $100, $99, $99, $97, $90
Offers to SELL: $109, $110, $110, $114, $115, $119
A match occurs when two people agree on a price. Some new offers do not match. These offers should be added to the active set of offers.
For example:Tim offers to SELL at $150. This will not match. No one is willing to buy at that price so we save the offer.
Offers to SELL:: $109, $110, $110, $114, $115, $119, $150
When matching we want to give the customer the “best price”. Example matches:
If Jane offers to BUY at $120, she will match and buy a book for $109 (the lowest offer to sell is the best price).
The sell offers should be updated to reflect the match Offers to SELL: $110, $110, $114, $115, $119, $150
If Connie offers to SELL at $99 she will match and sell her book for $100 (the highest offer to buy is the best price).
* The buy offers should be updated to reflect the match Offers to BUY: $100, $99, $99, $97, $90
Our system will need to:
Accept incoming offers to buy & sell
Output if the price matches
Keep an updated collections of buys & sells

* */

package archive.coinbase.phone;

import java.util.Collections;
import java.util.PriorityQueue;
class Solution {

public static class TextBook {

  PriorityQueue<Integer> buyList;
  PriorityQueue<Integer> sellList;

  public TextBook() {
    buyList = new PriorityQueue<>(Collections.reverseOrder());
    sellList = new PriorityQueue<>();
  }

  public int buy(int price) {
    if (sellList.isEmpty()) {
      buyList.offer(price);
      return -1;
    }
    int sellPrice = sellList.peek();
    if (sellPrice > price) {
      buyList.offer(price);
      return -1;
    }
    return sellList.poll();
  }

  public int sell(int price) {
    if (buyList.isEmpty()) {
      sellList.offer(price);
      return -1;
    }
    int buyPrice = buyList.peek();
    if (buyPrice < price) {
      sellList.offer(price);
      return -1;
    }
    return buyList.poll();
  }
}

  public static void main(String[] args) {
  TextBook textBook = new TextBook();
    int[] buyPrice = {100, 101, 103, 130, 140};
    int[] sellPrice = {80, 100, 90, 95, 98};
    for (int i = 0; i < buyPrice.length; i++) {
      textBook.buy(buyPrice[i]);
    }
    for (int j = 0; j < sellPrice.length; j++) {
      System.out.println(textBook.sell(sellPrice[j]));
    }
  }
}


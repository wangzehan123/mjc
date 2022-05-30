package archive.coinbase.phone;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.PriorityQueue;

class SolutionFu {

  public static void main(String[] args) {
  }

  public class TextBookFU {

    class Books {

      PriorityQueue<Book> buyList;
      PriorityQueue<Book> sellList;

      public Books() {
        buyList = new PriorityQueue<>((Book a, Book b)
            -> (int) (b.price - a.price));
        sellList = new PriorityQueue<>((Book a, Book b)
            -> (int) (a.price - b.price));
      }

      public int buy(Book book, int min) {
        if (sellList.isEmpty()) {
          buyList.offer(book);
          return -1;
        }
        while (sellList.peek().checkExpiration(min)) {
          sellList.poll();
          if (sellList.isEmpty()) {
            return -1;
          }
        }
        Book sellBook = sellList.peek();
        if (sellBook.price > book.price) {
          buyList.offer(book);
          return -1;
        }
        Book res = sellList.poll();
        return res.price;
      }

      public int sell(Book book, int min) {
        if (buyList.isEmpty()) {
          sellList.offer(book);
          return -1;
        }
        while (buyList.peek().checkExpiration(min)) {
          buyList.poll();
          if (buyList.isEmpty()) {
            return -1;
          }
        }
        Book buyBook = buyList.peek();
        if (buyBook.price < book.price) {
          sellList.offer(book);
          return -1;
        }
        Book res = buyList.poll();
        return res.price;
      }
    }

    class Book {

      Timestamp expiration;
      int price;

      public boolean checkExpiration(int min) {
        Timestamp actualTimeStampDate =
            new Timestamp(new Date().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(expiration.getTime());
        cal.add(Calendar.MINUTE, min);
        expiration = new Timestamp(cal.getTime().getTime());
        if (expiration.before(actualTimeStampDate)) {
          return true;
        }
        return false;
      }
    }
  }
}

package archive.opendoor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WebCrawler {

interface HtmlParser {
      public default List<String> getUrls(String url) {
        return null;
      }
  }

  public List<String> crawl(String startUrl, HtmlParser htmlParser) {
    Set<String> set = new HashSet<>();
    Queue<String> queue = new LinkedList<>();
    String hostname = getHostname(startUrl);

    queue.offer(startUrl);
    set.add(startUrl);

    while (!queue.isEmpty()) {
      String currentUrl = queue.poll();
      for (String url : htmlParser.getUrls(currentUrl)) {
        if (url.contains(hostname) && !set.contains(url)) {
          queue.offer(url);
          set.add(url);
        }
      }
    }

    return new ArrayList<String>(set);
  }

  private String getHostname(String Url) {
    String[] ss = Url.split("/");
    return ss[2];
  }
}

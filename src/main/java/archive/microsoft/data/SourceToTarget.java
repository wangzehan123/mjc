package archive.microsoft.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SourceToTarget {


  // utility function for printing
// the found path in graph
  private static void printPath(List<Integer> path) {
    int size = path.size();
    for (Integer v : path) {
      System.out.print(v + " ");
    }
    System.out.println();
  }

  // Utility function to check if current
// vertex is already present in path
  private static boolean isNotVisited(int x,
      List<Integer> path) {
    int size = path.size();
    for (int i = 0; i < size; i++)
      if (path.get(i) == x)
        return false;

    return true;
  }

  // Utility function for finding paths in graph
// from source to destination
  private static void findpaths(List<List<Integer>> g,
      int src, int dst, int v) {

    // Create a queue which stores
    // the paths
    Queue<List<Integer>> queue = new LinkedList<>();

    // Path vector to store the current path
    List<Integer> path = new ArrayList<>();
    path.add(src);
    queue.offer(path);

    while (!queue.isEmpty()) {
      path = queue.poll();
      int last = path.get(path.size() - 1);

      // If last vertex is the desired destination
      // then print the path
      if (last == dst) {
        printPath(path);
      }

      // Traverse to all the nodes connected to
      // current vertex and push new path to queue
      List<Integer> lastNode = g.get(last);
      for (int i = 0; i < lastNode.size(); i++) {
        if (isNotVisited(lastNode.get(i), path)) {
          List<Integer> newpath = new ArrayList<>(path);
          newpath.add(lastNode.get(i));
          queue.offer(newpath);
        }
      }
    }
  }
}

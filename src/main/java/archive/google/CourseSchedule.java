package archive.google;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {

  /*
   * @param numCourses: a total of n courses
   * @param prerequisites: a list of prerequisite pairs
   * @return: true if can finish all courses or false
   */
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    List[] graph = new ArrayList[numCourses];
    int[] inDegree = new int[numCourses];

    for (int i = 0; i < numCourses; i++) {
      graph[i] = new ArrayList<Integer>();
    }

    // 建图
    for (int[] edge: prerequisites) {
      graph[edge[1]].add(edge[0]);
      inDegree[edge[0]]++;
    }

    int numChoose = 0;
    Queue queue = new LinkedList();

    // 将入度为 0 的编号加入队列
    for(int i = 0; i < inDegree.length; i++){
      if (inDegree[i] == 0) {
        queue.add(i);
      }
    }

    while (! queue.isEmpty()) {
      int nowPos = (int)queue.poll();
      numChoose++;
      // 将每条边删去，如果入度降为 0，再加入队列
      for (int i = 0; i < graph[nowPos].size(); i++) {
        int nextPos = (int)graph[nowPos].get(i);
        inDegree[nextPos]--;
        if (inDegree[nextPos] == 0) {
          queue.add(nextPos);
        }
      }
    }

    return numChoose == numCourses;
  }
}

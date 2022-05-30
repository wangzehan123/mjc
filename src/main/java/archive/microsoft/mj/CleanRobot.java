package archive.microsoft.mj;

import java.util.HashSet;
import java.util.Set;

public class CleanRobot {

  interface Robot {

    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();

    public void turnRight();

    // Clean the current cell.
    public void clean();
  }

  int[] dx = {-1, 0, 1, 0};
  int[] dy = {0, 1, 0, -1};

  public void cleanRoom(Robot robot) {
    //write your code here
    dfs(robot, new HashSet<>(), 0, 0, 0);
  }

  public void dfs(Robot robot, Set<String> visited, int x, int y, int curDir) {
    String key = x + "@" + y;
    if (visited.contains(key)) {
      return;
    }
    visited.add(key);
    robot.clean();

    for (int i = 0; i < 4; i++) {
      if (robot.move()) {
        //go all the way till cannot move, then back one step
        dfs(robot, visited, x + dx[curDir], y + dy[curDir], curDir);
        //trace back
        backtrack(robot);
      }

      robot.turnRight(); // or turnRight();
      curDir += 1;
      curDir %= 4;
    }
  }

  private void backtrack(Robot robot) {
    robot.turnLeft();
    robot.turnLeft();
    robot.move();
    robot.turnRight();
    robot.turnRight();
  }
}

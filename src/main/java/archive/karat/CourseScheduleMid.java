package archive.karat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// "static void main" must be defined in a public class.
public class CourseScheduleMid {
  public static void main(String[] args) {
    System.out.println("Question 1: ");
    String[][] singleCourseList = new String[][]{
        {"Course_1", "Course_2"},
        {"Course_3", "Course_4"},
        {"Course_2", "Course_3"}
    };

    System.out.println(findMedianCourseI(singleCourseList));

    System.out.println("Question 2: ");
    String[][] courseList = new String[][]{
        {"Course_3", "Course_7"},
        {"Course_1", "Course_2"},
        {"Course_2", "Course_3"},
        {"Course_3", "Course_4"},
        {"Course_4", "Course_5"},
        {"Course_5", "Course_6"},
        // {"Course_6", "Course_7"}
    };
    String[][] courseList2 = new String[][] {
        {"Logic", "COBOL"},
        {"Data Structures", "Algorithms"},
        {"Creative Writing", "Data Structures"},
        {"Algorithms", "COBOL"},
        {"Intro to Computer Science", "Data Structures"},
        {"Logic", "Compilers"},
        {"Data Structures", "Logic"},
        {"Graphics", "Networking"},
        {"Networking", "Algorithms"},
        {"Creative Writing", "System Administration"},
        {"Databases", "System Administration"},
        {"Creative Writing", "Databases"},
        {"Intro to Computer Science", "Graphics"}
    };
    System.out.println(findMedianCourseII(courseList));
    System.out.println(findMedianCourseII(courseList2));
  }

  public static String findMedianCourseI(String[][] courses) {
    Map<String, String> pre2Next = new HashMap<>();
    Set<String> courseWithDep = new HashSet<>();
    for (String[] course : courses) {
      String pre = course[0];
      String next= course[1];
      pre2Next.put(pre, next);
      courseWithDep.add(next);
    }
    List<String> list = new ArrayList<>();
    String startCourse = "";
    for (String course : pre2Next.keySet()) {
      // find course to start with
      if (!courseWithDep.contains(course)) {
        startCourse = course;
        break;
      }
    }
    while (startCourse != null) {
      list.add(startCourse);
      startCourse = pre2Next.get(startCourse);
    }
    return list.get((list.size() - 1) / 2);
  }

  public static List<String> findMedianCourseII(String[][] courses) {
    Map<String, List<String>> courseToDep = new HashMap<>();
    Map<String, Integer> indegree = new HashMap<>();
    // build a map of course to dependent list of courses
    for (String[] coursePair : courses) {
      String pre = coursePair[0], dependent = coursePair[1];
      courseToDep.putIfAbsent(pre, new ArrayList<>());
      courseToDep.putIfAbsent(dependent, new ArrayList<>());
      courseToDep.get(pre).add(dependent);
      indegree.putIfAbsent(pre, 0);
      indegree.put(dependent, indegree.getOrDefault(dependent, 0) + 1);
    }
    // start from courses that has no dependent course
    List<String> medianCourses = new ArrayList<>();

    for (String c : indegree.keySet()) {
      if (indegree.get(c) == 0) {
        List<String> startList = new ArrayList<>();
        startList.add(c);
        findMedianCourseHelper(courseToDep, startList, medianCourses);
      }
    }

    return medianCourses;
  }

  private static void findMedianCourseHelper(
      Map<String, List<String>> courseToDep,
      List<String> path,
      List<String> medianCourses) {
    // record median when reach a track's end, course is not prerequisite of other courses
    String lastCourse = path.get(path.size() - 1);
    if (courseToDep.get(lastCourse).isEmpty()) {
      int midIndex = (path.size() - 1) / 2;
      String midCourse = path.get(midIndex);
      // de-dup
      if (!medianCourses.contains(midCourse)) {
        medianCourses.add(midCourse);
      }
      return;
    }
    for (String depCourse : courseToDep.get(lastCourse)) {
      path.add(depCourse);
      findMedianCourseHelper(courseToDep, path, medianCourses);
      path.remove(path.size() - 1);
    }
  }
}

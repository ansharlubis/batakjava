package mid.utility.ver1;

import upstream.collection.ver1.Point;
/**
 *  upstream.collection.Point is imported when the overview looks like:
 *  overview of class Point {
 *    Point(float, float) in version 1;
 *    float getX() in version 1;
 *    float getY() in version 1;
 *    float distance(Point) in version 1;
 *  }
 */

/*
    Similar with ExtendedPoint, only one possible version to choose.
 */
public class PointUtility {
  public ver1.Point target;
  public ver1.Point mirrorTarget() { return new ver1.Point(target.getY(), target.getX()); }
  public static ver1.Point mirror(ver1.Point p) {
    return new ver1.Point(p.getY(), p.getX());
  }
  public static boolean equals(ver1.Point p1, ver1.Point p2) {
    return (p1.getX() == p2.getX()) && (p2.getY() == p2.getY());
  }
}
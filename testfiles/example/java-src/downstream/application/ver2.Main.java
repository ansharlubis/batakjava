package downstream.application.ver2;
import upstream.collection.ver1.Point;
import upstream.collection.ver2.Point;
/**
 *  upstream.collection.Point is imported when the overview looks like:
 *  overview of class Point {
 *    Point(float, float) in version 1,2;
 *    float getX() in version 1;
 *    float getY() in version 2;
 *    float distance(Point) in version 1,2;
 *    float getR() in version 2;
 *    float getAngle() in version 2;
 *  }
 */
import mid.utility.ver1.*;
/**
 *  mid.utility.ExtendedPoint is imported when the overview looks like:
 *  overview of class ExtendedPoint extends Point {
 *    version 1 requires upstream.collection.Point version 1;
 *    float distanceFromZero() in version 1;
 *  }
 *
 *  mid.utility.PointUtility is imported when the overview looks like:
 *  overview of class PointUtility {
 *    version 1 requires upstream.collection.Point version 1;
 *    static Point mirror(Point) in version 1;
 *    static boolean equals(Point, Point) in version 1;
 *  }
 */
import mid.empty.ver1.Empty;
import mid.empty.ver2.Empty;
/**
 *  mid.empty.Empty is imported when the overview looks like:
 *  overview of class Empty {
 *    Empty() in version 1,2;
 *  }
 */

public class Main {
  /*
    The following methods are generated from:
    public static Point createPoint(Point, Empty);
   */
  public static ver1.Point createPoint(ver1.Point p, ver1.Empty e) {
    ver?.Pair pair = new ver?.Pair();
    p.usePair(pair);
    return new ver1.Point(0f,0f);
  }
  public static ver2.Point createPoint(ver2.Point p, ver1.Empty e) {
    ver?.Pair pair = new ver?.Pair(); -> 1 or 2 OK
    p.usePair(pair)
    return new ver2.Point(0f,0f);
  }
  public static ver1.Point createPoint(ver1.Point p, ver2.Empty e) {
    return new ver1.Point(0f,0f);
  }
  public static ver2.Point createPoint(ver2.Point p, ver2.Empty e) {
    return new ver2.Point(0f,0f);
  }

  /*
    The following methods are generated from:
    public static Empty createEmpty(Point);

    public static ver1.Empty createEmpty(ver1.Point) and public static ver2.Empty createEmpty(ver1.Point) are N
    public static ver1.Empty createEmpty(ver2.Point) and public static ver2.Empty createEmpty(ver2.Point) are NG
   */
  public static ver1.Empty createEmpty(ver1.Point p) {
    return new ver1.Empty();
  }
  public static ver1.Empty createEmpty(ver2.Point p) {
    return new ver2.Empty();
  }
  public static ver2.Empty createEmpty(ver1.Point p) { return new ver2.Empty(); }
  public static ver2.Empty createEmpty(ver2.Point p) {
    return new ver2.Empty();
  }

  /* No version can be inferred */
  public static void main(String[] args) {
    ver?.Point a = new ver?.Point(1f,30f);
    ver1.ExtendedPoint b = new ver1.ExtendedPoint(0f,0f);

    // The following two method invocations are limited to ver2.Point
    float r = a.getR();
    float angle = a.getAngle();

    // The following method invocation is limited to ver1.Point
    ver?.Point mirroredA = ver1.PointUtility.mirror(a);

    float distance = a.distance(b);
    float bFromZero = b.distanceFromZero();
    boolean t = ver1.PointUtility.equals(b, new ver1.Point(0f,0f));
  }
}
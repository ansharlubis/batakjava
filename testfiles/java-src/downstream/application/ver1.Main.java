package downstream.application.ver1;
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
    All the possible combinations are generated
   */
  public static ver1.Point createPoint(ver1.Point p, ver1.Empty e) {
    return new ver1.Point(0f,0f);
  }
  public static ver1.Point createPoint(ver1.Point p, ver2.Empty e) {
    return new ver1.Point(0f,0f);
  }

  public static ver1.Empty createEmpty(ver1.Point p) {
    return new ver1.Empty();
  }
  public static ver2.Empty createEmpty(ver1.Point p) {
    return new ver2.Empty();
  }

  /*
    Since there are no options, everything would be
    inferred with the only available version.
   */
  public static void main(String[] args) {
    ver1.Point a = new ver1.Point(1f,2f);
    ver1.ExtendedPoint b = new ver1.ExtendedPoint(0f,0f);
    float x = a.getX();
    float y = a.getY();
    ver1.Point mirroredA = ver1.PointUtility.mirror(a);
    float distance = a.distance(b);
    float bFromZero = b.distanceFromZero();
    boolean t = ver1.PointUtility.equals(a, new ver1.Point(0f,0f));
  }

}
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
    Based on the overview, the class would infer ver.1, because that's
    the only one existing right now. If there are multiple available
    versions, the programmer can assign priority during the import.
 */
public class ExtendedPoint extends ver1.Point {
  public ExtendedPoint(float x, float y) {
    super(x,y);
  }
  /*
      Since there is only one Point that can be inferred,
      this would be selected.
   */
  public float distanceFromZero() {
    return new ver1.Point(0,0).distance(this);
  }
  public Point parent() {
    return new Point(getX(), getY());
  }
}

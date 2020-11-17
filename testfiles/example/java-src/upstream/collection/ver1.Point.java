/**
 *  The idea is that each version would be grouped in different package
 *  with different naming based on version numbers
 */
package upstream.collection.ver1;

/**
 *  There is no need for version inference in the upstream package
 */
public class Point {
  private float x;
  private float y;
  public Point(float x, float y) {
    this.x = x; this.y = y;
  }
  public float getX() { return x; }
  public float getY() { return y; }
  public float distance(Point other) {
    return java.lang.Math.sqrt(
      ((x - other.getX())*(x - other.getX())) +
      ((y - other.getY())*(y - other.getY()))
    );
  }
}
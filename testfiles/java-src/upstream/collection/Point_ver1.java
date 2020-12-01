/**
 *  The idea is that each version would be grouped in different package
 *  with different naming based on version numbers
 */
package upstream.collection;

/**
 *  There is no need for version inference in the upstream package
 */
public class Point_ver1 {
  private float x;
  public Point_ver1(float x) {
    this.x = x;
  }
  public float getX() { return x; }
  public Point_ver1 move(Displacement_ver1 d) {
    return new Point_ver1(x + d.getValue(new Displacement_ver1(1)));
  }
  public double distance(Point_ver1 other, Point_ver1 another) {
    Displacement_ver1 e = new Displacement_ver1(1);
    float f = other.x;
    return x - other.getX();
  }
}
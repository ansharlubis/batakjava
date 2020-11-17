package upstream.collection.ver3;

/**
 *  There is no need for version inference in the upstream package
 */
public class Point {
  private float r;
  private float angle;
  public Point(float r, float angle) {
    this.r = r; this.angle = angle;
  }
  public float getR() { return r; }
  public float getAngle() { return angle; }
  public float distance(Point other) {
    return java.lang.Math.sqrt(
      r*r+(other.getR()*other.getR())-
      2*r*other.getR()*java.lang.Math.cos(angle-other.getAngle())
    );
  public boolean returnTrue() { return true; }
}
